package org.select.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.select.entitiy.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class UserServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    @Test
    public void checkUser() throws Exception {
        User user = userService.login("20151308057","12345");
        System.out.println(user);
        //Checker checker1 = userService.checkUser(1002L,"1234");
        //System.out.println(checker1);

    }

}