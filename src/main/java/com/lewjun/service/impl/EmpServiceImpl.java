/**
 * Sunnysoft.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.lewjun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lewjun.bean.Emp;
import com.lewjun.dao.EmpDao;
import com.lewjun.service.EmpService;

/**
 * 
 * @author LewJun
 * @version $Id: EmpServiceImpl.java, v 0.1 2017年4月13日 上午10:51:11 LewJun Exp $
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmpDao empDao;

    /** 
     * @see com.lewjun.service.EmpService#save(com.lewjun.bean.Emp)
     */
    @Override
    public void save(Emp emp) {
        System.out.println("save");
        empDao.save(emp);
    }

}
