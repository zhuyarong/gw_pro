package com.jl.jobs.bean;

import java.util.HashMap;
import java.util.Map;

public class AlarmBean {

	private int userid;
	private Map<String, AlarmEquInfo >  AlarmEquInfos;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public Map<String, AlarmEquInfo> getAlarmEquInfos() {
		return AlarmEquInfos==null?new  HashMap<String, AlarmEquInfo>():AlarmEquInfos;
	}
	public void setAlarmEquInfos(Map<String, AlarmEquInfo> alarmEquInfos) {
		AlarmEquInfos = alarmEquInfos;
	}

	

	
}
