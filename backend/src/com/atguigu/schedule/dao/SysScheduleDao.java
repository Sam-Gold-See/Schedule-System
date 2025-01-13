package com.atguigu.schedule.dao;

import com.atguigu.schedule.pojo.SysSchedule;

import java.util.List;

public interface SysScheduleDao {

    /**
     * 用于向 sys_schedule 数据库中添加一条日程记录的方法
     *
     * @param schedule 日程数据以SysSchedule 实体类对象形式入参
     * @return 返回影响数据库记录的行数，行数为0说明增加失败，行数大于0说明增加成功
     */
    int addSchedule(SysSchedule schedule);

    /**
     * 查询所有用户的所有日程的方法
     *
     * @return 将所有日程放入一个 List<SysSchedule> 集合中返回
     */
    List<SysSchedule> findAllSchedule();

    /**
     * 查询指定uid用户的所有日程的方法
     * @param uid 指定用户的uid号
     * @return list 返回该uid用户的所有存储日程
     */
    List<SysSchedule> findItemListByUid(int uid);

    /**
     * 增加指定uid用户的默认日程的方法
     *
     * @param uid 指定用户的uid号
     */
    void addDefault(int uid);

    /**
     * 更新指定日程数据的方法
     *
     * @param schedule 需要更新的Schedule对象
     */
    void updateSchedule(SysSchedule schedule);

    /**
     * 删除指定sid日程的方法
     *
     * @param sid 需要删除日程的sid
     */
    void removeSchedule(int sid);
}
