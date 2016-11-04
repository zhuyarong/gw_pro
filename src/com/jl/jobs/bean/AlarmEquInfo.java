package com.jl.jobs.bean;

import java.util.LinkedList;
import java.util.List;

import com.jl.mybean.Surpass;

public class AlarmEquInfo {
	private int equid;
	private int equNum;
	private List<Surpass> surpasses;
	public int getEquid() {
		return equid;
	}
	public void setEquid(int equid) {
		this.equid = equid;
	}
	public int getEquNum() {
		return equNum;
	}
	public void setEquNum(int equNum) {
		this.equNum = equNum;
	}
	public List<Surpass> getSurpasses() {
		return surpasses==null?new LinkedList<Surpass>():surpasses;
	}
	public void setSurpasses(List<Surpass> surpasses) {
		this.surpasses = surpasses;
	}
	
}
