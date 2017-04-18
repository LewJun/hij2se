/**
 * Sunnysoft.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.lewjun;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lewjun.bean.Emp;
import com.lewjun.service.EmpService;

/**
 * 
 * @author LewJun
 * @version $Id: EmpServiceTest.java, v 0.1 2017年4月18日 下午1:50:07 LewJun Exp $
 */
public class EmpServiceTest extends SpringJunitTest {
    @Autowired
    private EmpService empService;

    @Test
    public void test_insert() {
        Emp emp = new Emp();
        emp.setDeptno(20);
        emp.setEname("LewJun");
        int effectRows = empService.insert(emp);
        LOGGER.info("【emp={}】", emp);
        LOGGER.info("【effectRows={}】", effectRows);
    }

    @Test
    public void test_inserts() {
        List<Emp> ts = new ArrayList<Emp>();
        Emp emp = new Emp();
        emp.setDeptno(20);
        emp.setEname("LewJun");
        ts.add(emp);

        emp = new Emp();
        emp.setDeptno(22);
        emp.setEname("liujun");
        ts.add(emp);
        empService.inserts(ts);
    }

    @Test
    public void test_insertSelective() {
        Emp emp = new Emp();
        emp.setDeptno(20);
        emp.setEname("LewJun");
        emp.setHiredate(new Date());
        emp.setMgr(10);
        empService.insert(emp);
        int effectRows = empService.insertSelective(emp);
        LOGGER.info("【effectRows={}】", effectRows);
    }

    @Test
    public void test_selectByPrimaryKey() {
        int pk = 7941;
        Emp emp = empService.selectByPrimaryKey(pk);
        LOGGER.info("【emp={}】", emp);
    }

    @Test
    public void test_selectAll() {
        List<Emp> empList = empService.selectAll();
        LOGGER.info("【empList={}】", empList);
    }

    @Test
    public void test_updateByPrimaryKey() {
        Emp emp = new Emp();
        emp.setEmpno(7941);
        emp.setDeptno(20);
        emp.setEname("军哥");
        int effectRows = empService.updateByPrimaryKey(emp);
        LOGGER.info("【emp={}】", emp);
        LOGGER.info("【effectRows={}】", effectRows);
    }

    @Test
    public void test_updateByPrimaryKeySelective() {
        Emp emp = new Emp();
        emp.setEmpno(7941);
        emp.setEname("军哥哥");
        int effectRows = empService.updateByPrimaryKeySelective(emp);
        LOGGER.info("【emp={}】", emp);
        LOGGER.info("【effectRows={}】", effectRows);
    }

    @Test
    public void test_deleteByPrimaryKey() {
        Integer id = 7940;
        int effectRows = empService.deleteByPrimaryKey(id);
        LOGGER.info("【effectRows={}】", effectRows);
    }

}
