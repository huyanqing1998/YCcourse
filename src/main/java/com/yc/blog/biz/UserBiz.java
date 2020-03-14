package com.yc.blog.biz;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.yc.blog.bean.User;
import com.yc.blog.bean.UserExample;
import com.yc.blog.dao.UserMapper;

@Service
public class UserBiz {
	
	@Resource
	private UserMapper um;
	
	public void reg(User user, String repwd) throws BizException {
		//判断两次密码是否一致
		if(!(user.getPwd().equals(repwd))) {
			throw new BizException(101,"pwd","密码与确认密码不一致！");
		}
		
		//查询账号是否重复
		UserExample ue = new UserExample();
		ue.createCriteria().andAccountEqualTo(user.getAccount());		
		if(um.countByExample(ue) > 0) {
			throw new BizException(102,"name","该用户名已经被注册！");
		}
		
		um.insert(user);
	}
	
	public User login(@Valid User user) throws BizException {
		UserExample ue = new UserExample();
		ue.or().andAccountEqualTo(user.getAccount()).andPwdEqualTo(user.getPwd());
		ue.or().andNameEqualTo(user.getAccount()).andPwdEqualTo(user.getPwd());
		ue.or().andPhoneEqualTo(user.getAccount()).andPwdEqualTo(user.getPwd());
		ue.or().andEmailEqualTo(user.getAccount()).andPwdEqualTo(user.getPwd());
		
		List<User> list = um.selectByExample(ue);
		
		if(list.size()==0) {
			throw new BizException(103,"name","用户名或密码错误！");
		} else if(list.size()>1) {
			throw new BizException(104,"name","请更换其他登陆方式！");
		} else {
			return list.get(0);
		}
	}

}
