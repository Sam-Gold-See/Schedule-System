package com.atguigu.schedule.dao.impl;

import com.atguigu.schedule.dao.BaseDao;
import com.atguigu.schedule.dao.SysScheduleDao;
import com.atguigu.schedule.pojo.SysSchedule;

import java.util.List;

public class SysScheduleDaoImpl extends BaseDao implements SysScheduleDao {
	@Override
	public int addSchedule(SysSchedule schedule){
		String sql = "INSERT INTO sys_schedule values(DEFAULT,?,?,?)";
		int rows = baseUpdate(sql, schedule.getUid(), schedule.getTitle(), schedule.getCompleted());
		return rows;
	}

	@Override
	public List<SysSchedule> findAllSchedule() {
		String sql = "SELECT `sid`, `uid`, `title`, `completed` FROM sys_schedule";
		List<SysSchedule> scheduleList = baseQuery(SysSchedule.class, sql);
		return scheduleList;
	}

	@Override
	public List<SysSchedule> findItemListByUid(int uid) {
		String sql = "SELECT `sid`, `uid`, `title`, `completed` FROM `sys_schedule` WHERE `uid` = ?";
		List<SysSchedule> itemList = baseQuery(SysSchedule.class, sql, uid);
		return itemList;
	}

	@Override
	public void addDefault(int uid) {
		String sql = "INSERT INTO `sys_schedule` VALUES (DEFAULT,?,'请输入日常',0)";
		baseUpdate(sql, uid);
	}

	@Override
	public void updateSchedule(SysSchedule schedule) {
		String sql = "UPDATE `sys_schedule` SET `title` = ?, `completed` = ? WHERE `sid` = ?";
		baseUpdate(sql, schedule.getTitle(), schedule.getCompleted(), schedule.getSid());
	}

	@Override
	public void removeSchedule(int sid) {
		String sql = "DELETE FROM `sys_schedule` WHERE `sid` = ?";
		baseUpdate(sql, sid);
	}
}
