package org.select.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.select.entitiy.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring/spring-dao.xml"})
public class UserDaoTest {
    @Resource
    private UserDao userDao;
    @Test
    public void queryPasswordById() throws Exception {
        String username = "20151308057";
        User user = userDao.queryByUsername(username);
        System.out.println(user.getPassword());
        System.out.println(user);
        /**
         * 123456
         *User{userId=10001, username='全雪峰', password='123456'}
         */
    }

    @Test
    public void listAllUsers() throws Exception {
        List<User> users = userDao.listAllUsers();
        for(User user:users)
            System.out.println(user);
    }

}