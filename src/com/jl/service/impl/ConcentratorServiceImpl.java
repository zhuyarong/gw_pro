package com.jl.service.impl;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jl.arithmetic.PointCheck;
import com.jl.info.json.RXPKT;
import com.jl.mybean.Concentrator;
import com.jl.mybean.EquFence;
import com.jl.mybean.mydao.ConcentratorMapper;
import com.jl.mybean.mydao.EquFenceMapper;
import com.jl.service.AlramService;
import com.jl.service.ConcentratorService;
@Service
public class ConcentratorServiceImpl implements ConcentratorService {

	@Autowired
	private ConcentratorMapper concentratorMapper;
	@Autowired
	private EquFenceMapper equFenceMapper;
	@Autowired
	private AlramService alramService;
	@Override
	public int save(Concentrator conc,List<RXPKT> rxpkts) {
		String mac=conc.getMac();
		if(mac==null){
			return 0;
		}
		Concentrator dbconc = concentratorMapper.selectByMac(mac);
		if(dbconc==null){
			
			return concentratorMapper.insertSelective(conc);
		}else{
			if(null!=dbconc.getEfenceId()){
				EquFence equFence = equFenceMapper.selectByPrimaryKey(dbconc.getEfenceId());
				String location = equFence.getLocation();
				List<Point2D.Double> polygon = new ArrayList<Point2D.Double>();
				JSONArray array = JSONArray.fromObject(location);
				
				for (int i = 0; i < array.size(); i++) {
					JSONObject jsonObject = array.getJSONObject(i);
					Point2D.Double p2dd= new Point2D.Double();
					p2dd.setLocation(Double.parseDouble(jsonObject.getString("lat")),Double.parseDouble(jsonObject.getString("lng")));
					polygon.add(p2dd);
				}
				PointCheck pointCheck = new PointCheck();
				List<RXPKT> alramRxpkt=new ArrayList<RXPKT>();
				for (RXPKT rxpkt:rxpkts) {
					
					Point2D.Double point = new Point2D.Double();
					point.setLocation(Double.parseDouble(rxpkt.getLati()),Double.parseDouble(rxpkt.getLong()));
					if(!pointCheck.checkWithJdkPolygon(point, polygon)){
						rxpkt.setEfenceId(dbconc.getEfenceId());
						
						alramRxpkt.add(rxpkt);
					}
				}
				
				if(null!=equFence.getUsersId()){
					int userId =equFence.getUsersId();
					conc.setUsersId(userId);
					alramService.Alram(rxpkts,userId);
				}
				
				
			}
			return concentratorMapper.updateByMac(conc);
		}
		
	}

	@Override
	public Concentrator getByMac(String mac) {
		return concentratorMapper.selectByMac(mac);
	}

	@Override
	public int save(Concentrator conc) {
		String mac=conc.getMac();
		if(mac==null){
			return 0;
		}
		Concentrator dbconc = concentratorMapper.selectByMac(mac);
		if(dbconc==null){
			return concentratorMapper.insertSelective(conc);
		}else{
			return concentratorMapper.updateByMac(conc);
		}
		
	}

}
