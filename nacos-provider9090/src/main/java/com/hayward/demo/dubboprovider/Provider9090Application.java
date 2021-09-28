package com.hayward.demo.dubboprovider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
@MapperScan("com.hayward.demo.dubboapi.dao")
public class Provider9090Application {
    public static void main(String[] args) {
        SpringApplication.run(Provider9090Application.class,args);
    }
}
