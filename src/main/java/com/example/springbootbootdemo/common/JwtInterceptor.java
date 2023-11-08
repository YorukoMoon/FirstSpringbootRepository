package com.example.springbootbootdemo.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.springbootbootdemo.entity.User;
import com.example.springbootbootdemo.exception.ServiceException;
import com.example.springbootbootdemo.mapper.UserMapper;
import com.example.springbootbootdemo.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 功能:
 * 作者:晨兴
 * 日期: 2023/10/25 11:41
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //看是否有非拦截注解AuthAccess，有的话直接放行
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 获取方法上的@IgnoreJwtInterceptor注解
        AuthAccess AuthAccess = handlerMethod.getMethodAnnotation(AuthAccess.class);
        // 如果方法上存在@IgnoreJwtInterceptor注解，放行
        if (AuthAccess != null) {
            return true;
        }


        //没有非拦截注解，要验证token
        String token = request.getHeader("token");

        if (StrUtil.isBlank(token))
            token = request.getParameter("token");

        //没有token，请登录
        if (StrUtil.isBlank(token)) {
            throw new ServiceException("401", "请登录");
        }
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e) {
            throw new ServiceException("401", "请登录");
        }
        //根据token中的userid去查密码对不对
        User user = userService.getById(Integer.valueOf(userId));
        if (user == null)
            throw new ServiceException("401", "请登录");

        //创建密码验证器去验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new ServiceException("401", "请登录");
        }
        return true;


    }
}

