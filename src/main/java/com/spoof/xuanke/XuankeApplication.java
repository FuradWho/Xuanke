package com.spoof.xuanke;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.spoof.xuanke.mapper")
public class XuankeApplication {

    public static void main(String[] args) {
        SpringApplication.run(XuankeApplication.class, args);
    }

}
