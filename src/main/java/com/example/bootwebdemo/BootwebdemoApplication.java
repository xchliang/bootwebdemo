package com.example.bootwebdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages={"com.example.bootwebdemo", 
		"com.example.datasource", "com.example.util","com.example.cutomer.timer",
		"com.example.cutomer.controller", "com.example.cutomer.service",
		"com.example.goods.service"})
//可以用ComponentScan注解扫描报
//@ComponentScan(basePackages={"com.example.bootwebdemo", "com.example.util"})

/* repository包路径
 * 多数据源配置时，启动类上的@EnableJpaRepositories注解一定要去掉。
 * 因为在每个数据源的配置类上都会单独使用该注解，分别指明数据源的repository
 * 如果不去掉，会造成扫描混乱，一般会出现异常：
 * 	No bean named 'entityManagerFactory' available
 */
//@EnableJpaRepositories(basePackages={"com.example.cutomer.repository",
//		"com.example.goods.dao"})
/* 实体类的包路径
 * 多数据源时启动类上不需要@EntityScan注解，因为在数据源配置中会指定实体类包路径
 */
//@EntityScan(basePackages={"com.example.entity", "com.example.cutomer.model", 
//		"com.example.goods.model"})

/*
 * @EnableScheduling//开启定时任务
 */
public class BootwebdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootwebdemoApplication.class, args);
	}

}

