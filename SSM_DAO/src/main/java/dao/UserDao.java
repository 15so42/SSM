package dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pojo.User;

import java.util.List;
@Repository
public interface UserDao {
    public List<User> getUserList(@Param("queryUserName") String queryUserName,@Param("queryUserRole") int queryUserRole,@Param("start") int start,@Param("pageSize") int pageSize) ;
    public User login(@Param("userCode") String userCode, @Param("userPassword") String userPassword);
    public int getUserCount(@Param("queryUserName") String queryUserName,@Param("queryUserRole") int queryUserRole);
    public List<User> getAllUser();
    public User getUserById(@Param("id") int id);
    public int insertUser(@Param("user") User user);
    public int updateUser(@Param("id") int id,@Param("user")User user);
    public int deleteUserById(@Param("id") int id);
}
