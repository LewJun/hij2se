package com.lewjun;

import com.lewjun.bean.Emp;
import com.lewjun.mapper.EmpJpaRepo;
import com.lewjun.service.EmpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author LewJun
 * @version $Id: SpringJunitTest.java, v 0.1 2017年4月13日 下午2:26:50 LewJun Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
//配置了@ContextConfiguration注解并使用该注解的locations属性指明spring和配置文件之后，
@ContextConfiguration(locations = {"classpath:spring/spring.xml"})
public class SpringJunitTest {
    @Autowired
    private EmpService empService;

    @Autowired
    private EmpJpaRepo empJpaRepo;

    @Test
    public void testEmpServiceInsert() {
        empService.insert(new Emp());
    }

    @Test
    public void testCache() throws InterruptedException {
        Integer empno = 5;
        empService.selectByPrimaryKey(empno);
        empService.selectByPrimaryKey(empno);
        empService.selectByPrimaryKey(empno);
        empService.insert(new Emp());
        empService.selectByPrimaryKey(empno);
        empService.selectByPrimaryKey(empno);
        empService.selectByPrimaryKey(empno);
        // 测试过了1分钟是否会刷新缓存，前提是flushInterval=60*1000
        Thread.sleep((long) (62 * 1000));
        empService.selectByPrimaryKey(empno);
        empService.selectByPrimaryKey(empno);
    }

    @Test
    public void testEmpJpaRepo() {
        Emp emp = new Emp();
        empJpaRepo.save(emp);
        System.out.println(emp);
        empJpaRepo.delete(3);
    }
}
