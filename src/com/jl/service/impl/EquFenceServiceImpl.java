package com.jl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jl.mybean.EquFence;
import com.jl.mybean.mydao.EquFenceMapper;
import com.jl.service.EquFenceService;
//@Service("userService")
@Service
public class EquFenceServiceImpl implements EquFenceService{

	//@Resource
	@Autowired
	private EquFenceMapper equFenceMapper;
	public int  insert(EquFence record) {
		return equFenceMapper.insert(record);
	}
	public EquFence get(int equId) {
		return equFenceMapper.selectByPrimaryKey(equId);
	}

	@Override
	public List<EquFence> getEquFencesByUserId(int userId) {
		return equFenceMapper.selectByUserId(userId);
	}
	@Override
	public int updateEquFence(EquFence record) {
		return equFenceMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public int insertSelective(EquFence record) {
		// TODO Auto-generated method stub
		return equFenceMapper.insertSelective(record);
	}
	
	 
}
