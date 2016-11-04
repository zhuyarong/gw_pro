package com.jl.action;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jl.mybean.Concentrator;
import com.jl.mybean.EquInfo;
import com.jl.service.ConcentratorService;
import com.jl.service.EquInfoService;
import com.jl.simple.deal.SocketMap;
import com.jl.utils.DateUtils;

@Controller
@RequestMapping("/command")
public class SendCommandController {

	
	@Autowired
	private EquInfoService equInfoService;
	
	
	@Autowired
	private ConcentratorService concentratorService;
	@RequestMapping(value = { "/send/{commandtype}/{equid}" }, method = {
			RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public boolean send(
			@PathVariable int equid,
			@PathVariable int commandtype, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			EquInfo equInfo = equInfoService.get(equid);
			String mac = equInfo.getConcentratorMac();
			Concentrator concentrator = concentratorService.getByMac(mac);
			byte[] command =new byte[45];
		for(int i=0;i<command.length;i++){
			command[i]=32;
		}
			byte[] b= {2,1,2,0,0,1,2,3,4,5,6,7,50,48,49,54,45,48,56,45,48,50,84,
					49,53,58,50,48,58,49,55,43,48,56,48,48,32,32,32,123,10,9,
					34,114,120,112,107,116,34,58,9,91,123,10,9,9,9,34,77,111,
					116,101,32,73,68,34,58,9,49,44,10,9,9,9,34,76,97,116,105,
					34,58,9,50,50,46,53,54,54,51,49,52,44,10,9,9,9,34,76,111,
					110,103,34,58,9,49,49,52,46,48,55,56,50,56,54,44,10,9,9,
					9,34,68,97,116,114,34,58,9,57,57,44,10,9,9,9,34,115,116,
					101,112,115,34,58,9,48,10,9,9,125,44,32,123,10,9,9,9,34,
					77,111,116,101,32,73,68,34,58,9,50,44,10,9,9,9,34,76,97,
					116,105,34,58,9,45,50,50,46,53,54,54,51,49,52,44,10,9,9,
					9,34,76,111,110,103,34,58,9,45,49,49,52,46,48,55,56,50,
					56,54,44,10,9,9,9,34,68,97,116,114,34,58,9,57,57,44,10,
					9,9,9,34,115,116,101,112,115,34,58,9,48,10,9,9,125,93,10};
			command[0] =b[0];
			
			command[1]=b[1];
			
			command[2]=b[2];
			command[3]=3;
			int maclen =mac.length();
			if(maclen<8){
				for(int i=0;i<8-maclen;i++){
					mac+=" ";
				}
			}else if(maclen>8){
				mac =mac.substring(0, 8);
			}
			
			String moteId = equInfo.getEquNum();
			int motelen=moteId.length();
			if(motelen<4){
				for(int i=0;i<4-motelen;i++){
					moteId+=" ";
				}
			}else if(motelen>4){
				moteId =moteId.substring(0, 4);
			}
			String UTCTime = DateUtils.getTTime(new Date());
			int utclen = UTCTime.length();
			if(utclen<27){
				for(int i=0;i<27-utclen;i++){
					UTCTime+=" ";
				}
			}else if(motelen>27){
				UTCTime =UTCTime.substring(0, 27);
			}
			String commendascii="";
			commendascii=mac+moteId+" "+UTCTime;
			byte[] commendasciibyte = commendascii.getBytes();
			for(int i=0;i<commendasciibyte.length;i++){
				int n= i+4;
				if(n==16){
					switch (commandtype) {
					case 0:
						command[16]=0;
						break;
					case 1:
						command[16]=1;
						break;
					case 2:
						command[16]=2;
						break;
					default:
						command[16]=0;
						break;
					}
				}else{
					command[n]=commendasciibyte[i];
				}
			}
			
			
			command[44]=10;
			String str = new String(command);
			sendCommand(str, concentrator.getKey());
		
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;

		}
		return true;

	}
	
	private void sendCommand(String command,String key){
		Socket s = SocketMap.get(key);
		BufferedOutputStream bos;
		try {
			bos = new BufferedOutputStream(s.getOutputStream());
			bos.write(command.getBytes());
			bos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
