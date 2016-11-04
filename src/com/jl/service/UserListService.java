package com.jl.service;

import org.springframework.stereotype.Service;

import com.jl.mybean.Users;
import com.jl.page.Page;
@Service
public interface UserListService {
	public Page<Users> findAllUsersByname(String username);
	public Users findUserByname(String username);
	public int addUser(Users users);
	public Users findUserById(Integer  id);
}

