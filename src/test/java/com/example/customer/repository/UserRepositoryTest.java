package com.example.customer.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bootwebdemo.BootwebdemoApplication;
import com.example.cutomer.model.User;
import com.example.cutomer.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=BootwebdemoApplication.class)
public class UserRepositoryTest {
	@Autowired
	UserRepository userRepository;
	
	@Test
	public void test() {
//		fail("Not yet implemented");
		String userName = "test1233123";
		User user = userRepository.findByUserName(userName);
		System.out.println(user!=null?user.getUserName():null);
		String email = "test1233123@163.com";
		user = userRepository.getByUserNameAndEmail(userName,email);
		System.out.println(user!=null?user.getUserName():null);
		user = userRepository.queryByUserNameOrEmail(userName, null);
		System.out.println(user!=null?user.getUserName():null);
		//queryXXXLike
		List<User> userList = userRepository.queryByUserNameLike("%xin%");
		if(userList!=null && userList.size()>0){
			System.out.println("===============list start=============");
			for (User u : userList) {
				System.out.println(u.getUserName());
			}
			System.out.println("=============list end=================");
		}else{
			System.out.println("=============like null=================");
		}
	}
	
	@Test
	public void test2() {
		String userName="test123123";
		String email="test123123@163.com";
//		List<User> userList = userRepository.getUsers2(userName,email);
		List<User> userList = userRepository.getUsersBySql(userName,email);
		if(userList!=null && userList.size()>0){
			System.out.println("===============list start=============");
			for (User u : userList) {
				System.out.println(u.getUserName());
			}
			System.out.println("=============list end=================");
		}else{
			System.out.println("=============query null=================");
		}
	}
	
	@Test
	public void testSave(){
		User u = new User();
		u.setUserName("test123123");
		u.setEmail("test123123@163.com");
		User save = userRepository.save(u);
		System.out.println(save.getId());
		System.out.println(u.getId());
		
	}

	@Test
	public void testDelete(){
		/*System.out.println("======deleteById=======");
		userRepository.delete(181L);*/
		System.out.println("======deleteByUserName=======");
		int m = userRepository.deleteByUserName("test1231234");
		System.out.println("============="+m);
	}
	
	@Test
	public void testUpdate(){
		/*System.out.println("======updateByUserName=======");
		int m = userRepository.updateByUserName(1,"test123123");
		System.out.println("============="+m);*/
		userRepository.updateUser(1, 192L);
	}

	@Test
	public void testPage() {
		int pageNum = 0, size = 10;// 页码从0开始
		Sort sort = new Sort(Direction.ASC, "userName");// 排序
		sort = sort.and(new Sort(Direction.DESC, "baseCreateTime"));
		Pageable pageable = new PageRequest(pageNum, size, sort);
		// 查询条件
		User userParam = new User();
		userParam.setUserName("test123123");
		userParam.setEmail("test123123@163.com");
		Example<User> example = Example.of(userParam);// 所有非空属性生成Example
		Page<User> page = null;
		List<User> list = null;
		/*
		 * list = userRepository.findAll(example); 
		 * list = userRepository.findAll(sort); 
		 * list = userRepository.findAll(example, sort); 
		 * page = userRepository.findAll(pageable); 
		 * page = userRepository.findAll(example, pageable);
		 */
		while (page == null || page.hasNext()) {
			if (page != null) {
				pageable = page.nextPageable();
			}
			page = userRepository.findAll(example, pageable);
			System.out.println("=====页面大小：" + page.getSize());
			System.out.println("=====总页数：" + page.getTotalPages());
			System.out.println("=====当前页码：" + page.getNumber());
			System.out.println("=====总条数：" + page.getTotalElements());
			System.out.println("=====当前页的数据条数：" + page.getNumberOfElements());
			list = page.getContent();// 数据
			if (list != null && list.size() > 0) {
				System.out.println("===list.size==" + list.size());
				for (User u : list) {
					System.out.println(u.getUserName());
				}
			}
		}
	}
	
}
