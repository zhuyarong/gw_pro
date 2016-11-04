package com.jl.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.jl.http.HttpRequester;
import com.jl.utils.EncodeUtils;

public class EquFenceControllerTest {

	public static void main(String[] args) {
		System.out.println(EncodeUtils.md5Encode("123456"));
	}
	@Test
	public void testAdd(){
		//http://127.0.0.1:8088/xmserver/equFence/add.do?userid=1&name=%E5%B7%A8%E9%BE%99%E7%89%A7%E5%9C%BA&
		//location=%E4%B8%AD%E5%9B%BD&unit=%E7%AC%AC%E4%B8%80%E5%88%86%E7%89%A7%E5%9C%BA
		HttpRequester hr= new HttpRequester();
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", "1");
		params.put("name", "巨龙牧场");
		params.put("location", "中国");
		params.put("unit", "巨龙第一分牧场");
		try {
			hr.sendPost("http://127.0.0.1:8088/xmserver/equFence/add.do", params);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
