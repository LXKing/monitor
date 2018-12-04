package mmp.gps.logic.dao;

import mmp.gps.domain.statistics.MileageOilDto;

import java.util.Date;
import java.util.List;

public interface IStatisticsDao {

    boolean historyOnlineOffline(String number, Date start, Date end);

    Integer historyOnlineTime(String number, Date start, Date end);

    List<MileageOilDto> mileageOilRecords(String number, Date start, Date end);
}
