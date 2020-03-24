package com.yc.ebuy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//MyBatis 接口组件扫描
@MapperScan("com.yc.ebuy")
//开启声明式远程调用
@EnableFeignClients
public class EbuyBackAppcation {
	public static void main(String[] args) {
		SpringApplication.run(EbuyBackAppcation.class, args);
	}
}