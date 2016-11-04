package com.jl.jobs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.jl.jobs.bean.AlarmBean;
import com.jl.jobs.bean.AlarmEquInfo;
import com.jl.mybean.Surpass;
import com.jl.service.SurpassService;

public class OffsideAlarm{
	@Autowired
	private SurpassService surpassService;

	
	public void checkOffside(){
		List<Surpass> list = surpassService.getSurpassesBySendflag(0);
		Map<Integer, AlarmBean > userPassmap = new HashMap<Integer,AlarmBean>();
		List<Integer> ids= new LinkedList<Integer>();
		if(!CollectionUtils.isEmpty(list)){
			for(Surpass surpass:list){
				ids.add(surpass.getId());
				//surpass
				int userId = surpass.getUsersId();
				AlarmBean ab =  userPassmap.get(userId);
				if(null==ab){
					ab= new AlarmBean();
					
				}
				String equNum = surpass.getEquNum();
				Map<String, AlarmEquInfo > eio = ab.getAlarmEquInfos();
				AlarmEquInfo equInfos = eio.get(equNum);
				List<Surpass> sublist =equInfos.getSurpasses();
				sublist.add(surpass);
				userPassmap.put(userId, ab);
			}
			
		}
		
		doSms(userPassmap);
		
		surpassService.updateSurpasses(ids, 1);
		
	}
	public void doSms(Map<Integer, AlarmBean > userPassmap){

		System.out.println("doSms");
		
	}
}
