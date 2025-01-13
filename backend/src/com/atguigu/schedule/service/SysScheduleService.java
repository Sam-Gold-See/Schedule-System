package com.atguigu.schedule.service;

import com.atguigu.schedule.pojo.SysSchedule;

import java.util.List;

/**
 * 该接口定义了以 sys_schedule 表格为核心的业务处理功能
 */

public interface SysScheduleService {
    List<SysSchedule> findItemListByUid(int uid);

    /**
     * 对指定uid用户对象增加默认日程的方法
     *
     * @param uid 要注册的用户名和铭文密码以Sys对象的形式接收
     */
    void addDefault(int uid);

    /**
     *  对指定日程的数据进行更新的方法
     *
     * @param schedule 更新后的数据内容（通过内置uid和sid确定对应原数据）
     */
    void updateSchedule(SysSchedule schedule);

    /**
     *  对指定sid的日程进行删除的方法
     *
     * @param sid 需要删除的制定日程的sid序号
     */
    void removeSchedule(int sid);
}
