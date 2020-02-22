package com.yc.spring.bank.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;

public class LogAdvice {
	
	public void before(JoinPoint jp) {
		System.out.println("方法签名" + jp.getSignature());
		System.out.println("方法参数列表" + Arrays.toString(jp.getArgs()));
		System.out.println("=====before=====");
	}

}
