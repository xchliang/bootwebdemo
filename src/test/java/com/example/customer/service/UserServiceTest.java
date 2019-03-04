package com.example.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bootwebdemo.BootwebdemoApplication;
import com.example.cutomer.model.User;
import com.example.cutomer.service.UserService;
import com.example.cutomer.vo.UserVO;

@RunWith(SpringRunner.class)
//classes是启动类
@SpringBootTest(classes=BootwebdemoApplication.class)
public class UserServiceTest {
	@Autowired
	private UserService userService;
	
	@Test
	public void testPage(){
		int pageNum=1, pageSize=10;
		UserVO vo=new UserVO();
		vo.setUserName("test");
		vo.setEmail("test123123@163.com");
//		vo.setLockStatus(1);
		List<Integer> orgIdList=new ArrayList<Integer>();
		orgIdList.add(1);
		orgIdList.add(2);
		orgIdList.add(3);
		vo.setOrgIdList(orgIdList);
		Page<User> page = userService.getUserPage(vo, pageNum, pageSize);
		if(page!=null && page.hasContent()){
			List<User> list = page.getContent();
			System.out.println("====getUserPage  list.size："+list.size());
			for (User user : list) {
				System.out.println("====ID:"+user.getId()+" status:"+user.getLockStatus()
						+" orgId:"+user.getOrgId()+" level:"+user.getLevel());
			}
		}
		/*List<User> list2 = userService.getUserList(vo);
		if(list2!=null){
			System.out.println("====getUserList list.size："+list2.size());
			for (User user : list2) {
				System.out.println("====ID:"+user.getId()+"===status:"+user.getLockStatus());
			}
		}*/
	}
	@Test
	public void testCount(){
		UserVO vo=new UserVO();
		vo.setUserName("test1");
		vo.setLockStatus(1);
		long count = userService.getCount(vo);
		System.out.println("======count:"+count);
	}
	
	@Test
	public void testList(){
		int pageNum=1, pageSize=10;
		UserVO vo=new UserVO();
		vo.setUserName("test123123");
		vo.setLockStatus(1);
		List<Map<String,Object>> list2 = userService.getUserList(vo,pageNum,pageSize);
		if(list2!=null){
			System.out.println("====getUserList list.size："+list2.size());
			for (Map<String,Object> map : list2) {
				System.out.println(map.get("user_name") + " "+map.get("email"));
			}
		}
	}
	
	@Test
	public void testTransaction(){
		try {
			userService.modifyUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
