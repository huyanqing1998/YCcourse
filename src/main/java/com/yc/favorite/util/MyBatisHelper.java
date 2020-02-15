package com.yc.favorite.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisHelper {

	private static SqlSessionFactory sqlSessionFactory;

	static {
		// 定义mybatis配置文件路径，默认从 classpath 开始
		String resource = "mybatis.xml";
		InputStream inputStream = null;
		try {
			// 读入 配置文件
			inputStream = Resources.getResourceAsStream(resource);
			// 创建会话工厂 bean
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

	}

	public static SqlSession openSession() {
		// MyBatis 的会话底层包装着 一个 JDBC 连接
		return sqlSessionFactory.openSession();
	}

}
