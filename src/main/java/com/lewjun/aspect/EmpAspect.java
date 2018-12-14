package com.lewjun.aspect;

import com.lewjun.bean.Emp;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author LewJun
 */
@Component
@Aspect
@Order(1)
public class EmpAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmpAspect.class);

    @Around(value = "execution(* com.lewjun.service.EmpService.insert(..))")
    // @Around(value = "execution(* com.lewjun.service.impl.EmpServiceImpl.insert(..))")
    public Object insert(ProceedingJoinPoint jp) throws Throwable {
        LOGGER.info("【EmpAspect insert】");
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            if (arg instanceof Emp) {
                Emp emp = (Emp) arg;
                // 修改时间
                emp.setHiredate(new Date());
                LOGGER.info("【emp={}】", emp);
            }
        }
        // 使程序继续往下执行，并接收返回数据
        Object object = jp.proceed();
        LOGGER.info("【object={}】", object);
        return object;
    }
}
