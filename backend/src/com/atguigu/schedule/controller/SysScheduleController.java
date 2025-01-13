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

    /**
     * 查询指定uid用户的所有日程数据
     *
     * @param req  HttpServletRequest 对象，用于获取请求中的参数
     * @param resp HttpServletResponse 对象，用于发送响应
     */
    protected void findAllSchedule(HttpServletRequest req, HttpServletResponse resp) {
        // 接收请求的uid参数
        int uid = Integer.parseInt(req.getParameter("uid"));
        // 调用Service层业务处理方法查询用户的所有日程
        List<SysSchedule> itemList = scheduleService.findItemListByUid(uid);
        // 将用户的所有日程放入一个Result对象
        Map data = new HashMap();
        data.put("itemList", itemList);
        Result result = Result.ok(data);
        // 将Result对象转为JSON响应给客户端
        WebUtil.writeJson(resp, result);
    }

    /**
     * 对指定uid用户增加默认日程的业务处理方法
     *
     * @param req  HttpServletRequest 对象，用于获取请求中的参数
     * @param resp HttpServletResponse 对象，用于发送响应
     */
    protected void addDefaultSchedule(HttpServletRequest req, HttpServletResponse resp) {
        // 接收请求的uid参数
        int uid = Integer.parseInt(req.getParameter("uid"));
        // 调用Service层业务处理方法，向数据库增加一条空记录
        scheduleService.addDefault(uid);
        // 向前端发送业务已完成的信息
        WebUtil.writeJson(resp, Result.ok(null));
    }

    /**
     * 更新用户的指定日程
     *
     * @param req  HttpServletRequest 对象，用于获取请求中的参数
     * @param resp HttpServletResponse 对象，用于发送响应
     */
    protected void updateSchedule(HttpServletRequest req, HttpServletResponse resp) {
        // 接收请求体中的JSON串转换为一个SysSchedule对象
        SysSchedule sysSchedule = WebUtil.readJson(req, SysSchedule.class);
        // 调用Service层业务处理方法，向数据库传入新的数据
        scheduleService.updateSchedule(sysSchedule);
        // 向前端发送业务已完成的信息
        WebUtil.writeJson(resp, Result.ok(null));
    }

    /**
     * 接收删除指定sid日程的业务处理方法
     *
     * @param req  HttpServletRequest 对象，用于获取请求中的参数
     * @param resp HttpServletResponse 对象，用于发送响应
     */
    protected void removeSchedule(HttpServletRequest req, HttpServletResponse resp) {
        // 接收请求体中的sid参数
        int sid = Integer.parseInt(req.getParameter("sid"));
        // 调用Service层业务处理方法，删除指定sid日程
        scheduleService.removeSchedule(sid);
        // 向前端发送业务已完成的信息
        WebUtil.writeJson(resp, Result.ok(null));
    }
}
