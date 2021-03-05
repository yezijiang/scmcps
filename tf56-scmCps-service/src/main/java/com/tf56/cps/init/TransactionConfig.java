package com.tf56.cps.init;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author : matthew
 * @description :
 * @date : 2021/2/26 10:08 上午
 **/
@Configuration
@EnableTransactionManagement
public class TransactionConfig {


    @ConfigurationProperties("spring.datasource")
    @Bean(name = "masterDataSource")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }


    /**
     * 向容器中注入事务管理器
     */
    @Bean("txManager")
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(druidDataSource());
    }



}
