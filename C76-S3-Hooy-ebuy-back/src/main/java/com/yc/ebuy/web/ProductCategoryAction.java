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
public class ProductCategoryAction {
	
	@Resource
	private EasybuyProductCategoryMapper pcm;
	
	@GetMapping("getPc")
	public List<EasybuyProductCategory> getPc(){
		EasybuyProductCategoryExample pce = new EasybuyProductCategoryExample();
		pce.createCriteria().andTypeEqualTo(1);
		List<EasybuyProductCategory> list = pcm.selectByExample(pce);
		return list;
	}
	
	@GetMapping("test")
	public String test() {
		return "back test";
	}

}
