package com.yc.springmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * @RequestMapping("")加在类上表示，在该类下所有的方法的映射地址，共同的虚拟目录
 */
@RequestMapping("user")
@Controller
public class HelloAction {	
	@GetMapping("hello")
	public String hello6() {
		return "hello";
	}
	@GetMapping("hello1")
	public String hello7() {
		return "hello";
	}
	@GetMapping("hello2")
	public String hello8() {
		return "hello";
	}
	
	/*
	 * params限定请求必须带的参数，否则页面报错
	 * path==value
	 */
	@RequestMapping(value="show",params="user")
	public String hello9() {
		return "hello";
	}
	@RequestMapping(value="show1",params="user==root")
	public String hello10() {
		return "hello";
	}

}
