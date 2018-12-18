package Impl;

import dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.User;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

    public List<User> getUserList() {
        return userDao.getUserList();
    }

    public User login(String userCode,String userPassword) {

        return userDao.login(userCode,userPassword);
    }


}