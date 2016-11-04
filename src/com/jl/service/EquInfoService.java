package com.jl.service;

import com.jl.mybean.EquInfo;

public interface EquInfoService {
	public int save(EquInfo equInfo);
	public EquInfo get(Integer id);
}
