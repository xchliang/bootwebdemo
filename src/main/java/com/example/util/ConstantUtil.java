package com.example.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 常量类
 * application.properties
 * @author xcl
 *
 */
@Component
public class ConstantUtil {
	//静态变量不能使用@Value赋值
	public static String account;
	
	//注解非静态变量，不需要set方法
	@Value("${email.password}")
	private String password;
	
	@Value("${email.userName}")
	private String userName;

	//注解非静态方法，给静态变量赋值
	@Value("${email.account}")
	public void setAccount(String account) {
		ConstantUtil.account = account;
	}
	
	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}
	
}
