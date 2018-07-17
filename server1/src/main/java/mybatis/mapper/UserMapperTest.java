package mybatis.mapper;

import mybatis.dao.UserDao;
import mybatis.dao.UserDapImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import po.User;
import po.UserCustom;
import po.UserQueryVo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UserMapperTest {

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
    public void testFindUserById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findUserById(1);
        System.out.println(user);
    }

    @Test
    public void testFindUserByName() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> user = mapper.findUserByName("张三");
        System.out.println(user);
    }

    @Test
    public void testFindUserList() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserQueryVo vo = new UserQueryVo();
        UserCustom userCustom = new UserCustom();
        userCustom.setId(3);
        userCustom.setUserName("张三");
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        vo.setIds(list);
        vo.setUserCustom(userCustom);
        List<UserCustom> user = mapper.findUserList(vo);
        System.out.println(user);
    }

    @Test
    public void testFindUserCount() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserQueryVo vo = new UserQueryVo();
        UserCustom userCustom = new UserCustom();
        userCustom.setId(3);
        userCustom.setUserName("张三");
        vo.setUserCustom(userCustom);
        int count = mapper.findUserCount(vo);
        System.out.println(count);
    }

    @Test
    public void testFindUserByIdResultMap() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> user = mapper.findUserByIdResultMap(1);
        System.out.println(user);
    }
}
