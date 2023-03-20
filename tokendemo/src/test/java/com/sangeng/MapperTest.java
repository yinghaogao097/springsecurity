package com.sangeng;

import com.sangeng.mapper.MenuMapper;
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

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testConfig() {
        System.out.println(passwordEncoder.encode("1234"));

    }
    @Test
    public void testMenuMapper(){
        menuMapper.selectPermsByUserId(3L).forEach(System.out::println);
    }


    @Test
    public void testUserMapper() {
        userMapper.selectList(null).forEach(System.out::println);
    }

}
