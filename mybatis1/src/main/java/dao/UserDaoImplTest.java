package dao;

import cn.itcast.ssm.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import po.User;

public class UserDaoImplTest {

    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception{
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void testFindUserById() throws Exception {
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        User user = userDao.findUserById(1);
        System.out.println(user);
    }

    public void test() throws Exception {
        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        userMapper.selectByExample()
    }
}
