package org.select.service.impl;


import org.select.dao.UserDao;
import org.select.dto.Checker;
import org.select.entitiy.User;
import org.select.exception.EmptyFieldException;
import org.select.exception.LoginException;
import org.select.exception.SelectException;
import org.select.service.UserService;
import static  org.select.util.Util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserDao userDao;

    public User login(String username, String password) throws EmptyFieldException,SelectException{
       try{
           username =$("账户不能为空",username);
           password =$("密码不能为空",password);
           User user = userDao.queryByUsername(username);
           if(user == null)
               throw new LoginException("账户不存在");
           if(!user.getPassword().equals(password))
               throw  new LoginException("密码错误");
           return user;
       /*else
           throw new LoginException("密码错误");*/
       }catch (EmptyFieldException e1){
           throw  e1;
       }catch(LoginException e2){
           throw  e2;
       }
       catch (Exception e){
           logger.error(e.getMessage(),e);
           throw new SelectException("select inner error:"+e.getMessage());
       }

    }
}
