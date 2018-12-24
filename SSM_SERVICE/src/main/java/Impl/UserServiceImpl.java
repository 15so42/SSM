package Impl;

import dao.UserDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.User;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;


    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    public User getUserById(int id){
        return userDao.getUserById(id);
    }

    public int InsertUser(User user) {
        return userDao.insertUser(user);
    }

    public int updateUser(int id, User user) {
        return userDao.updateUser(id,user);
    }

    public int deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }

    public User login(String userCode, String userPassword) {

        return userDao.login(userCode,userPassword);
    }

    public int getUserCount(String queryUserName,int queryUserRole) {
        if(queryUserName.equals("")||queryUserName==null){
            queryUserName="%";}
        return userDao.getUserCount(queryUserName,queryUserRole);
    }

    public List<User> getUserList(String queryUserName, int _queryUserRole, int currentPageNo, int pageSize) {
        System.out.println("queryUserName is "+queryUserName);
            if(queryUserName.equals("")||queryUserName==null)
                queryUserName="%";

            int start=(currentPageNo-1)*pageSize;

            return userDao.getUserList(queryUserName,_queryUserRole,start,pageSize);


    }


}
