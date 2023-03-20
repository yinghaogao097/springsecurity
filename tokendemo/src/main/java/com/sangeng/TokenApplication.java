package com.sangeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author：Achen
 */
@SpringBootApplication
@MapperScan("com.sangeng.mapper")
public class TokenApplication {
    public static void main(String[] args) {
        SpringApplication.run(TokenApplication.class, args);
    }
}
