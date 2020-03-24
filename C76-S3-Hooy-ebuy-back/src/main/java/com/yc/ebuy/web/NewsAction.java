package com.yc.ebuy.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yc.ebuy.bean.EasybuyNews;
import com.yc.ebuy.bean.EasybuyNewsExample;
import com.yc.ebuy.bean.EasybuyProductCategory;
import com.yc.ebuy.bean.EasybuyProductCategoryExample;
import com.yc.ebuy.dao.EasybuyNewsMapper;
import com.yc.ebuy.dao.EasybuyProductCategoryMapper;

@RestController
public class NewsAction {
	
	@Resource
	private EasybuyNewsMapper nm;	
	
	@GetMapping("getNews")
	public List<EasybuyNews> getNews(){
		EasybuyNewsExample ne = new EasybuyNewsExample();
		PageHelper.startPage(1, 5);
		ne.setOrderByClause("id desc ");
		List<EasybuyNews> list = nm.selectByExample(ne);
		return list;
	}

}
