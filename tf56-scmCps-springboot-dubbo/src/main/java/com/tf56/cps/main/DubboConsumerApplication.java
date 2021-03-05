package com.tf56.cps.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @author : matthew
 * @description :
 * @date : 2021/2/25 11:11 上午
 **/
@ComponentScan({"com.tf56.cps"})
@ImportResource({"classpath:config/applicationContext.xml"})
@SpringBootApplication
public class DubboConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }
}
