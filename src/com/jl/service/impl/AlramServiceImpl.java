package com.jl.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jl.info.json.RXPKT;
import com.jl.mybean.Surpass;
import com.jl.service.AlramService;
import com.jl.service.SurpassService;
@Service
public class AlramServiceImpl implements AlramService {
	@Autowired
	private SurpassService surpassService;
	@Override
	public boolean Alram(List<RXPKT> alramRxpkt, int userId) {
		try {
			for(RXPKT rxpkt:alramRxpkt){
				Surpass surpass = new Surpass();
				surpass.setCurrlat(new BigDecimal(rxpkt.getLati()));
				surpass.setCurrlng(new BigDecimal(rxpkt.getLong()));
				surpass.setEqufenceId(rxpkt.getEfenceId());
				surpass.setEquNum(rxpkt.getMoteID());
				surpass.setPassTimestamp(new Date());
				surpass.setUsersId(userId);
				surpass.setIsSend(0);
				surpassService.save(surpass);
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
