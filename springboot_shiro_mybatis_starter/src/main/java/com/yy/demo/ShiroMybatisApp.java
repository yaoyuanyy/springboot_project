package com.yy.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ShiroMybatisApp extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ShiroMybatisApp.class, args);
	}
}
