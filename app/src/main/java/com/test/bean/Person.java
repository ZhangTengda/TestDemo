package com.test.bean;

import java.io.Serializable;

/**
 * Created by roger on 2018/4/28.
 */

public class Person implements Serializable{
    private static final long serialVersionUID = 0L;

    private String name;

    private int age;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
