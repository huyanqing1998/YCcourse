package com.yc.ebuy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.yc.ebuy.web.LoginInterceptor;

@SpringBootApplication
//MyBatis 接口组件扫描
@MapperScan("com.yc.ebuy")
//开启声明式远程调用
@EnableFeignClients
//服务熔断降级开关
@EnableCircuitBreaker
//spring会话共享注解
@EnableRedisHttpSession
public class EbuyIndexAppcation {
	public static void main(String[] args) {
		SpringApplication.run(EbuyIndexAppcation.class, args);
	}

	/*
	 * 拦截器配置 addPathPatterns拦截 excludePathPatterns不拦截(排除)
	 * 
	 * @Override protected void addInterceptors(InterceptorRegistry registry) {
	 * super.addInterceptors(registry); registry.addInterceptor(new
	 * LoginInterceptor()) .addPathPatterns("addCart") .excludePathPatterns("/**");
	 * }
	 */

}