package Impl;

import pojo.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUser();
    public User login(String userCode,String userPassword);
    public int getUserCount(String queryUserName,int queryUserRole);
    public List<User> getUserList(String queryUserName,int queryUserRole,int currentPageNo,int pageSize);
    public User getUserById(int id);
    //添加用户
    public int InsertUser(User user);
    //修改用户
    public int updateUser(int id,User user);
    //删除用户
    public int deleteUserById(int id);
}
