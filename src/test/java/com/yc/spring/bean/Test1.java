package com.yc.spring.bean;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.mybatis.bean.Cinema;
import com.yc.mybatis.bean.User;

public class Test1 {

	private ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	
	@Test
	public void test() {
		System.out.println("-------------测试开始------------");
		//使用id获取bean需要做强制类型转换
		User user1 = (User) context.getBean("user");
		Assert.assertNotNull(user1);
		User user2 = context.getBean(User.class);
		User user3 = context.getBean(User.class);
		Assert.assertNotEquals(user1, user2);
		Assert.assertNotEquals(user1, user3);
		//默认情况下，getBean同一个bean，返回同一个对象 user1 == user2
		
		System.out.println("------------------------------");
		//测试动态的工厂方法
		User user4 = (User) context.getBean("user1");
	}
	
	@Test
	public void test1() {
		User user = (User) context.getBean("user2");
		Assert.assertEquals("武松",user.getUsername());
		Assert.assertEquals((Integer)1,user.getId());
		
		User user1 = (User) context.getBean("user3");
		Assert.assertEquals("李逵",user1.getUsername());
		Assert.assertEquals((Integer)1,user.getId());
		
		Assert.assertEquals("小象",user1.getHall().getName());
		
		User user2 = (User) context.getBean("user4");
		Assert.assertEquals(user1.getHall(),user2.getHall());
	}
	
	@Test
	public void test2() {
		Cinema c = context.getBean(Cinema.class);
		Assert.assertEquals(4, c.getHalls().size());
		Assert.assertEquals("进步",c.getHalls().get(3).getName());
	}

}
