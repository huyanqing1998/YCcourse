package com.yc.blog.web;


import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yc.blog.bean.Article;
import com.yc.blog.bean.ArticleExample;
import com.yc.blog.bean.User;
import com.yc.blog.biz.BizException;
import com.yc.blog.biz.UserBiz;
import com.yc.blog.dao.ArticleMapper;
import com.yc.blog.dao.CategoryMapper;
import com.yc.blog.vo.Result;

@Controller
public class IndexAction {
	
	@Resource
	private ArticleMapper am;
	
	@Resource
	private CategoryMapper cm;
	
	@Resource
	private UserBiz ubiz;
	
	@ModelAttribute
	public void init(Model m) {
		//查询分类列表
		m.addAttribute("clist",cm.selectByExample(null));
	}
	
	/*
	 * 首页
	 */
	@GetMapping({"/","index","index.html"})
	public String index(@RequestParam(defaultValue = "1") Integer page,Model m) {
		Page<Article> pg = PageHelper.startPage(page, 5);
		//注意：PageHelper.startPage(page, 5)必须在查询代码的前一行
		am.selectByExampleWithBLOBs(null);
		m.addAttribute("alist",pg);
		return "index";
	}
	
	/*
	 * 文章详情
	 */
	@GetMapping({"article"})
	public String article(Integer id,Model m) {
		Article a = am.selectByPrimaryKey(id);
		m.addAttribute(a);
		return "article";
	}
	
	/*
	 * 分类查询
	 */
	@GetMapping({"category"})
	public String category(Integer id,@RequestParam(defaultValue = "1") Integer page,Model m) {
		Page<Article> pg = PageHelper.startPage(page, 5);
		ArticleExample ae = new ArticleExample();
		ae.createCriteria().andCategoryidEqualTo(id);
		am.selectByExampleWithBLOBs(ae);
		m.addAttribute("alist",pg);
		m.addAttribute("id",id);
		return "category";
	}
	
	@GetMapping({"toreg"})
	public String toreg() {
		return "reg";
	}
	
	/*
	 * AJAX 方法使用@ResponseBody返回字符串
	 */
	@Value("${myUploadPath}")
	private String myUploadPath;
	@ResponseBody
	@PostMapping({"reg"})
	public Result reg(@Valid User user,Errors errors,@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		if(errors.hasFieldErrors()) {
			//不符合注册要求，比如密码长度或短或长
			return new Result(1,"用户注册失败！",errors.getFieldError());
		}
		file.transferTo(new File(myUploadPath + file.getOriginalFilename()));
		try {
			//让上传的头像的路径存入数据库
			String head = "/" + file.getOriginalFilename();
			user.setHead(head);
			ubiz.reg(user);
			return new Result(0,"用户注册成功！");
		} catch (BizException e) {
			e.printStackTrace();
			errors.rejectValue("name", "100",e.getMessage());
			return new Result(1,"用户注册失败！",errors.getFieldError());
		}
		
	}
	
	

}
