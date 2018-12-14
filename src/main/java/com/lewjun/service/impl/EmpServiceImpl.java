package com.lewjun.service.impl;

import com.lewjun.bean.Emp;
import com.lewjun.mapper.EmpMapper;
import com.lewjun.service.EmpService;
import com.lewjun.utils.Developer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author LewJun
 * @version $Id: EmpServiceImpl.java, v 0.1 2017年4月13日 上午10:51:11 LewJun Exp $
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmpMapper empMapper;

    @Autowired
    Developer developer;

    /**
     * @see com.lewjun.service.EmpService#insert(com.lewjun.bean.Emp)
     */
    @Override
    public void insert(Emp emp) {
        System.out.println("insert");
        empMapper.insert(emp);
        // 模拟产生一个异常
//        int i = 1/0;
    }

    @Override
    public int deleteByPrimaryKey(Integer empno) {
        return empMapper.deleteByPrimaryKey(empno);
    }

    @Override
    public int insertSelective(Emp record) {
        return empMapper.insertSelective(record);
    }

    @Override
    public Emp selectByPrimaryKey(Integer empno) {
        return empMapper.selectByPrimaryKey(empno);
    }

    @Override
    public int updateByPrimaryKeySelective(Emp record) {
        return empMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Emp record) {
        return empMapper.updateByPrimaryKey(record);
    }

}
