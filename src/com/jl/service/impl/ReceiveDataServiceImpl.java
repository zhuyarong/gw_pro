package com.jl.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jl.info.PUSH_DATA;
import com.jl.info.json.RXPKT;
import com.jl.mybean.EquInfo;
import com.jl.mybean.LocationInfo;
import com.jl.service.EquInfoService;
import com.jl.service.LocationInfoService;
import com.jl.service.ReceiveDataService;

@Service
public class ReceiveDataServiceImpl implements ReceiveDataService{
	@Autowired
	private LocationInfoService locationInfoService;
	@Autowired
	private EquInfoService equInfoService;
	
	private static long count=0;
	private static int typeCount=0;
	private static Set<Integer> set= new HashSet<Integer>();
	private static Logger logger = Logger.getLogger("ReceiveData"); 
	private final static int PUSH_DATA=0x00;
	private final static int PUSH_ACK=0x02;
	private final static int PULL_RESP=0x03;
	private final static int TX_ACK=0x05;

	public  void deal(final byte[] b,int len){
		if(len<39){
			return;
		}

		
		if(!set.contains(len)){
			set.add(len);
			typeCount++;
		}
		count++;
		String logDetail=" "+count+">Detail["+len+","+typeCount+"] ( n: ascii <=> char ) : ";
		String instr=" "+count+">Info["+len+","+typeCount+"]: ";

		Calendar cal= Calendar.getInstance();  
		String year="";
		String month="";
		String day = "";
		String hours="";
		String min="";
		String second="";
		String mac="";
		String timeZone="";
		String json="";
		switch (b[3]) {
		case PUSH_DATA:
			PUSH_DATA pushdata =new PUSH_DATA();
			pushdata.setProtocolVersion(b[0]+"");
			pushdata.setRandomToken(b[1]+""+b[2]);
			pushdata.setIdentifier(b[3]+"");
			//pushdata.setMAC_address(""+b[4]+b[5]+b[6]+b[7]+b[8]+b[9]+b[10]+b[11]);
			
			for(int i=0;i<len;i++){
				if(i==0){
					
				}else if(i<3){
					
				}else if(i==3){
					
				}else if(i<12){
					mac+=Integer.toHexString(b[i]);
				}else if(i<16){
					year+=(char)b[i];
				}else if(i==16){
					//-
				}else if(i<19){
					month+=(char)b[i];
				}else if(i==19){
					//-
				}else if(i<22){
					day+=(char)b[i];
				}else if(i==22){
					//T
				}else if(i<25){
					hours+=(char)b[i];
				}else if(i==25){
					//:
				}else if(i<28){
					min+=(char)b[i];
				}else if(i==28){
					//:
				}else if(i<31){
					second+=(char)b[i];
				}else if(i<=38){
					//31 +
					timeZone+=(char)b[i];
				}else if(i>38){
					json+=(char)b[i];
				}
				
				logDetail+="( "+i+": "+b[i]+" <=> "+(char)b[i]+" )  ";
				//instr+=(char)b[i];
			}
			System.err.println(year+"-"+month+"-"+"-"+day+" "+hours+":"+min+":"+second);
			cal.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day),
					Integer.parseInt(hours), Integer.parseInt(min), Integer.parseInt(second));	
			//timeZone =timeZone.substring(beginIndex)
			timeZone= timeZone.trim();
			int zoneStart=timeZone.indexOf("+");
			zoneStart =zoneStart>0?zoneStart:0;
			timeZone=timeZone.substring(zoneStart,timeZone.length());
			cal.setTimeZone(TimeZone.getTimeZone("GMT"+timeZone));
			json =json.substring(json.indexOf('['),json.indexOf(']')+1);
			 mac = mac.trim();
			 logger.info("json:"+json);
			JSONArray array = JSONArray.fromObject(json);               
	        for(int i = 0; i < array.size(); i++){        
	            JSONObject jsonObject = array.getJSONObject(i);    
	            RXPKT rxpkt =new RXPKT();
	            rxpkt.setMoteID(jsonObject.getString("Mote ID"));
	            rxpkt.setLati(jsonObject.getString("Lati"));
	            rxpkt.setLong(jsonObject.getString("Long"));
	            rxpkt.setDatr(jsonObject.getString("Datr"));
	            rxpkt.setSteps(jsonObject.getString("steps"));
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
		        int steps=0;
		     
		        try {
		        	steps = Integer.parseInt(rxpkt.getSteps());
					
				} catch (NumberFormatException e) {
					logger.info(e.getMessage());
				}

		        equInfo.setElectricity(datr);
		        equInfo.setEquNum(rxpkt.getMoteID());
		        equInfo.setDeleted((short)0);
		        equInfo.setExpirytimestamp(new Date(System.currentTimeMillis()+12*30*24*60*60*1000));
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
	        }  
	       
	      
			break;
		case PUSH_ACK:
			break;
		case PULL_RESP:
			break;
		case TX_ACK:
			doTX_ACK(b);
			break;
		default:
			break;
		}
		
		logger.info(logDetail);
		logger.info(instr);
	}
	private void doTX_ACK(final byte[] b){
		PUSH_DATA pushdata =new PUSH_DATA();
		pushdata.setProtocolVersion(b[0]+"");
		pushdata.setRandomToken(b[1]+""+b[2]);
		pushdata.setIdentifier(b[3]+"");
		pushdata.setMAC_address(""+b[4]+b[5]+b[6]+b[7]+b[8]+b[9]+b[10]+b[11]);
		
	}
}
