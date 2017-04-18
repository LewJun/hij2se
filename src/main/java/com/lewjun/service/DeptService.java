/**
 * Sunnysoft.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.lewjun.service;

import java.io.Serializable;
import java.util.List;

import com.lewjun.bean.Dept;

/**
 * 
 * @author LewJun
 * @version $Id: DeptService.java, v 0.1 2017年4月18日 下午3:51:11 LewJun Exp $
 */
public interface DeptService {

    /**
     * 根据部门编号得到该部门及其部门下的成员
     * 
     * @param deptno
     */
    public List<Dept> selectDeptWithEmpsByPrimaryKey(Serializable deptno);

    public Dept selectByPrimaryKey(Serializable deptno);
}
