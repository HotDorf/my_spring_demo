package com.example.annotation.mybatisplus.service;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.annotation.mybatisplus.controller.MybatisplusUserController;
import com.example.annotation.mybatisplus.entities.entity.MybatisplusUser;
import com.example.annotation.mybatisplus.entities.mapper.MybatisplusUserMapper;
import com.example.annotation.mybatisplus.entities.service.impl.MybatisplusUserServiceImpl;
import com.example.annotation.mybatisplus.pojo.BigPerson;
import com.example.annotation.poi.Person;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class UserEntityService {

    @Resource
    private MybatisplusUserMapper mybatisplusUserMapper;

    @Resource
    private MybatisplusUserServiceImpl userServiceImpl;

    @Resource
    private MybatisplusUserController mybatisplusUserController;


    /**
     * service层调用Controller层
     * Spring容器是父容器，servlet是子容器，有很多个servlet容器，他们公用spring的IOC容器XmlWebApplicationContext
     * 子上下文可以访问父上下文中的bean，但是父上下文不可以访问子上下文中的bean。
     */
    public void testContext() {
        mybatisplusUserController.selectAll();
    }

    public void testInsert() {
        MybatisplusUser mybatisplusUser = new MybatisplusUser(null, "zhangsan100", 19, "123@163.com");
        userServiceImpl.save(mybatisplusUser);
    }

    public void testAnd() {
        QueryWrapper<MybatisplusUser> query = new QueryWrapper<>();
        query.eq("name", "csj").and(i -> i.eq("age", 19).eq("id", 4));
        List<MybatisplusUser> mybatisplusUsers = mybatisplusUserMapper.selectList(query);
        System.out.println(mybatisplusUsers);
    }

    public void testPage() {
        Page<MybatisplusUser> personPage = new Page<>();
        personPage.setSize(2);
        personPage.setCurrent(1);
        Page<MybatisplusUser> page = userServiceImpl.page(personPage, new QueryWrapper<MybatisplusUser>());
        System.out.println(page.getRecords());
    }

    public void save() {
        MybatisplusUser mybatisplusUser = new MybatisplusUser(null, "zhangsan1", 19, "123@163.com");
        MybatisplusUser mybatisplusUser2 = new MybatisplusUser(null, "zhangsan4", 19, "123@163.com");
        MybatisplusUser mybatisplusUser3 = new MybatisplusUser(null, "zhangsan5", 19, "123@163.com");
        MybatisplusUser mybatisplusUser4 = new MybatisplusUser(2, "zhangsanmax", 19, "123@163.com");
        LinkedList<MybatisplusUser> userList = new LinkedList<>();
        userList.add(mybatisplusUser2);
        userList.add(mybatisplusUser3);

        //userServiceImpl.saveBatch(userList);
        //userServiceImpl.saveOrUpdate(mybatisplusUser4); //如果不能确定唯一的数据就会新增
        List<Map<String, Object>> maps = userServiceImpl.listMaps();
        maps.forEach(each -> {
            each.forEach((k, v) -> {
                // System.out.println(k + "===" + v);
            });
        });

        // 分页
        Page<MybatisplusUser> mybatisplusUserPage = new Page<>();
        mybatisplusUserPage.setCurrent(3);
        mybatisplusUserPage.setSize(3);
        Page<MybatisplusUser> page = mybatisplusUserMapper.selectPage(mybatisplusUserPage, null);
        Page<MybatisplusUser> page1 = userServiceImpl.page(mybatisplusUserPage); //当操作封装类为null时就这么写

        // chain
        QueryChainWrapper<MybatisplusUser> chainWrapper = new QueryChainWrapper<MybatisplusUser>(mybatisplusUserMapper);
        List<MybatisplusUser> listUser = chainWrapper.eq("name", mybatisplusUser.getName())
                .eq("age", mybatisplusUser.getAge())
                .list();
        listUser.forEach(System.out::println);
        chainWrapper.le("age", 18).list();
        System.out.println("1");
    }

    /**
     * 查数据
     *
     * @return
     */
    public List<MybatisplusUser> testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<MybatisplusUser> mybatisplusUserList = null;
        try {
            mybatisplusUserList = mybatisplusUserMapper.selectList(null); //条件为null，表示查询所有
        } catch (Exception e) {
            e.printStackTrace();
        }
        mybatisplusUserList.forEach(System.out::println);
        return mybatisplusUserList;
    }
}
