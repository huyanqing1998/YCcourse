package com.yc.springmvc.web.d0229;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yc.springmvc.bean.User;

@Controller
public class UserAction {
	
	@GetMapping("tologin")
	public String tologin() {
		return "login";
	}
	
	/*
	 * 实现数据推送的对象：
	 * 1.Model
	 * 2.ModelAndView	Model + View
	 * 3.Map
	 * 
	 */
	
	//Model
	/*@GetMapping("signin")
	public String login(User user,Model model) {
		if("yc".equals(user.getUsername()) && "123".equals(user.getPassword())) {
			user.setEmail("123123123@qq.com");
			user.setPhone("1313501111");
			model.addAttribute("user",user);
			return "success";
		} else {
			model.addAttribute("msg", "用户名或密码错误");
			return "login";
		}
	}*/
	
	//ModelAndView
	/*@GetMapping("signin")
	public ModelAndView login(User user) {
		ModelAndView mav = new ModelAndView("success");
		if("yc".equals(user.getUsername()) && "123".equals(user.getPassword())) {
			user.setEmail("123123123@qq.com");
			user.setPhone("1313501111");
			mav.addObject("user",user);
			return mav;
		} else {
			mav.addObject("msg", "用户名或密码错误");
			mav.setViewName("login");
			return mav;
		}
	}*/
	
	//Map
	@GetMapping("signin")
	public String login(User user,Map<String,Object> map) {
		if("yc".equals(user.getUsername()) && "123".equals(user.getPassword())) {
			user.setEmail("123123123@qq.com");
			user.setPhone("1313501111");
			map.put("user",user);
			return "success";
		} else {
			map.put("msg", "用户名或密码错误");
			return "login";
		}
	}

}
