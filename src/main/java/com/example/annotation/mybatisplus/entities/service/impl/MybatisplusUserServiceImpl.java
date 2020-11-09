package com.example.annotation.mybatisplus.entities.service.impl;

import com.example.annotation.mybatisplus.entities.entity.MybatisplusUser;
import com.example.annotation.mybatisplus.entities.mapper.MybatisplusUserMapper;
import com.example.annotation.mybatisplus.entities.service.IMybatisplusUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author csj
 * @since 2020-01-13
 */
@Service
public class MybatisplusUserServiceImpl extends ServiceImpl<MybatisplusUserMapper, MybatisplusUser> implements IMybatisplusUserService {

}
