package com.lewjun.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author LewJun
 * @version $Id: Developer.java, v 0.1 2017年4月13日 下午1:36:48 LewJun Exp $
 */
@Component
public class Developer {

    @Value("${developer.name}")
    private String name;

    @Value("${developer.age}")
    private Integer age;

    @Value("${developer.birthday}")
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Developer [name=" + name + ", age=" + age + ", birthday=" + birthday + "]";
    }

}
