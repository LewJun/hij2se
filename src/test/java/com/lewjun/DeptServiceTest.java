/**
 * Sunnysoft.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.lewjun;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.lewjun.bean.Dept;
import com.lewjun.service.DeptService;

/**
 * 
 * @author LewJun
 * @version $Id: DeptServiceTest.java, v 0.1 2017年4月18日 下午3:53:31 LewJun Exp $
 */
public class DeptServiceTest extends SpringJunitTest {
    @Autowired
    private DeptService deptService;

    @Test
    public void test_selectDeptWithEmpsByPrimaryKey() {
        List<Dept> deptList = deptService.selectDeptWithEmpsByPrimaryKey(40);
        LOGGER.info("【deptList={}】", deptList);
        LOGGER.info("【deptList JSON ={}】", JSON.toJSONString(deptList));
    }
}
