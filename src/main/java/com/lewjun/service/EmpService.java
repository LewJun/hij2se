package com.lewjun.service;

import com.lewjun.bean.Emp;

/**
 * @author LewJun
 * @version $Id: EmpService.java, v 0.1 2017年4月13日 上午10:33:18 LewJun Exp $
 */
public interface EmpService {
    void insert(Emp emp);

    int deleteByPrimaryKey(Integer empno);

    int insertSelective(Emp record);

    Emp selectByPrimaryKey(Integer empno);

    int updateByPrimaryKeySelective(Emp record);

    int updateByPrimaryKey(Emp record);
}
