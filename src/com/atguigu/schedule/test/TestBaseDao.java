package com.atguigu.schedule.test;

import com.atguigu.schedule.dao.BaseDao;
import com.atguigu.schedule.pojo.SysUser;
import org.junit.*;

import java.util.List;

public class TestBaseDao {

	private static BaseDao baseDao;

	@BeforeClass
	public static void initBaseDao() {
		baseDao = new BaseDao();
	}

	@Test
	public void testBaseQueryObject() {
		//查询用户数量
		String sql = "SELECT COUNT(2) FROM sys_user";
		Long count = baseDao.baseQueryObject(Long.class, sql);
		System.out.println(count);
	}

	@Test
	public void testBaseQuery(){
		String sql = "SELECT `uid`, `username`, `user_pwd` AS `userPwd` FROM sys_user";
		List<SysUser> sysUserList = baseDao.baseQuery(SysUser.class, sql);
		sysUserList.forEach(System.out::println);
	}

	@Test
	public void testBaseUpdate(){
		String sql = "INSERT INTO sys_schedule values(DEFAULT,?,?,?)";
		int rows = baseDao.baseUpdate(sql,1,"学习Java",0);
		System.out.println(rows);
	}
}
