package com.jl.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jl.mybean.Surpass;
import com.jl.page.Page;
import com.jl.service.SurpassService;

@Controller
@RequestMapping("/surpass")
public class SurpassController {
	@Autowired
	private SurpassService surpassService;
	@ResponseBody
	@RequestMapping("/getAlramList")
	public List<Surpass>   getAlramList(HttpServletRequest request, HttpServletResponse response) {

		List<Surpass>  alramSp = surpassService.findAlramEqu();
		return alramSp;

	}
	@ResponseBody
	@RequestMapping("/getAlramList/{pageNum}")
	public List<Surpass>   getAlramListPage(@PathVariable int pageNum,HttpServletRequest request, HttpServletResponse response) {

		List<Surpass>  alramSp = surpassService.findAlramEqu();
		Page<Surpass>  page =new Page<Surpass>(alramSp);
		List<Surpass>  thispage =page.getPage(pageNum);
		return thispage;

	}
}
