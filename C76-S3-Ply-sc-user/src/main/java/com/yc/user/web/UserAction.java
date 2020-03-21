package com.yc.user.web;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAction {
	
	@GetMapping("user/way")
	public String user(HttpServletRequest req,HttpServletResponse resp) {
		//遍历所有头域
		Enumeration<String> enus = req.getHeaderNames();
		while(enus.hasMoreElements()) {
			String name = enus.nextElement();
			System.out.println(name + "=" + req.getHeader(name));
		}
		//添加cookie数据
		Cookie cookie = new Cookie("testcookie","123456");
		resp.addCookie(cookie);
		
		return "user" + req.getServerPort();
	}

}
