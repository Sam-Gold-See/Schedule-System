package com.atguigu.schedule.dao.impl;

import com.atguigu.schedule.dao.BaseDao;
import com.atguigu.schedule.dao.SysUserDao;
import com.atguigu.schedule.pojo.SysUser;

import java.util.List;

public class SysUserDaoImpl extends BaseDao implements SysUserDao {

	@Override
	public int addSysUser(SysUser sysUser) {
		String sql = "INSERT INTO `sys_user` VALUES(DEFAULT,?,?)";
		return baseUpdate(sql, sysUser.getUsername(), sysUser.getUserPwd());
	}

	@Override
	public SysUser findByUsername(String username) {
		String sql = "SELECT `uid`, `username`, `user_pwd` AS `userPwd` FROM `sys_user` WHERE `username` = ?";
		List<SysUser> sysUserList = baseQuery(SysUser.class, sql, username);
		return sysUserList != null && !sysUserList.isEmpty() ? sysUserList.get(0) : null;
	}
}