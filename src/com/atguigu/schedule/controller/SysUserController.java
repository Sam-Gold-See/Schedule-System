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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

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

		// 调用服务层业务处理方法查询该用户名是否有对应的用户
		SysUser sysUser = userService.findByUsername(username);

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
	 * @throws IOException 当发生I/O错误时抛出
	 */
	protected void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//1 接收用户名和密钥
		String username = req.getParameter("username");
		String userPwd = req.getParameter("userPwd");
		//2 调用服务层方法，根据用户名查询用户信息
		SysUser loginUser = userService.findByUsername(username);
		if (loginUser == null)
			//跳转到用户名有误提示页
			resp.sendRedirect("/loginUsernameError.html");
			//3 判断密码是否匹配
		else if (!MD5Util.encrypt(userPwd).equals(loginUser.getUserPwd()))
			resp.sendRedirect("/loginUserPwdError.html");
		else {
			// 登录成功之后，将登录时的用户信息放入session域对象中
			HttpSession session = req.getSession();
			session.setAttribute("sysUser", loginUser);
			//4 跳转到首页
			resp.sendRedirect("/showSchedule.html");
		}
	}

	/**
	 * 接收用户注册请求的业务处理方法（业务接口）
	 *
	 * @param req  HttpServletRequest 对象，用于获取请求中的参数
	 * @param resp HttpServletResponse 对象，用于发送响应
	 * @throws IOException 当发生I/O错误时抛出
	 */
	protected void regist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 1 接收客户端提交的参数
		String username = req.getParameter("username");
		String userPwd = req.getParameter("userPwd");
		// 2 调用服务层方法，完成注册功能
		// 将参数放入一个SysUser对象中，在调用regist方法时传入
		SysUser sysUser = new SysUser(null, username, userPwd);
		int rows = userService.regist(sysUser);
		// 3 根据注册结果（成功、失败）做页面跳转
		if (rows > 0)
			resp.sendRedirect("/registSuccess.html");
		else
			resp.sendRedirect("/registFail.html");
	}
}
