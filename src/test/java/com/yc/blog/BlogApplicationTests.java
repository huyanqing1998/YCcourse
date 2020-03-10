package com.yc.blog;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import com.yc.blog.bean.Article;
import com.yc.blog.bean.ArticleExample;
import com.yc.blog.bean.ArticleExample.Criteria;
import com.yc.blog.dao.ArticleMapper;
import com.yc.blog.dao.UserMapper;


@SpringBootTest
class BlogApplicationTests {
	
	@Resource
	private UserMapper um;
	
	@Resource
	private ArticleMapper am;

	@Test
	void contextLoads() {
		Assert.isTrue(um.selectByExample(null).size() > 0, "结果集数量不正确!");
		
		// 如何使用组合条件查询
		// careatetime is not null and title like '%css%'
		
		//创建一个组合条件查询对象
		ArticleExample ae = new ArticleExample();
		
		//由这个对象来创建条件
		Criteria c = ae.createCriteria();
		c.andCreatetimeIsNotNull();
		c.andTitleLike("%css%");
		//相当于以下代码，每次调用都返回同一个对象，因此可以链接下去
		//c.andCreatetimeIsNotNull().andTitleLike("%css%");
		
		//组合条件查询对象放入到 接口对象里查询，返回数据库表记录（bean对象）
		List<Article> list = am.selectByExample(ae);
		
		System.out.println(list);
		
		// 查询文章:  label 带 s   分类 category = 3 的记录
		
	}
	
	

}
