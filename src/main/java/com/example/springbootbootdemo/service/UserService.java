package com.example.springbootbootdemo.service;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootbootdemo.entity.TokenUtils;
import com.example.springbootbootdemo.entity.User;
import com.example.springbootbootdemo.exception.ServiceException;
import com.example.springbootbootdemo.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 功能:
 * 作者:晨兴
 * 日期: 2023/10/11 23:45
 */

@Service
public class UserService extends ServiceImpl<UserMapper,User> {
    @Resource
    private UserMapper userMapper;

    public User selectByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return getOne(queryWrapper);
    }

    public User login(User user) {
        User dbUser = selectByUsername(user.getUsername());

        if (dbUser == null)
            throw new ServiceException("用户不存在");
        else if (!(user.getPassword().equals(dbUser.getPassword())))
            throw new ServiceException("密码错误");

        //登陆成功，生成token,并传入dbUser
        dbUser.setToken(TokenUtils.createToken(dbUser.getId().toString(),dbUser.getPassword()));


        return dbUser;


    }


    public boolean register(User user) {
        User dbUser = selectByUsername(user.getUsername());
        if(dbUser!=null)
            throw new ServiceException("用户名已存在，请更改用户名");
        user.setName("用户-"+ RandomUtil.randomNumbers(4));
        userMapper.insert(user);
        return true;
    }

}
