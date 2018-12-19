package Impl;

import pojo.User;

import java.util.List;

public interface UserService {
    public List<User> getUserList();
    public User login(String userCode,String userPassword);
    public int getUserCount(String queryUserName,int _queryUserRole);
    public List<User> getUserList(String queryUserName,int _queryUserRole,int currentPageNo,int pageSize);
}
