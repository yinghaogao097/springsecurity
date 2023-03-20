package com.sangeng.service.impl;

import com.sangeng.domain.LoginUser;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.User;
import com.sangeng.service.LoginService;
import com.sangeng.utils.JwtUtil;
import com.sangeng.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author：Achen
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        // 封装Authentication对象 存入用户名和密码
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        // 调用authenticate进行验证 这一步会去UserDetailsServiceImpl
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或者密码错误。。");
        }
        // 使用userId生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        // 生产jwt
        String jwt = JwtUtil.createJWT(userId);
        // authenticate存入redis
        redisCache.setCacheObject("login:" + userId, loginUser);
        // token响应给前端
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return new ResponseResult(200, "登陆成功", map);
    }

    @Override
    public ResponseResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();
        redisCache.deleteObject("login:" + id);
        return new ResponseResult(200, "注销成功");
    }
}
