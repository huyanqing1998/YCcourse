package com.yc.C76S3PlySpringBoot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//Mybatis包扫描 - 扫接口
@MapperScan("com.yc.C76S3PlySpringBoot")
public class C76S3PlySpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(C76S3PlySpringBootApplication.class, args);
	}

}
