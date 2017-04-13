/**
 * Sunnysoft.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.lewjun.dao;

import org.springframework.stereotype.Repository;

import com.lewjun.bean.Emp;

/**
 * 
 * @author LewJun
 * @version $Id: EmpDaoImpl.java, v 0.1 2017年4月13日 上午11:30:52 LewJun Exp $
 */
@Repository
public class EmpDaoImpl implements EmpDao {

    /** 
     * @see com.lewjun.dao.EmpDao#save(com.lewjun.bean.Emp)
     */
    @Override
    public void save(Emp emp) {
        System.out.println("save emp dao");
    }

}
