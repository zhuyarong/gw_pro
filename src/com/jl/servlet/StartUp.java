package com.jl.servlet;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.jl.simple.SimpleServer;

public class StartUp  implements ApplicationListener<ContextRefreshedEvent>{

	/**
	 * 
	 */



	public void init() {
		System.out.println("start server");
		try {
			SimpleServer.main(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		init();
	}

}
