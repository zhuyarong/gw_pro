package com.jl.simple.deal;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jl.info.PUSH_DATA;
import com.jl.simple.thread.ASK;

@Service
public class ReceiveData {

	
	private static long count=0;
	private static int typeCount=0;
	private static Set<Integer> set= new HashSet<Integer>();
	private static Logger logger = Logger.getLogger("ReceiveData"); 
	private final static int PUSH_DATA=0x00;
	private final static int PUSH_ACK=0x02;
	private final static int PULL_RESP=0x03;
	private final static int TX_ACK=0x05;
	private final static String URL="http://127.0.0.1:8088/xmserver/ReceiveData/save.do";
	/*
	 * 		0
		protocol version = 2
		1-2
		random token
		3
		PULL_RESP identifier 0x03
		4-11
		Gateway unique identifier (MAC address)
		12-15
		Mote ID
		16
		Command  ID，用于服务器下发命令存储命令ID，放在header部分，是为了集中器解析方便。
		0：让指定moteid上报：经纬度和剩余电量，此时json为空
		1:让指定moteid 启动 蜂鸣器   此时json为空
		2：让指定的moteid  关闭蜂鸣器 此时json为空
		120：此时json中有内容
		17-43
		UTC time of pkt RX, us precision, ISO 8601 'compact' format
		43-End
		JSON对象
	 * */
	public  void deal(final byte[] b,int len, String key){
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
				Map<String, String> params = new HashMap<String, String>();
				params.put("json", json);
				params.put("mac", mac);
				params.put("key", key);
				new ASK().sendPost(URL, params);
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



/**
 
package com.jl.simple.deal;


import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SocketMap {
	 private  static Map<String,Socket> map ;

	 static{
		 map = new HashMap<String,Socket>();
	 }

	public static  void add(String key, Socket sk) {
		synchronized (map) {
			map.put(key, sk);
		}
		
	}
	public static Socket get(String key){
		synchronized (map) {
			return map.get(key);
		}
		
	}
	public static Map<String,Socket> getAll(){
		synchronized (map) {
			return map;
		}
	}
	public static boolean  remove(String key){
		try {
			synchronized (map) {
				map.remove(key);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	 
}


**/