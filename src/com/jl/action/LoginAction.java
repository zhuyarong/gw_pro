package com.jl.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jl.mybean.Users;
import com.jl.page.Page;
import com.jl.service.LoginService;
import com.jl.service.UserListService;
import com.jl.utils.JsonUtils;
import com.jl.webBean.WebUser;

@Controller
@RequestMapping("/")
public class LoginAction {

	@Autowired
	private LoginService loginService; // 等价于spring传统注入方式写get和set方法，这样的好处是简洁工整，省去了不必要得代码
	@Autowired
	private UserListService UserListService; // 等价于spring传统注入方式写get和set方法，这样的好处是简洁工整，省去了不必要得代码

	@RequestMapping("/index.do")
	public String index(
			@RequestParam(value = "num", required = false) Long num,
			HttpServletRequest request) {
			if(null!=num){
				System.out.println(num);
			}
		

		return "login.jsp";
	}
	// 请求url地址映射，类似Struts的action-mapping
	@ResponseBody
	@RequestMapping("/login")
	public Users  UserLogin(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			HttpServletRequest request, HttpServletResponse response) {
//		  Map<String, Object> map = new HashMap<String, Object>();
//		  map.put("user", user);
			//JsonUtils.renderJson(response,map);
		System.out.println("userlogin");
		Users user = new Users();
		if (loginService.login(username, password)) {
			System.out.println("username:"+username);
			user = UserListService.findUserByname(username);
		} 
		
		return user;

	}

	
	@RequestMapping(value = { "/login.do" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	// 请求url地址映射，类似Struts的action-mapping
	public String Login(@RequestParam(value = "username") String  username,
			@RequestParam(value = "password") String password,
			HttpServletRequest request, HttpServletResponse response) {
		// public String login(String username, String password,
		// HttpServletRequest request) {
		// @RequestParam是指请求url地址映射中必须含有的参数(除非属性required=false)
		// @RequestParam可简写为：@RequestParam("username")

		if (loginService.login(username, password)) {
			request.setAttribute("todayCustoms", 567);// TODO
			request.setAttribute("username", username);
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			request.setAttribute("role", 1);
			Page<Users> users = UserListService.findAllUsersByname(username);
			users.save();
			request.setAttribute("users", users);
			return "WEB-INF/jsp/userlist.jsp";
		} else {
			return "login.jsp";
		}

	}
	
	@RequestMapping(value = { "/login1.do/{username}" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	// 请求url地址映射，类似Struts的action-mapping
	public String Login1(@PathVariable String  username,
			@RequestParam(value = "password") String password,
			HttpServletRequest request, HttpServletResponse response) {
		// public String login(String username, String password,
		// HttpServletRequest request) {
		// @RequestParam是指请求url地址映射中必须含有的参数(除非属性required=false)
		// @RequestParam可简写为：@RequestParam("username")

		if (loginService.login(username, password)) {
			request.setAttribute("todayCustoms", 567);// TODO
			request.setAttribute("username", username);
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			request.setAttribute("role", 1);
			Page<Users> users = UserListService.findAllUsersByname(username);
			users.save();
			request.setAttribute("users", users);
			return "WEB-INF/jsp/userlist.jsp";
		} else {
			return "login.jsp";
		}

	}

	@RequestMapping(value = "/getPage.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getUserList(
			@RequestParam(value = "nextPageNum") int nextPageNum,
			@RequestParam(value = "pageId") String pageId,
			HttpServletRequest request) {
		request.setAttribute("todayCustoms", 567);// TODO
		String username = (String) request.getSession()
				.getAttribute("username");
		request.setAttribute("username", username);
		request.setAttribute("role", 1);
		Page users = new Page(new ArrayList<String>());
		;
		users.setCurruentPageNum(nextPageNum);

		request.setAttribute("users", users);
		return "WEB-INF/jsp/userlist.jsp";
	}

	@RequestMapping(value = "login1.do")
	public @ResponseBody
	Map<String, Object> login(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println(username);
		Map<String, Object> map = new HashMap<String, Object>();

		if ("user1".equals(username)) {
			map.put("rs", "success");
		} else {
			map.put("rs", "fail");
		}
		return map;
	}

	@RequestMapping(value = "/getPage1.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	Map<String, Object> test(HttpServletResponse response) {
		Map map = new HashMap();

		ArrayList list = new ArrayList();;
		WebUser user = new WebUser();
		
		user.setUsername("user@@");
		user.setPhone("13524245052");
		user.setEmail("348702695@qq.com");
		list.add(user);
		list.add(user);
		list.add(user);
		list.add(user);
		map.put("result", "success");
		map.put("count", 10);
		map.put("list", list);
		// JsonUtils.renderJson(response, "{result:'" + map.get("result") +
		// "',count:"
		// + map.get("count") + "}");
		JsonUtils.renderJson(response, map);
		return null;
	}

}
