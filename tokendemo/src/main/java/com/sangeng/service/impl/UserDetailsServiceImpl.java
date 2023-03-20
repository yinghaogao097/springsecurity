package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sangeng.domain.LoginUser;
import com.sangeng.domain.User;
import com.sangeng.mapper.MenuMapper;
import com.sangeng.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author：Achen
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        // 非空判断
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或者密码错误");
        }
        List<String> permissionKeyList = menuMapper.selectPermsByUserId(user.getId());
        return new LoginUser(user, permissionKeyList);
    }
}
