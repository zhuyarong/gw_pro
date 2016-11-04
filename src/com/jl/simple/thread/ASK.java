package com.jl.simple.thread;

import java.util.Map;

public class ASK {
	public void sendPost(String url,Map<String, String> params){
		Thread thr = new Thread(new DealASK(url, params));
		thr.start();
	}
}
