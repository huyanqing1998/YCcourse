package com.yc.springmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
 * @RestController 注解的类内部方法返回的是字符串
 * @Controller	        注解的类内部方法返回的是页面名(jsp/html)
 */
//@RestController
@Controller
public class IndexAction {
	
	//@RequestMapping("默认参数value==path")
	
	@RequestMapping("hello.s")
	public String hello() {
		return "hello";
	}
	
	/*
	 * 带目录的地址(虚拟地址)
	 */
	@RequestMapping("/page/hello1")
	public String hello1() {
		return "hello";
	}
	
	/*
	 * 多个地址
	 */
	@RequestMapping({"abc","efg"})
	public String hello2() {
		return "hello";
	}
	
	/*
	 * method 限定HTTP方法类型
	 */
	@RequestMapping(path="hello3",method=RequestMethod.GET)
	public String hello3() {
		return "hello";
	}
	
	@GetMapping("hello4")
	public String hello4() {
		return "hello";
	}
	
	//相当于@RequestMapping(path="hello5",method=RequestMethod.GET)
	@PostMapping("hello5")
	public String hello5() {
		return "hello";
	}

}
