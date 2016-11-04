package com.jl.test2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
public class JacksonCoreTest {
	
	public static void main(String[] args) throws IOException {
		ArrayList<String> lists = new ArrayList();
		lists.add("testlist01");
		lists.add("testlist02");
		HashMap<String, String> maps = new HashMap();
		maps.put("mapkey01", "mapvalue01");
		maps.put("mapkey02", "mapvalue02");
		User user1 = new User();
		user1.setListinfo(lists);
		user1.setUname("yetao");
		user1.setUpwd("666");
		ObjectMapper mapper = new ObjectMapper();
		// 仅输出一行json字符串
		mapper.writeValue(System.out, user1);
		// 将字符串美化成多行
		System.out.println(mapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(user1));
	}
	
}
class User{
	private String uname;
	private String upwd;
	private List<String> listinfo;
	public User(){}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public List<String> getListinfo() {
		return listinfo;
	}
	public void setListinfo(List<String> listinfo) {
		this.listinfo = listinfo;
	}
	
}