package dao;

import po.User;

public interface UserDao {

    //根据用户id查询用户信息
    User findUserById(int id) throws Exception;

}
