package Impl;

import dao.RoleDao;
import dao.UserDao;
import org.springframework.stereotype.Service;
import pojo.Role;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements  RoleService {
    @Resource
    private RoleDao roleDao;

    public List<Role> getRoleList() {
        return roleDao.getRoleList();
    }
}
