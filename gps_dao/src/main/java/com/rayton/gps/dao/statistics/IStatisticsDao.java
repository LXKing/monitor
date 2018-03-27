package com.rayton.gps.dao.statistics;

import java.util.Date;
import java.util.List;

public interface IStatisticsDao {

    boolean historyOnlineOffline(String number, Date start, Date end);

    int historyOnlineTime(String number, Date start, Date end);

    List<MileageOilDto> mileageOilRecords(String number, Date start, Date end);
}
