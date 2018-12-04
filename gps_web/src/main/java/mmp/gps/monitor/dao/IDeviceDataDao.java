package mmp.gps.monitor.dao;

import mmp.gps.domain.locate.DeviceEventReportDto;
import mmp.gps.domain.locate.MultimediaEventReportDto;
import mmp.gps.domain.locate.MultimediaRetrievalDto;
import mmp.gps.domain.locate.UpgradeResultReportDto;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface IDeviceDataDao {

    int queryMultimediaEventReportPageCount(String deviceNumber, Date start, Date end);

    List<MultimediaEventReportDto> queryMultimediaEventReportPageDetail(String deviceNumber, Date start, Date end, int pageIndex, int pageSize);

    int queryMultimediaDataRetrievalPageCount(String deviceNumber, Date start, Date end);

    List<MultimediaRetrievalDto> queryMultimediaDataRetrievalPageDetail(String deviceNumber, Date start, Date end, int pageIndex, int pageSize);

    int queryDeviceEventReportPageCount(String deviceNumber, Date start, Date end);

    List<DeviceEventReportDto> queryDeviceEventReportPageDetail(String deviceNumber, Date start, Date end, int pageIndex, int pageSize);

    int queryDeviceUpgradeResultReportPageCount(String deviceNumber, Date start, Date end);

    List<UpgradeResultReportDto> queryDeviceUpgradeResultReportPageDetail(String deviceNumber, Date start, Date end, int pageIndex, int pageSize);

    String getDriverName(String licenses);
}
