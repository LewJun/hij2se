/**
 * Sunnysoft.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.lewjun.service;

import java.io.Serializable;
import java.util.List;

import com.lewjun.bean.Emp;

/**
 * 
 * @author LewJun
 * @version $Id: EmpService.java, v 0.1 2017年4月13日 上午10:33:18 LewJun Exp $
 */
public interface EmpService {


    /**
     * 根据pk删除
     * 
     * @param pk
     * @return effect rows
     */
    int deleteByPrimaryKey(Serializable pk);

    /**
     * 插入一个对象到数据库，能得到主键值
     * 
     * @param record 插入的对象
     * @return effect rows
     */
    int insert(Emp record);

    /**
     * 批量插入数据，但是不能得到主键值
     * 
     * @param ts 批量插入的对象
     * @return effect rows
     */
    int inserts(List<Emp> ts);

    /**
     * 有选择的插入（插入不为空的字段）
     * 
     * @param record 插入的对象
     * @return effect rows
     */
    int insertSelective(Emp record);

    /**
     * 更新
     * 
     * @param record
     * @return effect rows
     */
    int updateByPrimaryKey(Emp record);
    
    /**
     * 有选择的更新
     * 
     * @param record
     * @return effect rows
     */
    int updateByPrimaryKeySelective(Emp record);

    /**
     * 查询所有
     * 
     * @return Emp
     */
    List<Emp> selectAll();

    /**
     * 根据主键查询数据
     * 
     * @param pk 主键
     * @return Emp
     */
    Emp selectByPrimaryKey(Serializable pk);

    /**
     * 查询员工及其下属员工
     * 
     * @param pk
     * @return
     */
    List<Emp> selectEmpWithSubEmpByPrimaryKey(Serializable pk);
}
