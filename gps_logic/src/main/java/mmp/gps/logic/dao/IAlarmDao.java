package mmp.gps.logic.dao;

import mmp.gps.domain.alarm.AlarmDto;

import java.util.Date;
import java.util.List;

public interface IAlarmDao {
    int loadPageCount(String number, Date start, Date end);

    List<AlarmDto> loadPageDetail(String number, Date start, Date end, int pageIndex, int pageSize) throws Exception;

}
