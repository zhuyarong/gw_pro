package com.jl.action;

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
import com.jl.utils.EncodeUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	
	/**http://www.mamicode.com/info-detail-471331.html
	说明： 
	其中@ApiOperation和@ApiParam为添加的API相关注解，个参数说明如下： 
	@ApiOperation(value = “接口说明”, httpMethod = “接口请求方式”, response = “接口返回参数类型”, notes = “接口发布说明”；其他参数可参考源码； 
	@ApiParam(required = “是否必须参数”, name = “参数名称”, value = “参数具体描述”
	 */
	@Autowired
	private UserListService userListService; // 等价于spring传统注入方式写get和set方法，这样的好处是简洁工整，省去了不必要得代码
	@Autowired
	private LoginService loginService; // 等价于spring传统注入方式写get和set方法，这样的好处是简洁工整，省去了不必要得代码
	
	//@ApiOperation(value = "添加用户", httpMethod = "POST", response = Integer.class, notes = "add user")
	//@ApiParam(required = true, name = "username", value = "用户信息json数据")
	@RequestMapping(value ="/addUsers.do",method=  { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	int addUser( int userId,
			String username,String pass, HttpServletRequest request) {
		Users users = new Users();
		users.setCid(userId);
		users.setUsername(username);
		users.setPass(EncodeUtils.md5Encode(pass));
		int rs = userListService.addUser(users);

		return rs;

	}
	
	@RequestMapping(value = { "/getUser/{username}" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public Users getUser(@PathVariable String  username,
			HttpServletRequest request, HttpServletResponse response) {
		Users user = userListService.findUserByname(username);
		return user;
	}
//	
//    @RequestMapping(value="/{day}", method = RequestMethod.GET)
//    public Map<String, Object> getForDay(@PathVariable @DateTimeFormat(iso=ISO.DATE) Date day, Model model) {
//		return null;
//    }
//    
//    @RequestMapping(value="/owners/{ownerId}", method=RequestMethod.GET)
//    public String findOwner(@PathVariable String ownerId, Model model) {
// 
//      return "displayOwner"; 
//    }
//    
//    @RequestMapping("/spring-web/{symbolicName:[a-z-]+}-{version:\d\.\d\.\d}.{extension:\.[a-z]}")
//    public void handle(@PathVariable String version, @PathVariable String extension) {    
//      // ...
//    }
//  }


}
