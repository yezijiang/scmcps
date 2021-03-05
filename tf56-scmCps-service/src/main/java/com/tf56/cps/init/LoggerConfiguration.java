package com.tf56.cps.init;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.tf56.cps.common.util.StringPersonalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author : maxx
 * @description：监听apollo的变更
 * @date ：2019/5/7 20:37
 */
@Service
@Slf4j
public class LoggerConfiguration {
    private static final String LOGGER_TAG = "logging.level.";

    @Autowired
    private LoggingSystem loggingSystem;

    @ApolloConfig
    private Config config;

    @ApolloConfigChangeListener
    private void onChange(ConfigChangeEvent changeEvent) {
        refreshLoggingLevels();
    }

    private void refreshLoggingLevels() {
        Set<String> keyNames = config.getPropertyNames();
        for (String key : keyNames) {
            if (StringPersonalUtil.containsIgnoreCase(key, LOGGER_TAG)) {
                String strLevel = config.getProperty(key, "info");
                LogLevel level = LogLevel.valueOf(strLevel.toUpperCase());
                loggingSystem.setLogLevel(key.replace(LOGGER_TAG, ""), level);
                log.info("{}:{}", key, strLevel);
            }
        }
    }
}
