package com.sangeng.expression;

import com.sangeng.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义权限校验
 *
 * @Author：Achen
 */
@Component("ex")
public class SGExpressionRoot {
    public boolean hasAnyAuthority(String authorities) {
        // 获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 获取当前用户
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 获取当前权限
        List<String> permissions = loginUser.getPermissions();
        // 判断authorities是否存在于用户权限集合
        return permissions.contains(authorities);
    }
}
