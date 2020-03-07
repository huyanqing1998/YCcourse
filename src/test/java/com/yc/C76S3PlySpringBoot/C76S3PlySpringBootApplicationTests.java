package com.yc.C76S3PlySpringBoot;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import com.yc.C76S3PlySpringBoot.bean.User;
import com.yc.C76S3PlySpringBoot.biz.UserBiz;
import com.yc.C76S3PlySpringBoot.dao.TagMapper;
import com.yc.C76S3PlySpringBoot.dao.UserMapper;

@SpringBootTest
class C76S3PlySpringBootApplicationTests {
	
	@Resource
	private TagMapper tm;
	
	@Resource
	private UserBiz ubiz;
	
	@MockBean
	private UserMapper um;
	

	@Test
	void contextLoads() {
		Assert.isTrue(tm.selectAll().size() > 0,"结果集数量不正确");
	}
	
	@Test
	void test1() {
User user = new User();
		
		/**
		 * 输入值的设置
		 */
		Mockito.when(um.selectByNameAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
		
		/*
		 * 存根验证
		 */
		Mockito.when(um.selectByNameAndPassword("yc", "123")).thenReturn(user);
		Mockito.when(um.selectByNameAndPassword("zhangsan", "123")).thenReturn(null);
		Mockito.when(um.selectByNameAndPassword("lisi", "111")).thenReturn(user);
		
		boolean bool = ubiz.login("zhangsan", "123");
		Assert.isTrue(bool == false, "登录测试失败");
		
		bool  = ubiz.login("yc", "123");
		Assert.isTrue(bool, "登录测试失败");
		
		bool  = ubiz.login("lisi", "111");
		Assert.isTrue(bool, "登录测试失败3");
		
		bool  = ubiz.login("武松", "111");
		Assert.isTrue(bool == false, "登录测试失败4");
		
		bool  = ubiz.login("李逵", "111");
		Assert.isTrue(bool == false, "登录测试失败5");
		
		
		/**
		 * 行为验证
		 */
		Mockito.verify(um).selectByNameAndPassword("zhangsan", "123");
		Mockito.verify(um).selectByNameAndPassword("yc", "123");
		Mockito.verify(um).selectByNameAndPassword("lisi", "111");
		Mockito.verify(um).selectByNameAndPassword("武松", "111");
		Mockito.verify(um).selectByNameAndPassword("李逵", "111");
		
		//Mockito.verify(um).selectByNameAndPassword("林冲", "111");
	}

}
