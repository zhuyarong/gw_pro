package com.jl.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jl.mybean.Surpass;
import com.jl.mybean.mydao.SurpassMapper;
import com.jl.service.SurpassService;
@Service
public class SurpassServiceImpl implements SurpassService{

	@Autowired
	private SurpassMapper surpassMapper;
	@Override
	public int save(Surpass surpass) {
		
		return surpassMapper.insertSelective(surpass);
	}
	@Override
	public List<Surpass> getSurpassesBySendflag(int flag) {
		
		return  surpassMapper.getSurpassesBySendflag(flag);
	}
	@Override
	public int updateSurpasses(List<Integer> ids, int flag) {
		return surpassMapper.updateSurpasses(ids, flag);
	}
	@Override
	public List<Surpass> findAlramEqu() {
		
		List<Surpass>  list =surpassMapper.selectEqunum();
		List<Surpass>  currSpes=new LinkedList<Surpass>();
		
		for(Surpass sp:list ){
			String equNum =sp.getEquNum();
			Surpass currSp = surpassMapper.selectByEqunum(equNum);
			currSpes.add(currSp);
		}
		
		return currSpes;
	}

}
