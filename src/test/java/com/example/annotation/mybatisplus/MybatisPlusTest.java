package com.example.annotation.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.annotation.dynamicProxy.springAOP.service.IAccountService;
import com.example.annotation.mybatisplus.entities.entity.MybatisplusUser;
import com.example.annotation.mybatisplus.entities.service.impl.MybatisplusUserServiceImpl;
import com.example.annotation.mybatisplus.service.UserEntityService;
import com.example.annotation.springDataJPA.service.UserService;
import com.example.annotation.utils.Datautil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.config.TransactionManagementConfigUtils;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusTest {

    @Resource(name = "userEntityService", type = UserEntityService.class)
    private UserEntityService userEntityUser;

    @Resource
    private MybatisplusUserServiceImpl mybatisplusUserServiceImpl;

    @Resource
    private UserService userService;

    @Test
    public void testJpa(){
        Datautil datautil = new Datautil();
        datautil.getAll();
        //System.out.println(userService.findAllUser());
    }

    @Test
    public void getAll(){
        userEntityUser.testSelect();
    }

    @Test
    public void saveBatch(){
        userEntityUser.save();
    }

    @Test
    public void test(){
        QueryWrapper<MybatisplusUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id","1").eq("name","csj");
        System.out.println(mybatisplusUserServiceImpl.getOne(queryWrapper));
    }

    @Test
    public void testAnd(){
        //userEntityUser.testAnd();
        userEntityUser.testInsert();

    }

    @Test
    public void testMybqtis(){
        /*// 加载配置文件
        InputStream in = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
        // 创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 创建SqlSession对象
        SqlSession session = factory.openSession();
        // 获取到代理对象
        IAccountService dao = session.getMapper(IAccountService.class);
        dao.saveAccount();*/
        Transaction transaction;
    }

}
