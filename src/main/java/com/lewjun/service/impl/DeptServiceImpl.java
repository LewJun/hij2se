/**
 * Sunnysoft.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.lewjun.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lewjun.bean.Dept;
import com.lewjun.mapper.DeptMapper;
import com.lewjun.service.DeptService;

/**
 * 
 * @author LewJun
 * @version $Id: DeptServiceImpl.java, v 0.1 2017年4月18日 下午3:52:23 LewJun Exp $
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    /** 
     * @see com.lewjun.service.DeptService#selectDeptWithEmpsByPrimaryKey(java.io.Serializable)
     */
    @Override
    public List<Dept> selectDeptWithEmpsByPrimaryKey(Serializable pk) {
        return deptMapper.selectDeptWithEmpsByPrimaryKey(pk);
    }
    /** 
     * @see com.lewjun.service.DeptService#selectByPrimaryKey(java.io.Serializable)
     */
    @Override
    public Dept selectByPrimaryKey(Serializable pk) {
        return deptMapper.selectByPrimaryKey(pk);
    }

}
