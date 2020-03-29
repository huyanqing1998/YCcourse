package com.yc.ebuy.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.yc.ebuy.bean.EasybuyCart;
import com.yc.ebuy.bean.EasybuyCartExample;
import com.yc.ebuy.bean.EasybuyUser;
import com.yc.ebuy.biz.BizExcepiton;
import com.yc.ebuy.biz.UserBiz;
import com.yc.ebuy.dao.EasybuyCartMapper;

@RestController
@SessionAttributes("loginedUser")
public class IndexAction {

	@Resource
	private IEbuyBackAction pca;

	@Resource
	UserBiz ubiz;

	/*
	 * 加载动态数据的方法一
	 * 
	 * @ModelAttribute public void init() {
	 * 
	 * }
	 */

	@GetMapping({ "/", "index", "index.html" })
	public ModelAndView index(ModelAndView mav) {

		/*
		 * 通过远程服务调用方式获取分类信息
		 */
		mav.addObject("pclist", pca.getPc());
		mav.addObject("hplist", pca.getHotP());
		mav.addObject("newslist", pca.getNews());
		if (mav.getViewName() == null) {
			mav.setViewName("index");
		}
		return mav;
	}

	@GetMapping("tologin")
	public ModelAndView tologin(ModelAndView mav) {
		mav.setViewName("Login");
		return mav;
	}

	@PostMapping("login")
	public ModelAndView login(EasybuyUser user, ModelAndView mav,
			@SessionAttribute(name = "uri", required = false) String uri, HttpSession session) {
		try {
			EasybuyUser dbuser = ubiz.login(user);
			 /* 	屏蔽之前的写法
				// 将用户对象添加到会话
				mav.addObject("loginedUser", dbuser);
				
			 * 	响应重定向添加会话属性, 使用
			 * 		mav.addObject("loginedUser", dbuser);
			 * 	会出现会话属性添加失败的问题, 所以改成下面的写法
			 */
			session.setAttribute("loginedUser", dbuser);
			if (uri != null) {
				// 这是拦截登录的情况
				mav.setViewName("redirect:http://127.0.0.1" + uri);
			} else {
				// 这是用户的主动登录
				mav.setViewName("index");
			}
			// 加载动态数据的方法二
			return index(mav);
		} catch (BizExcepiton e) {
			e.printStackTrace();
			mav.addObject("msg", e.getMessage());
			mav.setViewName("Login");
		}
		return mav;

	}

	@GetMapping("product")
	public ModelAndView product(int id, ModelAndView mav) {
		// 要展示的商品
		mav.addObject("product", pca.product(id));
		// 商品分类
		mav.addObject("pclist", pca.getPc());
		mav.setViewName("product");
		return mav;
	}

	@GetMapping("addCart")
	public ModelAndView addCart(EasybuyCart cart,ModelAndView mav,
			@SessionAttribute("loginedUser") EasybuyUser user) {
		ubiz.addCart(user, cart);
		return toCart(mav,user);
	}
	
	@Resource
	private EasybuyCartMapper ecm;

	@GetMapping("toCart")
	private ModelAndView toCart(ModelAndView mav,@SessionAttribute("loginedUser") EasybuyUser user) {
		EasybuyCartExample ece = new EasybuyCartExample();
		ece.createCriteria().andUidEqualTo(user.getId());
		mav.addObject("clist",ecm.selectByExample(ece));
		mav.setViewName("BuyCar");
		return mav;
	}

}