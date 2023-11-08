package com.example.springbootbootdemo.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 功能:
 * 作者:晨兴
 * 日期: 2023/10/23 14:51
 */
@Getter
@Setter
public class ServiceException extends RuntimeException {

    String code;

    public ServiceException(String msg) {
        super(msg);
        this.code = "500";
    }

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }
}
