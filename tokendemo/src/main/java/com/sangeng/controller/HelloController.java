package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：Achen
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
//    @PreAuthorize("hasAnyAuthority('system:user:list22')")
    @PreAuthorize("@ex.hasAnyAuthority('system:user:list')")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/testCors")
    public ResponseResult testCors() {
        return new ResponseResult(200, "testCors");
    }

    @RequestMapping("/testdd")
    public ResponseResult testdd() {
        return new ResponseResult(200, "testdd");
    }
}
