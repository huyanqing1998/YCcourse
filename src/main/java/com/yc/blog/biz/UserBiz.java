package com.yc.blog.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.blog.bean.User;
import com.yc.blog.bean.UserExample;
import com.yc.blog.dao.UserMapper;

@Service
public class UserBiz {
	
	@Resource
	private UserMapper um;
	
	public void reg(User user) throws BizException {
		
		//查询账号是否重复
		UserExample ue = new UserExample();
		ue.createCriteria().andAccountEqualTo(user.getAccount());		
		if(um.countByExample(ue) > 0) {
			throw new BizException("该用户名已经被注册！");
		}
		
		um.insert(user);
	}

}
