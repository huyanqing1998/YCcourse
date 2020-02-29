package com.yc.springmvc.web.d0229;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.springmvc.bean.Hall;

@RestController
public class ParamAction {
	
	/*
	 * 参数映射
	 * 请求参数名必须和方法参数名一致
	 * 参数类型必须兼容
	 * 1.请求参数  /..?param
	 * 如果名称不同@RequestParam
	 * 2.地址参数  /param/param
	 * spring支持地址参数@PathVariable
	 */
	
	@GetMapping("login")
	public String login(String user,String pwd) {
		return "user = " + user + " pwd = " + pwd;
	}
	
	@GetMapping(path="login1",params="username")
	public String login1(@RequestParam("username")String user,String pwd) {
		return "user = " + user + " pwd = " + pwd;
	}
	
	@GetMapping("login2/{username}/{password}")
	public String login2(@PathVariable("username")String user,@PathVariable("password")String pwd) {
		return "user = " + user + " pwd = " + pwd;
	}
	
	/*
	 * 高级参数映射
	 * 自动装箱 请求参数名与对象属性名一致
	 * 例如：Integer i = new Integer(100) -自动装箱-> Integer i = 100;
	 */
	
	@PostMapping("saveHall")
	public String saveHall(Hall hall) {
		hall.setCapacity(10000);
		hall.setCinemaId(2222);
		return hall.toString();
	}
	
	/*
	 * 获取Cookie值
	 */
	@GetMapping("cookie")
	public String cookie(@CookieValue("JSESSIONID") String sessionid) {
		return "JSESSIONID : " + sessionid;
	}
	
	/*
	 * 获取请求头
	 */
	@GetMapping("header")
	public String cookie(@RequestHeader("Host") String host,@RequestHeader("Accept") String accpet) {
		return "Host : " + host + "Accept : " + accpet;
	}
	
	/*
	 * 获取原生Servlet对象 -> 请求 响应 会话
	 */
	@GetMapping("servlet")
	public String servlet(HttpServletRequest req,HttpServletResponse resp,HttpSession session) {
		return "Request : " + req + "<br/>======================<br/>" +
			   "Response : " + resp + "<br/>======================<br/>" +
			   "Session : " + session;
	}

}
