package com.example.bootwebdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cutomer.model.User;
import com.example.cutomer.repository.UserRepository;
import com.example.util.ConstantUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootwebdemoApplicationTests {
	@Autowired
	private ConstantUtil constant;
	@Autowired
	UserRepository userRepository;
	@Test
	public void contextLoads() {
		System.out.println("=======test======"+constant.getUserName());
		System.out.println("=======test======"+ConstantUtil.account);
		User user = userRepository.findByUserName("test123123");
		System.out.println("=======test======"+user.getEmail());
	}

}

