package com.jl.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jl.info.json.RXPKT;
import com.jl.mybean.Concentrator;
import com.jl.mybean.EquInfo;
import com.jl.mybean.LocationInfo;
import com.jl.service.ConcentratorService;
import com.jl.service.EquInfoService;
import com.jl.service.LocationInfoService;

@Controller
@RequestMapping("/ReceiveData")
public class ReceiveDataController {
	private static Logger logger = Logger.getLogger("ReceiveData");
	@Autowired
	private LocationInfoService locationInfoService;
	@Autowired
	private EquInfoService equInfoService;
	
	@Autowired
	private ConcentratorService concentratorService;

	// @ModelAttribute User user
	@ResponseBody
	@RequestMapping(value = "/save.do", produces = "application/json")
	public String save(String json, String mac, HttpServletRequest request,
			String key,HttpServletResponse response) {
		
		JSONArray array = JSONArray.fromObject(json);
		List<RXPKT> rxpkts = new ArrayList<RXPKT>();
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			RXPKT rxpkt = new RXPKT();
			rxpkt.setMoteID(jsonObject.getString("Mote ID"));
			rxpkt.setLati(jsonObject.getString("Lati"));
			rxpkt.setLong(jsonObject.getString("Long"));
			rxpkt.setDatr(jsonObject.getString("Datr"));
			rxpkt.setSteps(jsonObject.getString("steps"));
			rxpkts.add(rxpkt);
			EquInfo equInfo = new EquInfo();
			equInfo.setConcentratorMac(mac);
			equInfo.setCreateTime(new Date());
			equInfo.setModifyTime(new Date());
			int datr = 100;
			try {
				datr = Integer.parseInt(rxpkt.getDatr());
			} catch (NumberFormatException e) {
				logger.info(e.getMessage());
			}
			int steps = 0;

			try {
				steps = Integer.parseInt(rxpkt.getSteps());

			} catch (NumberFormatException e) {
				logger.info(e.getMessage());
			}

			equInfo.setElectricity(datr);
			equInfo.setEquNum(rxpkt.getMoteID());
			equInfo.setDeleted((short) 0);
			equInfo.setExpirytimestamp(new Date(System.currentTimeMillis() + 12
					* 30 * 24 * 60 * 60 * 1000));
		
			equInfoService.save(equInfo);
			LocationInfo li = new LocationInfo();
			li.setElectricity(rxpkt.getDatr());
			li.setActive(steps);
			li.setCreateTime(new Date());
			li.setEquNum(rxpkt.getMoteID());
			li.setLat(new BigDecimal(rxpkt.getLati()));
			li.setLng(new BigDecimal(rxpkt.getLong()));
			li.setEquType("1");
			locationInfoService.save(li);
			
			Concentrator conc = new Concentrator();
			conc.setMac(mac);
			conc.setKey(key);
			concentratorService.save(conc,rxpkts);
			
			

		}
		return "success";
	}
}
