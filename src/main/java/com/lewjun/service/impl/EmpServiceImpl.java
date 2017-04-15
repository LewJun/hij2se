/**
 * Sunnysoft.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.lewjun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lewjun.bean.Emp;
import com.lewjun.mapper.EmpMapper;
import com.lewjun.service.EmpService;
import com.lewjun.utils.Developer;

/**
 * 
 * @author LewJun
 * @version $Id: EmpServiceImpl.java, v 0.1 2017年4月13日 上午10:51:11 LewJun Exp $
 */
@Service
@Transactional
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmpMapper empMapper;
    
    @Autowired
    Developer developer;

    /** 
     * @see com.lewjun.service.EmpService#save(com.lewjun.bean.Emp)
     */
    @Override
    public void insert(Emp emp) {
        System.out.println("insert");
        empMapper.insert(emp);
        // 模拟产生一个异常
//        int i = 1/0;
    }

}
