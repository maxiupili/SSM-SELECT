package org.select.service;

import org.select.entitiy.User;
import org.select.exception.LoginException;
import org.select.exception.SelectException;

public interface UserService {
    public User login(String username, String password) throws LoginException,SelectException;
}
