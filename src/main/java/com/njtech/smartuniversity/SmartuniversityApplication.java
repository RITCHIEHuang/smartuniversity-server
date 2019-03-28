package com.njtech.smartuniversity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.njtech.smartuniversity.dao")
public class SmartuniversityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartuniversityApplication.class, args);
    }
}
