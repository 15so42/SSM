package Impl;

import org.springframework.stereotype.Repository;
import pojo.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getRoleList();
}
