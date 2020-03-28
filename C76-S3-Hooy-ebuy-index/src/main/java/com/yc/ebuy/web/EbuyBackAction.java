package com.yc.ebuy.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.yc.ebuy.bean.EasybuyNews;
import com.yc.ebuy.bean.EasybuyProduct;
import com.yc.ebuy.bean.EasybuyProductCategory;

@Component
public class EbuyBackAction implements IEbuyBackAction{

	@Override
	public List<EasybuyProductCategory> getPc() {
		List<EasybuyProductCategory> list = new ArrayList<EasybuyProductCategory>();
		list.add(new EasybuyProductCategory(1,"化妆品"));
		list.add(new EasybuyProductCategory(2,"箱包"));
		list.add(new EasybuyProductCategory(3,"保健食品"));
		list.add(new EasybuyProductCategory(4,"电子商品"));
		list.add(new EasybuyProductCategory(5,"进口食品,生鲜"));
		return list;
	}

	@Override
	public List<EasybuyNews> getNews() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EasybuyProduct> getHotP() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public EasybuyProduct product(int id) {
		return null;
	}
	
	
}
