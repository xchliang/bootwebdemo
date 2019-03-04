package com.example.cutomer.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.example.cutomer.model.User;
import com.example.cutomer.vo.UserVO;

public interface UserService {
	
	public Page<User> getUserPage(UserVO vo,int pageNum, int pageSize);
	
	public long getCount(UserVO vo);
	
	public List<Map<String,Object>> getUserList(UserVO vo,int pageNum,int pageSize);
	
	public void modifyUser();

}
