package com.atguigu.schedule.dao;

import com.atguigu.schedule.pojo.SysSchedule;

import java.util.List;

public interface SysScheduleDao {

	/**
	 * 用于向 sys_schedule 数据库中添加一条日程记录
	 *
	 * @param schedule 日程数据以SysSchedule 实体类对象形式入参
	 * @return 返回影响数据库记录的行数，行数为0说明增加失败，行数大于0说明增加成功
	 */
	int addSchedule(SysSchedule schedule);

	/**
	 *  查询所用用户的所有日程
	 * @return 将所有日程放入一个 List<SysSchedule> 集合中返回
	 */
	List<SysSchedule> findAllSchedule();
}
