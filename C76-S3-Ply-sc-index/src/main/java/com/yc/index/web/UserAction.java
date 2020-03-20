package com.yc.index.web;

import org.springframework.stereotype.Component;

@Component
public class UserAction implements IUserAction{

	@Override
	public String user() {
		return "声明式熔断/降级!";
	}
	
}
