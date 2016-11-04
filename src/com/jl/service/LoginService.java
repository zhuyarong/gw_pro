package com.jl.service;

import org.springframework.stereotype.Service;

import com.jl.mybean.Users;


@Service
public interface LoginService {

	public boolean login(Users user);
	public boolean login(String username,String password);

}
