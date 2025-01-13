package com.atguigu.schedule.service.impl;

import com.atguigu.schedule.dao.SysScheduleDao;
import com.atguigu.schedule.dao.impl.SysScheduleDaoImpl;
import com.atguigu.schedule.pojo.SysSchedule;
import com.atguigu.schedule.service.SysScheduleService;

import java.util.List;

public class SysScheduleServiceImpl implements SysScheduleService {
    private SysScheduleDao scheduleDao = new SysScheduleDaoImpl();

    @Override
    public List<SysSchedule> findItemListByUid(int uid) {
        return scheduleDao.findItemListByUid(uid);
    }

    @Override
    public void addDefault(int uid) {
        scheduleDao.addDefault(uid);
    }

    @Override
    public void updateSchedule(SysSchedule schedule) {
        scheduleDao.updateSchedule(schedule);
    }

    @Override
    public void removeSchedule(int sid) {
        scheduleDao.removeSchedule(sid);
    }
}
