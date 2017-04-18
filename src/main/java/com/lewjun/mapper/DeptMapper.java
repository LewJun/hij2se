/**
 * Sunnysoft.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.lewjun.mapper;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lewjun.bean.Dept;

/**
 * 
 * @author LewJun
 * @version $Id: DeptMapper.java, v 0.1 2017年4月18日 下午3:41:01 LewJun Exp $
 */
@Repository
public interface DeptMapper {
    /**
     * 根据部门编号得到该部门及其部门下的成员
     * 
     * @param pk
     */
    public List<Dept> selectDeptWithEmpsByPrimaryKey(Serializable pk);
    
    public Dept selectByPrimaryKey(Serializable pk);
}
