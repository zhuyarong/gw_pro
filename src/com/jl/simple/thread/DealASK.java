package com.jl.simple.thread;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.jl.http.HttpRequester;

public class DealASK implements Runnable{
	private String url ="";
	private Map<String, String> params = new HashMap<String, String>(); 
	public DealASK(String url,Map<String, String> params){
		this.url =url;
		this.params=params;
	}
	@Override
	public void run() {
		 HttpRequester hr= new HttpRequester();

			
			try {
				hr.sendPost(url, params);
			} catch (IOException e) {
				e.printStackTrace();
			}      
	}
	
}
