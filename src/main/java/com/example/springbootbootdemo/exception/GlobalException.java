package com.example.springbootbootdemo.exception;

import com.example.springbootbootdemo.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.rmi.ServerException;

/**
 * 功能:
 * 作者:晨兴
 * 日期: 2023/10/23 14:44
 */

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result serviceException(ServiceException e) {
        return Result.error(e.getCode(),e.getMessage());
    }
}
