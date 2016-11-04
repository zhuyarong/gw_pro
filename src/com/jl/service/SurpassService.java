package com.jl.service;

import java.util.List;

import com.jl.mybean.Surpass;

public interface SurpassService {
	public int save(Surpass surpass);
	public List<Surpass> getSurpassesBySendflag(int flag);
	public int updateSurpasses(List<Integer> ids,int flag);
	public  List<Surpass> findAlramEqu();
}
