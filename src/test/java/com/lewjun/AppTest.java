package com.lewjun;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lewjun.bean.Emp;
import com.lewjun.service.EmpService;

/**
 * Unit test for simple App.
 *
 * @author LewJun
 */
public class AppTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);

    @Test
	public void testGreet() {
		LOGGER.info("info");
		LOGGER.warn("warn");
		LOGGER.debug("debug");
		LOGGER.error("error");
	}

	ApplicationContext context = null;
	@Before
    public void initSpringContext() {
        context = new ClassPathXmlApplicationContext("classpath:spring/spring.xml");
        LOGGER.info("context={}", context);
    }

    @Test
    public void testGetBean() {
        Emp emp = context.getBean(Emp.class);
        LOGGER.info("emp={}", emp);
    }

    @Test
    public void testAutowired() {
        EmpService empService = context.getBean(EmpService.class);
        LOGGER.info("{}", empService);

        empService.save(new Emp());
    }
}
