package com.paul.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication // 標註這是一個SpringBoot應用程式
@ServletComponentScan
public class DemoApplication {
 public static void main(String[] args) {
     // 通過SpringApplication調用run的方式啟動工程
       SpringApplication.run(DemoApplication.class, args);
    }
}
