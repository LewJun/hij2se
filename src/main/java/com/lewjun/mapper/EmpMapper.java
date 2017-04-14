/**
 * Sunnysoft.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.lewjun.mapper;

import org.springframework.stereotype.Repository;

import com.lewjun.bean.Emp;

/**
 * 
 * @author LewJun
 * @version $Id: EmpMapper.java, v 0.1 2017年4月13日 上午11:30:11 LewJun Exp $
 */
@Repository
public interface EmpMapper {
    public void insert(Emp emp);
}
