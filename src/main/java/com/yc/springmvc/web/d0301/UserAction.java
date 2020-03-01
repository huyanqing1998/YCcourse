package com.yc.springmvc.web.d0301;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.yc.springmvc.bean.Subject;
import com.yc.springmvc.bean.User;

@Controller("UserAction0301")
/* @SessionAttributes("")
 * 加在类上
 * 用于监控 Model中数据，如果出现指定名称或类型的数据，那么就将该数据添加到会话中去
 */
@SessionAttributes("loginedUser")
public class UserAction {
	
	/* @ModelAttribute
	 * 1.加在方法上
	 * 在所有控制器方法之前执行
	 * 该方法还可以添加 Model对象
	 */
	@ModelAttribute
	public String init(Model model) {
		System.out.println("============");
		User user = new User();
		user.setUsername("hooy");
		user.setPassword("123");
		model.addAttribute("user",user);
		
		String[] likeItems = {"篮球","跳舞","唱歌","看书","手游"};
		List<String> eduItems = Arrays.asList(new String[] {"初中","高中","专科","本科"});
		List<Subject> subjectItems = new ArrayList<>(); 
		
		subjectItems.add(new Subject(1,"计算机网络"));
		subjectItems.add(new Subject(2,"计算机应用计算"));
		subjectItems.add(new Subject(3,"财务管理"));
		subjectItems.add(new Subject(4,"市场营销"));
		subjectItems.add(new Subject(5,"企业管理"));
		
		model.addAttribute("likeItems",likeItems);
		model.addAttribute("eduItems",eduItems);
		model.addAttribute("subjectItems",subjectItems);
		/*
		 * 该方法返回值自动添加到model中 没有指定model名，那么数据的类型就是名字
		 * 类名首字母小写：String -> string / User -> user
		 */
		return "你好";
	}
	
	/* @ModelAttribute("")
	 * 2.加在参数上
	 * 从model里寻找一个对象名为user的对象，然后传递给形参变量
	 */
	@GetMapping("tosign")
	public String tologin(@ModelAttribute("user") User user) {
		System.out.println(user);
		return "login";
	}
	
	//ModelAndView
	@GetMapping("sign")
	public ModelAndView login(User user) {
		/*
		 * 响应重定向:
		 *		使用 "视图名" 方式默认的是请求转发方式
		 *		响应重定向 使用 "redirect: 地址" 跳转
		 */
		ModelAndView mav = new ModelAndView("redirect:tosuccess");
		if("yc".equals(user.getUsername()) && "123".equals(user.getPassword())) {
			user.setEmail("123123123@qq.com");
			user.setPhone("1313501111");
			mav.addObject("loginedUser",user);
			return mav;
		} else {
			mav.addObject("msg", "用户名或密码错误");
			mav.setViewName("login");
			return mav;
		}
	}
	
	/* @SessionAttribute
	 * 加在方法参数上
	 * 用于将会话中的数据取出来, 注入到方法参数中
	 */
	@GetMapping("tosuccess")//reuired=false 设置为false，如果session里没有的对象，也能正常访问页面。否则，报错。
	public String toSuccess(@SessionAttribute(required=false,value="loginedUser") User user) {
		System.out.println("==========toSuccess==========");
		System.out.println(user);
		System.out.println("==========toSuccess==========");
		return "success";
	}
		
	@PostMapping(path="reg",produces="text/html;charset=utf-8")
	@ResponseBody()
	public String reg(User user,@RequestBody String reqBody) {
		System.out.println(reqBody);
		return "成功！";
	}
	
	//--------------------------------------------------------------------------------------------
	
	@GetMapping("toreg")
	public String toreg(Model model,@ModelAttribute("user") User user) {
		user.setLikes(new String[] {"跳舞","手游"});
		user.setEdu("本科");
		user.setSubject("3");

		return "reg";
	}
	
	@GetMapping("reg")
	public String reg(@Valid User user,Errors error) {
		if(error.hasFieldErrors()) {
			return "reg";
		} else {
			return "success";
		}

	}

}
