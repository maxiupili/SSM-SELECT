package org.select.dao;

import org.select.entitiy.User;

import java.util.List;

public interface UserDao {
    User queryByUsername(String username);
    List<User> listAllUsers();

}
