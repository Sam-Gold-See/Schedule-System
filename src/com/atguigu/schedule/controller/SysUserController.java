package com.atguigu.schedule.controller;

import com.atguigu.schedule.pojo.SysUser;
import com.atguigu.schedule.service.SysUserService;
import com.atguigu.schedule.service.impl.SysUserServiceImpl;
import com.atguigu.schedule.util.MD5Util;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/*")
public class SysUserController extends BaseController {

	private SysUserService userService = new SysUserServiceImpl();

	/**
	 * 接收用户登录请求的业务处理方法（业务接口）
	 * @param req HttpServletRequest 对象，用于获取请求中的参数
	 * @param resp HttpServletResponse 对象，用于发送响应
	 * @throws IOException 当发生I/O错误时抛出
	 */
	protected void login(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		//1 接收用户名和密钥
		String username = req.getParameter("username");
		String userPwd = req.getParameter("userPwd");
		//2 调用服务层方法，根据用户名查询用户信息
		SysUser loginUser = userService.findByUsername(username);
		if(loginUser == null)
			//跳转到用户名有误提示页
			resp.sendRedirect("/loginUsernameError.html");
		//3 判断密码是否匹配
		else if(!MD5Util.encrypt(userPwd).equals(loginUser.getUserPwd()))
			resp.sendRedirect("/loginUserPwdError.html");
		else
		//4 跳转到首页
			resp.sendRedirect("/showSchedule.html");
	}

	/**
	 * 接收用户注册请求的业务处理方法（业务接口）
	 * @param req HttpServletRequest 对象，用于获取请求中的参数
	 * @param resp HttpServletResponse 对象，用于发送响应
	 * @throws IOException 当发生I/O错误时抛出
	 */
	protected void regist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 1 接收客户端提交的参数
		String username = req.getParameter("username");
		String userPwd = req.getParameter("userPwd");
		// 2 调用服务层方法，完成注册功能
			// 将参数放入一个SysUser对象中，在调用regist方法时传入
		SysUser sysUser = new SysUser(null,username,userPwd);
		int rows = userService.regist(sysUser);
		// 3 根据注册结果（成功、失败）做页面跳转
		if (rows > 0)
			resp.sendRedirect("/registSuccess.html");
		else
			resp.sendRedirect("/registFail.html");
	}
}
