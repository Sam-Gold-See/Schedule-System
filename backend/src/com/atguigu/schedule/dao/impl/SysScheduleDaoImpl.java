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
}
