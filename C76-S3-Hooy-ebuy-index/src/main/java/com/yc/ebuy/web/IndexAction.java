package com.yc.ebuy.web;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.yc.ebuy.bean.EasybuyUser;
import com.yc.ebuy.biz.BizExcepiton;
import com.yc.ebuy.biz.UserBiz;

@RestController
@SessionAttributes("loginedUser")
public class IndexAction {
	
	@Resource
	private IEbuyBackAction pca;

	@Resource
	UserBiz ubiz;
	
	/* 加载动态数据的方法一
	 * @ModelAttribute
	 * public void init() {
	 * 
	 * }
	 */

	@GetMapping({ "/", "index", "index.html" })
	public ModelAndView index(ModelAndView mav) {
		
		/*
		 * 通过远程服务调用方式获取分类信息
		 */
		mav.addObject("pclist",pca.getPc());
		mav.addObject("newslist",pca.getNews());
		mav.setViewName("Index");
		return mav;
	}
	
	@GetMapping("tologin")
	public ModelAndView tologin(ModelAndView mav) {
		mav.setViewName("Login");
		return mav;
	}
	
	@PostMapping("login")
	public ModelAndView login(EasybuyUser user,ModelAndView mav) {
		try {
			EasybuyUser dbuser = ubiz.login(user);
			mav.addObject("loginedUser", dbuser);
			//mav.setViewName("Index");
			//加载动态数据的方法二
			return index(mav);
		} catch (BizExcepiton e) {
			e.printStackTrace();
			mav.addObject("msg",e.getMessage());
			mav.setViewName("Login");
		}
		return mav;
		
	}

}