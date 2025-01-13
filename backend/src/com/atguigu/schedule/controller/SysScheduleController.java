package com.atguigu.schedule.controller;

import com.atguigu.schedule.common.Result;
import com.atguigu.schedule.pojo.SysSchedule;
import com.atguigu.schedule.service.SysScheduleService;
import com.atguigu.schedule.service.impl.SysScheduleServiceImpl;
import com.atguigu.schedule.util.WebUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/schedule/*")
public class SysScheduleController extends BaseController {
	private SysScheduleService scheduleService = new SysScheduleServiceImpl();

	protected void findAllSchedule(HttpServletRequest req, HttpServletResponse resp) {
		// 接收请求的uid参数
		int uid = Integer.parseInt(req.getParameter("uid"));
		// 查询用户的所有日程
		List<SysSchedule> itemList = scheduleService.findItemListByUid(uid);
		// 将用户的所有日程放入一个Result对象
		Map data = new HashMap();
		data.put("itemList", itemList);
		Result result = Result.ok(data);
		// 将Result对象转为json响应给客户端
		WebUtil.writeJson(resp, result);
	}

	protected void addDefaultSchedule(HttpServletRequest req, HttpServletResponse resp) {
		// 接收请求的uid参数
		int uid = Integer.parseInt(req.getParameter("uid"));
		// 调用服务层方法，向数据库增加一条空记录
		scheduleService.addDefault(uid);

		WebUtil.writeJson(resp,Result.ok(null));
	}

	protected void updateSchedule(HttpServletRequest req, HttpServletResponse resp) {
		// 接收请求体中的JSON串转换为一个SysSchedule对象
		SysSchedule sysSchedule = WebUtil.readJson(req, SysSchedule.class);
		// 调用服务层方法
		scheduleService.updateSchedule(sysSchedule);

		WebUtil.writeJson(resp,Result.ok(null));
	}

	protected void removeSchedule (HttpServletRequest req, HttpServletResponse resp) {
		// 接收请求体中的sid参数
		int sid = Integer.parseInt(req.getParameter("sid"));
		// 调用服务层方法
		scheduleService.removeSchedule(sid);

		WebUtil.writeJson(resp,Result.ok(null));
	}
}
