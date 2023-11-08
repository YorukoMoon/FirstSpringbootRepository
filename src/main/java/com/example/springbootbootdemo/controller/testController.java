package com.example.springbootbootdemo.controller;

import com.example.springbootbootdemo.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

@RequestMapping
    public Result test() {
        return Result.success("helloWorld");



    }
    }



