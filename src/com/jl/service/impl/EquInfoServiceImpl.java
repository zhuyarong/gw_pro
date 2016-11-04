package com.jl.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jl.mybean.EquInfo;
import com.jl.mybean.mydao.EquInfoMapper;
import com.jl.service.EquInfoService;
@Service
public class EquInfoServiceImpl implements EquInfoService{

	@Autowired
	private EquInfoMapper equInfoMapper;
	
	@Override
	public int save(EquInfo equInfo) {
		if(equInfo.getEquNum()!=null){
			EquInfo dbequInfo =equInfoMapper.selectByEqunum(equInfo.getEquNum());
			if(dbequInfo==null){
				return equInfoMapper.insertSelective(equInfo);
			}else if(dbequInfo.getConcentratorMac()==equInfo.getConcentratorMac()){ 
				equInfo.setCreateTime(dbequInfo.getCreateTime());
				equInfo.setModifyTime(new Date());
				equInfo.setVersion(dbequInfo.getVersion()+1);
				return equInfoMapper.updateByEqunumSelective(equInfo);
			}
		}
		return 0;
	}

	@Override
	public EquInfo get(Integer id) {
	
		return equInfoMapper.selectByPrimaryKey(id);
	}

}
