package com.example.annotation.springDataJPA.service;

import com.example.annotation.springDataJPA.dao.OperateUserDao;
import com.example.annotation.springDataJPA.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    OperateUserDao userDao;

    public List<User> findAllUser(){
        return userDao.findAll();
    }

    /**
     *  条件分页查询
     * @param params
     * @return
     */
    public List<User> queryPage(Map<String, Object> params) {
        /**
         * 自定义查询条件
         *      1. 实现Specification接口(提供泛型；查询的对象类型）
         *      2. 实现toPredicate方法（构造查询条件）
         *      3.  需要借助方法参数中的两个参数（
         *              root：获取需要查询的对象属性
         *              CriteriaQuery<?>: 执行查询条件
         *              CriteriaBuilder：构造查询条件，内部封装了很多查询条件（模糊匹配，精准匹配）
         *                              gt, ge, lt, le, like（分别表示>, >=, <, <=,模糊查询）
         */
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                // 1. 创建集合 存储查询条件
                List<Predicate> list = new ArrayList<>();
                // 2. 添加查询条件
                if (!"".equals(params.get("name")) && params.get("name") != null) {
                    // 当title 有值时  项目list集合中存储查询条件     select * from t_user where name like %name%
                    Path<String> path = root.get("name");
                    Predicate predicate = criteriaBuilder.like(path,"%" + params.get("name") + "%");
                    list.add(predicate);
                }
                if (params.get("age") != null) {
                    // age > 19
                    list.add(criteriaBuilder.gt(root.get("age"), Integer.parseInt((String) params.get("age"))));
                }
                if (params.get("email") != null) {
                    // email = test5@baomidou.com
                    list.add(criteriaBuilder.equal(root.get("email"), params.get("email")));
                }
                // 3. 执行查询
               query.where(list.toArray(new Predicate[list.size()]));

                return null;
            }
        };

        Integer pageNo = Integer.valueOf((String) params.get("pageNo"));
        Integer pageSize = Integer.valueOf((String)params.get("pageSize"));
        /**
         * 添加排序Sort, new Sort()是私有方法了，不能用
         *      Sort.Direction.DESC表示降序
         *      Sort.Direction.ASC表示升序
         *      properties是指实体类的属性名（不是字段名）
         */
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        /**
         *  分页参数Pageable
         *      参数1：查询的页码
         *      参数2：每页查询的条数
         *      参数3：查询结果的排序规则（可选
         */
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort); //原来的new PageRequest()已经过时
        /**
         *  分页查询
         *      参数1：查询条件Specification
         *      参数2：分页参数Pageable
         */
        Page<User> page = userDao.findAll(spec, pageable);
        return page.getContent();
    }

    /**
     *  只是分页不带条件的情况
     * @param params
     * @return
     */
    public List<User> queryPageWithoutSprcifical(Map<String, Object> params) {
        Integer pageNo = Integer.valueOf((String) params.get("pageNo"));
        Integer pageSize = Integer.valueOf((String)params.get("pageSize"));
        /**
         * 添加排序Sort, new Sort()是私有方法了，不能用
         *      Sort.Direction.DESC表示降序
         *      Sort.Direction.ASC表示升序
         *      properties是指实体类的属性名（不是字段名）
         */
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        /**
         *  分页参数Pageable
         *      参数1：查询的页码
         *      参数2：每页查询的条数
         *      参数3：查询结果的排序规则（可选
         */
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort); //原来的new PageRequest()已经过时
        /**
         *  分页查询
         *      参数1：查询条件Specification
         *      参数2：分页参数Pageable
         */
        Page<User> page = userDao.findAll(pageable);
        return page.getContent();
    }

}
