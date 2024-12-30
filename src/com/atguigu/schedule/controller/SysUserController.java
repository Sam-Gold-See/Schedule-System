package com.atguigu.schedule.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/*")
public class SysUserController extends BaseController {
	protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("add");
	}
}
