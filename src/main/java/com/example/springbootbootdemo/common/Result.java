package com.example.springbootbootdemo.common;

import com.example.springbootbootdemo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能:
 * 作者:晨兴
 * 日期: 2023/10/11 21:01
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder




public class Result {

    private static final String SUCCESS_CODE = "200";
    private static final String CODE_AUTH_ERROR = "401";
    private static final String CODE_SYS_ERROR = "500";

    private String code;
    private String msg;
    private Object data;





    public static Result success() {
        return Result.builder().code(SUCCESS_CODE).msg("请求成功").build();
    }
    public static Result success(Object data) {
        return new Result(SUCCESS_CODE, "请求成功", data);
    }
    public static Result error(String msg) {
        return new Result(CODE_SYS_ERROR,msg,null);
    }
    public static Result error(String code,String msg) {
        return new Result(code,msg,null);
    }
    public static Result error() {
        return new Result(CODE_SYS_ERROR,"系统错误",null);
    }

}
