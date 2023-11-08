package com.example.springbootbootdemo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootbootdemo.common.Result;
import com.example.springbootbootdemo.entity.User;
import com.example.springbootbootdemo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能:
 * 作者:晨兴
 * 日期: 2023/10/11 20:56
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/insert")
    public Result insert(@RequestBody User user) {
        try {
            userService.save(user);

        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return Result.error("数据库错误");
            } else
                return Result.error("系统错误");
        }
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user) {

        userService.updateById(user);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.removeById(id);
        return Result.success();
    }

    @DeleteMapping("/delete/batch")
    public Result deletes(@RequestBody List<Integer> ids) {
        userService.removeBatchByIds(ids);
        return Result.success();
    }

    @GetMapping("/select/{id}")
    public Result select(@PathVariable Integer id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

    @GetMapping("/selectAll")
    public Result selectAll() {
        List<User> users = userService.list(new QueryWrapper<User>().orderByDesc("id"));
        return Result.success(users);
    }

//    分页显示，查询

    @GetMapping("/ ")
    public Result selectPages(
            @RequestParam int pageSize,
            @RequestParam int pageNum,
            @RequestParam String username,
            @RequestParam String name
    ) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isBlank(username), "username", username);
        queryWrapper.like(StrUtil.isBlank(name), "name", name);
//        Page<User> page = new Page<>(pageNum, pageSize);
//
//        userService.page(page, queryWrapper);


        Page<User> page = userService.page(new Page<>(pageNum, pageSize),queryWrapper);
        return Result.success(page);
    }



}
