package com.jl.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BaiduMapController {
	//@RequestMapping("/getMap/{username}") @PathVariable String  username,
	@RequestMapping("getMap")
	public String getMap(HttpServletRequest request) {	
		System.out.println("getMap");
		return "/map/map.html";
	}

}
