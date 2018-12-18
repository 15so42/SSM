package Impl;

import pojo.User;

import java.util.List;

public interface UserService {
    public List<User> getUserList();
    public User login(String userCode,String userPassword);
}
