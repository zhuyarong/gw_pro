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
