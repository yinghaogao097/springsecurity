package com.sangeng;

import com.sangeng.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author：Achen
 */
@SpringBootTest
public class MapperTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testConfig() {
    }

    @Test
    public void testUserMapper() {
        userMapper.selectList(null).forEach(System.out::println);
    }

}
