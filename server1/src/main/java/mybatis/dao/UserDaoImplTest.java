package mybatis.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import po.User;

import java.io.InputStream;

public class UserDaoImplTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //保存配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂,传入mybatis的配置文件信息
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }

    @Test
    public void testFindUserById() throws Exception {
        UserDao userDao = new UserDapImpl(sqlSessionFactory);

        User user = userDao.findUserById(1);
        System.out.println(user);
    }

}
