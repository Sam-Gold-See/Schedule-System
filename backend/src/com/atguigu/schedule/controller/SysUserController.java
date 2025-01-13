package com.atguigu.schedule.controller;

import com.atguigu.schedule.common.Result;
import com.atguigu.schedule.common.ResultCodeEnum;
import com.atguigu.schedule.pojo.SysUser;
import com.atguigu.schedule.service.SysUserService;
import com.atguigu.schedule.service.impl.SysUserServiceImpl;
import com.atguigu.schedule.util.MD5Util;
import com.atguigu.schedule.util.WebUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class SysUserController extends BaseController {

    private SysUserService userService = new SysUserServiceImpl();

    /**
     * 注册时，接收要注册的用户名，校验用户名是否被占用的业务接口
     *
     * @param req  HttpServletRequest 对象，用于获取请求中的参数
     * @param resp HttpServletResponse 对象，用于发送响应
     */
    protected void checkUsernameUsed(HttpServletRequest req, HttpServletResponse resp) {
        // 接收用户名
        String username = req.getParameter("username");
        // 调用Service层业务处理方法查询该用户名是否有对应的用户
        SysUser sysUser = userService.findByUsername(username);
        // 根据查询返回的SysUser对象是否有值
        Result result = Result.ok(null);
        if (null != sysUser) {
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        // 将result对象转换为JSON串响应给客户端
        WebUtil.writeJson(resp, result);
    }

    /**
     * 接收用户登录请求的业务处理方法（业务接口）
     *
     * @param req  HttpServletRequest 对象，用于获取请求中的参数
     * @param resp HttpServletResponse 对象，用于发送响应
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) {
        //1 接收用户名和密钥
        SysUser sysUser = WebUtil.readJson(req, SysUser.class);
        //2 调用服务层方法，根据用户名查询用户信息
        SysUser loginUser = userService.findByUsername(sysUser.getUsername());

        Result result;

        if (loginUser == null)
            result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        else if (!MD5Util.encrypt(sysUser.getUserPwd()).equals(loginUser.getUserPwd()))
            result = Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        else {
            Map data = new HashMap();
            loginUser.setUserPwd("");
            data.put("loginUser", loginUser);
            result = Result.ok(data);
        }

        //3 将登陆结果响应给客户端
        WebUtil.writeJson(resp, result);
    }

    /**
     * 接收用户注册请求的业务处理方法（业务接口）
     *
     * @param req  HttpServletRequest 对象，用于获取请求中的参数
     * @param resp HttpServletResponse 对象，用于发送响应
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) {
        // 1 接收客户端提交的JSON参数，并转换为User对象，获取信息
        SysUser registUser = WebUtil.readJson(req, SysUser.class);
        // 2 调用服务层方法，完成注册功能
        int rows = userService.regist(registUser);
        // 3 根据注册结果（成功、失败）做页面跳转
        Result result = Result.ok(null);
        if (rows < 1)
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        WebUtil.writeJson(resp, result);
    }
}
