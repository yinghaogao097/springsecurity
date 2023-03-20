package com.sangeng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：Achen
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
