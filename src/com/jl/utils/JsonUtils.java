/**
 * Copyright (c) 2005-2010 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: Struts2Utils.java 1493 2011-02-13 16:33:38Z calvinxiu $
 */
package com.jl.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * 工具类.
 * 
 * 实现获取Request/Response/Session与绕过jsp直接输出文本的简化函数.
 * 
 * @author liuxc
 */
public abstract class JsonUtils {


	private static ObjectMapper mapper = new ObjectMapper();

	

	/**
	 * 直接输出内容的简便函数.
	 * 
	 * eg. render("text/plain", "hello", "encoding:GBK"); render("text/plain",
	 * "hello", "no-cache:false"); render("text/plain", "hello", "encoding:GBK",
	 * "no-cache:false");
	 * 
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void render(HttpServletResponse response ,final String contentType, final String content,
			final String... headers) {

		try {
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 直接输出文本.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderText(HttpServletResponse response ,final String text, final String... headers) {
		render(response,ServletUtils.TEXT_TYPE, text, headers);
	}

	/**
	 * 直接输出HTML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(HttpServletResponse response ,final String html, final String... headers) {
		render(response,ServletUtils.HTML_TYPE, html, headers);
	}

	/**
	 * 直接输出XML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(HttpServletResponse response ,final String xml, final String... headers) {
		render(response,ServletUtils.XML_TYPE, xml, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param jsonString
	 *            json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(HttpServletResponse response ,final String jsonString,
			final String... headers) {
		render( response ,ServletUtils.JSON_TYPE, jsonString, headers);
	}

	/**
	 * 直接输出JSON,使用Jackson转换Java对象.
	 * 
	 * @param data
	 *            可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(HttpServletResponse response ,final Object data,final String... headers) {

		try {
//			ByteArrayOutputStream bo = new java.io.ByteArrayOutputStream();
//			java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.OutputStreamWriter(bo));
			mapper.writeValue(response.getWriter(), data);
//			mapper.writeValue(bw,data);
//			String tt = new String(bo.toByteArray(),"UTF-8");
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 直接输出支持跨域Mashup的JSONP.
	 * 
	 * @param callbackName
	 *            callback函数名.
	 * @param object
	 *            Java对象,可以是List<POJO>, POJO[], POJO ,也可以Map名值对, 将被转化为json字符串.
	 */
	public static void renderJsonp(HttpServletResponse response ,final String callbackName,
			final Object object, final String... headers) {
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(object);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}

		String result = new StringBuilder().append(callbackName).append("(")
				.append(jsonString).append(");").toString();

		// 渲染Content-Type为javascript的返回内容,输出结果为javascript语句,
		// 如callback197("{html:'Hello World!!!'}");
		render(response,ServletUtils.JS_TYPE, result, headers);
	}



	/**
	 * 
	 * 读取资源文件生成Map
	 * 
	 * path 文件的完整路径 com/lzb/pro/xxx.properties
	 */
	public static Map<String, String> readProperties(String path) {

		Map<String, String> map = new HashMap<String, String>();
		StringBuffer sb = new StringBuffer();
		/**
		 * 获取字节码的路径
		 */
		URL url = JsonUtils.class.getResource("/");
		sb.append(url.getPath() + path);
		String absolutePath = sb.toString();
		File file = new File(absolutePath);

		InputStreamReader ins = null;
		BufferedReader reader = null;
		try {
			// 解决读取配置文件的中文的乱码问题
			ins = new InputStreamReader(new FileInputStream(file), "UTF-8");
			reader = new BufferedReader(ins);
			Properties prop = new Properties();
			// 读取udp.properties
			prop.load(reader);
			Enumeration enums = prop.propertyNames();
			while (enums.hasMoreElements()) {
				String key = (String) enums.nextElement();
				String value = prop.getProperty(key);
				map.put(key, value);
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				ins.close();
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

}
