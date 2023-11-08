package com.example.springbootbootdemo.controller;

import cn.hutool.core.util.StrUtil;
import com.example.springbootbootdemo.common.AuthAccess;
import com.example.springbootbootdemo.common.Result;
import com.example.springbootbootdemo.entity.User;
import com.example.springbootbootdemo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * 功能:
 * 作者:晨兴
 * 日期: 2023/10/23 12:51
 */
@RestController

public class WebController {

    @Resource
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword()))
            return Result.error("数据不合法");
        User dbUser = userService.login(user);

        //登陆成功之后把token放进dbuser里面去

        return Result.success(dbUser);
    }


    @AuthAccess
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        //注册时输入的用户名密码不能为空
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword()))
            return Result.error("数据不合法");
        userService.register(user);


        return Result.success();
    }





}
