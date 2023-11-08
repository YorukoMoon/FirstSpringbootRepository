package com.example.springbootbootdemo.entity;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springbootbootdemo.mapper.UserMapper;
import com.example.springbootbootdemo.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

/**
 * 功能:
 * 作者:晨兴
 * 日期: 2023/10/25 15:18
 */
@Component
public class TokenUtils {


    public static UserService staticUserService;
    @Resource
    UserMapper userMapper;
    @Resource
    UserService userService;

        @PostConstruct
     public void setUserService() {
        staticUserService = userService;
    }

    public static String createToken(String userId, String sign) {
        String token = JWT.create()
                .withAudience(userId) //userid作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) //过期时间为当前时间后退2h
                .sign(Algorithm.HMAC256(sign));//pass作为token的密钥
        return token;

    }

    public static User getCurrentUser(){
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)) {
                String userId =JWT.decode(token).getAudience().get(0);
                return staticUserService.getById(Integer.valueOf(userId));
            }
        } catch (Exception  e) {
            return null;
        }

        return null;
    }






}
