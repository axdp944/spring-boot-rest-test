package com.example.server;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//@Import(RestTestApplication.class)
@EnableFeignClients(basePackages = "com.example.client")
@Configuration
@ComponentScan(basePackages = {"com.example.server"})
@SpringBootConfiguration
public class TestConfig {
}
