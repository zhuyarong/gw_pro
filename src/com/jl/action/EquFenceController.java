package com.jl.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jl.mybean.EquFence;
import com.jl.service.EquFenceService;

@Controller
@RequestMapping("/equFence")
public class EquFenceController {
	private static Logger logger = Logger.getLogger(EquFenceController.class);
	//
	@Autowired
	private EquFenceService equFenceService; // 等价于spring传统注入方式写get和set方法，这样的好处是简洁工整，省去了不必要得代码

	
	@ResponseBody
	@RequestMapping("/update/{equId}")
	public String setEquFence(@PathVariable int equId,String name, String location,
			String unit, HttpServletRequest request) {
		System.out.println(name);
		EquFence record = new EquFence();
		record=equFenceService.get(equId);
		// record.setId(1);
		record.setDeleted((short) 1);
		record.setLocation(location);
		record.setModifyTime(new Date());
		record.setName(name);
		record.setState((short) 1);
		record.setUnit(unit);
		int version =record.getVersion()+1;
		record.setVersion(version);

		try {
			equFenceService.updateEquFence(record);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
			return "fail";
		}

	}
	@ResponseBody
	@RequestMapping("/add")
	public String addEquFence(int userid, String name, String location,
			String unit, HttpServletRequest request) {
		System.out.println(name);
		EquFence record = new EquFence();
		// record.setId(1);
		record.setCreateTime(new Date());
		record.setDeleted((short) 1);
		record.setLocation(location);
		record.setModifyTime(new Date());
		record.setName(name);
		record.setState((short) 1);
		record.setUnit(unit);
		record.setUsersId(userid);
		record.setVersion(1);

		try {
			equFenceService.insert(record);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
			return "fail";
		}

	}
	@ResponseBody
	@RequestMapping(value="/getEquFence",produces = "application/json")
	public EquFence getEquFence(int equId, HttpServletRequest request, HttpServletResponse response) {
		EquFence record = new EquFence();

		record = equFenceService.get(equId);
		//ObjectMapper mapper = new ObjectMapper();
		return record;

		//JsonUtils.renderJson(response,record);
		
	}

	@RequestMapping("/getUserEquFences")
	public @ResponseBody
	List<EquFence>  getUserEquFences(int userId, HttpServletRequest request) {
		List<EquFence> records = new ArrayList<EquFence>();
		records = equFenceService.getEquFencesByUserId(userId);
		return records;
	}
}
