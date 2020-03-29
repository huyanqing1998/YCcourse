package com.yc.ebuy.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.ebuy.bean.EasybuyCart;
import com.yc.ebuy.bean.EasybuyUser;
import com.yc.ebuy.bean.EasybuyUserExample;
import com.yc.ebuy.dao.EasybuyCartMapper;
import com.yc.ebuy.dao.EasybuyUserMapper;

@Service
public class UserBiz {
	
	@Resource
	EasybuyUserMapper eum;
	
	@Resource
	EasybuyCartMapper ecm;
	
	public EasybuyUser login(EasybuyUser user) throws BizExcepiton {
		EasybuyUserExample eue = new EasybuyUserExample();
		eue.createCriteria().andLoginnameEqualTo(user.getLoginname()).andPasswordEqualTo(user.getPassword());
		List<EasybuyUser> list = eum.selectByExample(eue);
		if(list.size() != 1) {
			throw new BizExcepiton("用户名或密码错误！");
		}
		return list.get(0);
	}
	
	public void addCart(EasybuyUser user,EasybuyCart cart) {
		cart.setUid(user.getId());
		System.out.println(cart.getUid()+"\\"+cart.getPid());
		if(ecm.addCount(cart)==0) {
			//未更新到指定记录，就新增一条
			cart.setCount(1);
			ecm.insert(cart);
		}
	}

}
