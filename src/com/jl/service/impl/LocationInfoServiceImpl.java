package com.jl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jl.mybean.LocationInfo;
import com.jl.mybean.mydao.LocationInfoMapper;
import com.jl.service.LocationInfoService;

@Service
public class LocationInfoServiceImpl implements LocationInfoService{
	@Autowired
	private LocationInfoMapper locationInfoMapper;

	@Override
	public int save(LocationInfo li) {
		
		return locationInfoMapper.insertSelective(li);
	}
	
}
