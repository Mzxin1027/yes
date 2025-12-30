package com.neuedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.neuedu.mapper")
public class LogisiticsLycApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisiticsLycApplication.class, args);
    }

}
