package com.jl.service;

import java.util.List;

import com.jl.mybean.EquFence;

public interface EquFenceService {
	public int insert(EquFence record);
	public int insertSelective(EquFence record);
	public EquFence get(int equId);
	public int updateEquFence(EquFence record);
	public List<EquFence> getEquFencesByUserId(int userId);
}
