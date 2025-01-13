package com.atguigu.schedule.service;

import com.atguigu.schedule.pojo.SysSchedule;

import java.util.List;

/**
 * 该接口定义了以 sys_schedule 表格为核心的业务处理功能
 */

public interface SysScheduleService {
	List<SysSchedule> findItemListByUid(int uid);

	void addDefault(int uid);

	void updateSchedule(SysSchedule schedule);

	void removeSchedule(int sid);
}
