package com.mindtree.mystayapp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class MyStayAppAspect {

	@Before("execution(* com.mindtree.mystayapp.service.impl.*(..)")
	public void before(JoinPoint joinPoint) {
		System.out.print("Befor : ");
		System.out.println(joinPoint.getSignature().getName());
	}
}
