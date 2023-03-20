package com.sangeng.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：Achen
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    @PreAuthorize("hasAnyAuthority('system:user:list')")
    public String hello() {
        return "hello";
    }
}
