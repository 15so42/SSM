package dao;

import org.springframework.stereotype.Repository;
import pojo.Role;

import java.util.List;
@Repository
public interface RoleDao {
    public List<Role> getRoleList();
}
