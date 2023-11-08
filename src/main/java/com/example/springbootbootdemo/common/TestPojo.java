package com.example.springbootbootdemo.common;

/**
 * 功能:
 * 作者:晨兴
 * 日期: 2023/10/11 21:11
 */
public class TestPojo {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestPojo(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
