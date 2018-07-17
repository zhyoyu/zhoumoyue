package mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import po.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisFirst {

    @Test
    public void findUserByIdTest() throws IOException {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //保存配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂,传入mybatis的配置文件信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过会话工厂得到sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过sqlSession操作数据库
        //第一个参数：映射文件中statement的id,等于namespace+"."+statement的id
        //第二个参数：指定和映射文件中匹配的paramType类型的参数
        //sqlSession.selectOne 结果就是映射文件中匹配的resultType类型的对象
        User user = sqlSession.selectOne("test.findUserById", 1);
        System.out.println(user);

        //释放资源
        sqlSession.close();
    }

    //根据用户名称模糊查询用户列表
    @Test
    public void findUserByName() throws IOException {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //保存配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂,传入mybatis的配置文件信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过会话工厂得到sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<User> list = sqlSession.selectList("test.findUserByName", "小明");
        System.out.println(list);

        sqlSession.close();
    }

    //添加用户
    @Test
    public void insertUserTest() throws IOException {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //保存配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂,传入mybatis的配置文件信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过会话工厂得到sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //插入用户对象
        User user = new User();

        user.setUserName("王五");
        user.setSex("女");
        user.setBirthday(new Date());
        user.setAddress("北京");
        sqlSession.insert("test.insertUser", user);

        //执行提交事务
        sqlSession.commit();
        System.out.println(user.getId());
        sqlSession.close();
    }

    //删除用户
    @Test
    public void deleteUserTest() throws IOException {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //保存配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂,传入mybatis的配置文件信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过会话工厂得到sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        sqlSession.delete("test.deleteUser", 6);

        //执行提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    //更新用户
    @Test
    public void updateUserTest() throws IOException {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //保存配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂,传入mybatis的配置文件信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过会话工厂得到sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = sqlSession.selectOne("test.findUserById", 5);
        user.setUserName("王五");
        sqlSession.update("test.updateUser", user);
        //执行提交事务
        sqlSession.commit();
        sqlSession.close();
    }
}
