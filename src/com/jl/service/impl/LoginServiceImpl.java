package com.jl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jl.mybean.Users;
import com.jl.mybean.mydao.UsersMapper;
import com.jl.service.LoginService;
import com.jl.utils.EncodeUtils;
@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	private UsersMapper usersMapper;

	public boolean login(Users user) {
		if(user!=null&&null!=user.getUsername()&&null!=user.getPass()){
			Users dtoUser = usersMapper.findUserByname(user.getUsername());
			if(dtoUser!=null&&null!=dtoUser.getUsername()&&(user.getPass()).equals(dtoUser.getPass()))
			{
				System.out.println("login success");
				return true;
			}
		}
		return false;
	}
	public boolean login(String username, String password) {
		if(null!=username&&null!=password){
			Users dtoUser = usersMapper.findUserByname(username);
			if(dtoUser!=null&&null!=dtoUser.getUsername()&&password.equals(dtoUser.getPass()))
			{
				
				return true;
			}
			
		}
		return false;
	}

}
