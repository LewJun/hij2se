/**
 * Sunnysoft.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.lewjun.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lewjun.bean.Emp;
import com.lewjun.mapper.EmpMapper;
import com.lewjun.service.EmpService;

/**
 * 
 * @author LewJun
 * @version $Id: EmpServiceImpl.java, v 0.1 2017年4月13日 上午10:51:11 LewJun Exp $
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmpMapper empMapper;

    /** 
     * @see com.lewjun.service.EmpService#save(com.lewjun.bean.Emp)
     */
    @Override
    public int insert(Emp emp) {
        return empMapper.insert(emp);
    }

    /** 
     * @see com.lewjun.service.EmpService#deleteByPrimaryKey(java.io.Serializable)
     */
    @Override
    public int deleteByPrimaryKey(Serializable pk) {
        return empMapper.deleteByPrimaryKey(pk);
    }

    /** 
     * @see com.lewjun.service.EmpService#inserts(java.util.List)
     */
    @Override
    public int inserts(List<Emp> ts) {
        return empMapper.inserts(ts);
    }

    /** 
     * @see com.lewjun.service.EmpService#insertSelective(com.lewjun.bean.Emp)
     */
    @Override
    public int insertSelective(Emp record) {
        return empMapper.insertSelective(record);
    }

    /** 
     * @see com.lewjun.service.EmpService#selectByPrimaryKey(java.io.Serializable)
     */
    @Override
    public Emp selectByPrimaryKey(Serializable pk) {
        return empMapper.selectByPrimaryKey(pk);
    }

    /** 
     * @see com.lewjun.service.EmpService#selectAll()
     */
    @Override
    public List<Emp> selectAll() {
        return empMapper.selectAll();
    }

    /** 
     * @see com.lewjun.service.EmpService#updateByPrimaryKey(com.lewjun.bean.Emp)
     */
    @Override
    public int updateByPrimaryKey(Emp record) {
        return empMapper.updateByPrimaryKey(record);
    }

    /** 
     * @see com.lewjun.service.EmpService#updateByPrimaryKeySelective(com.lewjun.bean.Emp)
     */
    @Override
    public int updateByPrimaryKeySelective(Emp record) {
        return empMapper.updateByPrimaryKeySelective(record);
    }

}
