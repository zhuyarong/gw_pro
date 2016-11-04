package com.jl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jl.mybean.Users;
import com.jl.mybean.mydao.UsersMapper;
import com.jl.page.Page;
import com.jl.service.UserListService;
@Service
public class UserListServiceImpl implements UserListService {
	@Autowired
	private UsersMapper usersMapper;

	public Page<Users> findAllUsersByname(String username) {
	
		List<Users> users = new ArrayList<Users>();
		Users user =usersMapper.findUserByname(username);
		if(null!=user){
			users.add(user);
			List<Users> subUsers = usersMapper.selectByPuserid(user.getId());
			if(null!=subUsers){
				users.addAll(subUsers);
			}
			
		}
		
		Page<Users> userPage = new Page<Users>(users);
		return userPage;
	}

	@Override
	public int addUser(Users users) {
		
		return usersMapper.insertSelective(users);
	}

	@Override
	public Users findUserByname(String username) {
		
		return usersMapper.findUserByname(username);
	}

	@Override
	public Users findUserById(Integer id) {
		return usersMapper.selectByPrimaryKey(id);
	}

}
