package mybatis.mapper;

import po.User;
import po.UserCustom;
import po.UserQueryVo;

import java.util.List;

public interface UserMapper {

    //根据用户id查询用户信息
    User findUserById(int id);

    List<User> findUserByName(String userName);

    List<UserCustom> findUserList(UserQueryVo vo);

    int findUserCount(UserQueryVo vo);

    List<User> findUserByIdResultMap(int id);
//    //根据用户id查询用户信息
//    User findUserById(int id) throws Exception;
//    //添加用户信息
//    void insertUser(User user) throws Exception;
//    //删除用户信息
//    void deleteUser(int id) throws Exception;

}
