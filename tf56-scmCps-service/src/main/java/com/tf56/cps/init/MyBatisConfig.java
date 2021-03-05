package com.tf56.cps.init;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis相关配置
 * Created by matthew on 2021/2/25 07:11 下午
 */
@Configuration
@MapperScan({"com.tf56.cps.mapper"})
public class MyBatisConfig {
}
