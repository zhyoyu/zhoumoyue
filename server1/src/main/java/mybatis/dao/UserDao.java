package mybatis.dao;

import po.User;

public interface UserDao {

    //根据用户id查询用户信息
    User findUserById(int id) throws Exception;
    //添加用户信息
    void insertUser(User user) throws Exception;
    //删除用户信息
    void deleteUser(int id) throws Exception;

}
