package com.lewjun.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 * @author LewJun
 */
@Component
@Aspect
@Order(Integer.MIN_VALUE) // order 越小越先被执行
public class ApplicationBaseAspect {
	public static final Logger LOGGER = LoggerFactory.getLogger(ApplicationBaseAspect.class);

	// 拦截到所有的操作
	@Around("execution(* com.lewjun.service.*Service.*(..))")
	public Object aspect(ProceedingJoinPoint jp) throws Throwable {
		Signature signature = jp.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		StringBuilder sb = new StringBuilder("");
		sb.append(method.getReturnType().getName()).append(" ").append(method.getDeclaringClass().getName()).append(".")
				.append(method.getName());
		Object[] args = jp.getArgs();
		if (args != null && args.length > 0) {
			sb.append("(");
			for (Object arg : args) {
				sb.append(arg.getClass().getName()).append(", ");
			}
			sb.append(")");
			sb.deleteCharAt(sb.lastIndexOf(", "));
		}
		LOGGER.info("【 方法 {} 正被拦截】", sb.toString());
		// 方法 void com.lewjun.service.EmpService.insert(com.lewjun.bean.Emp ) 正被拦截
		Object object = jp.proceed();
		LOGGER.info("【返回值={}】", object);
		return object;
	}
}
