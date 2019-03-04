package com.example.cutomer.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.cutomer.model.Student;
import com.example.cutomer.model.User;
import com.example.cutomer.repository.UserRepository;
import com.example.cutomer.service.UserService;
import com.example.cutomer.vo.UserVO;
import com.example.util.ConstantUtil;

//@RestController

@Controller
@RequestMapping( "/world/" )
public class HelloController {
	private static final Logger log = LoggerFactory.getLogger(HelloController.class);
	
	//ConstantUtil取application.properties的配置信息
	@Autowired
	private ConstantUtil constant;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	
	@RequestMapping("hello")
	public String hello(HttpServletRequest request,HttpSession session, ModelMap modelMap){
		UserVO vo = new UserVO();
		vo.setUserName("test123123");
		List<Map<String, Object>> userList = userService.getUserList(vo , 1, 10);
		System.out.println("size:"+userList.size());
		modelMap.put("users", userList);
		return "/user/list";
	}
	@RequestMapping("delete")
	public String delete(HttpServletRequest request,HttpSession session, ModelMap modelMap,Long userId){
		userRepository.delete(userId);
		return "redirect:hello";//请求重定向
	}
	@RequestMapping("getUser")
	@ResponseBody
	public Student getUser(){
		Student user = new Student();
		user.setAddress("北京");
		user.setAge(11);
		user.setName(constant.getUserName());
		log.info(ConstantUtil.account);
		log.info(constant.getPassword());
		log.info(constant.getUserName());
		return user;
	}
	@RequestMapping("getUserByName")
	@ResponseBody
	public User getUserByName(@RequestParam(value = "name")String name){
		User user = userRepository.findByUserName(name);
		System.out.println(user.getEmail());
		return user;
	}
	@RequestMapping("getStr")
	@ResponseBody
	public String getStr(){
		/*User u = new User();
		u.setUserName("test123123");
		u.setEmail("test123123@163.com");
		User save = userRepository.save(u);
		System.out.println(save.getId());
		System.out.println(u.getId());*/
		/*System.out.println("======deleteById=======");
		userRepository.delete(182L);
		System.out.println("======deleteByUserName=======");*/
//		long n = userRepository.deleteByUserName("test123123");
//		System.out.println("============="+n);
		return "aaa";
	}
}
