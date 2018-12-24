package dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pojo.Role;

import java.util.List;
@Repository
public interface RoleDao {
    public List<Role> getRoleList(@Param("queryUserName") String queryUserName,@Param("queryUserRole") int queryUserRole,@Param("pageIndex") int pageIndex);

}
