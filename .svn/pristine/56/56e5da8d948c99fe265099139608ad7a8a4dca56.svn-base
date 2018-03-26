package com.edata.monitor.dao.devicedata;

import com.edata.monitor.dao.locate.DeviceEventReportDto;
import com.edata.monitor.dao.locate.MultimediaEventReportDto;
import com.edata.monitor.dao.locate.MultimediaRetrievalDto;
import com.edata.monitor.dao.locate.UpgradeResultReportDto;

import java.util.Date;
import java.util.List;

public interface IDeviceDataDao {

    int queryMultimediaEventReportPageCount(String deviceNumber, Date start, Date end);

    List<MultimediaEventReportDto> queryMultimediaEventReportPageDetail(String deviceNumber, Date start, Date end,
                                                                        int pageIndex, int pageSize);

    int queryMultimediaDataRetrievalPageCount(String deviceNumber, Date start, Date end);

    List<MultimediaRetrievalDto> queryMultimediaDataRetrievalPageDetail(String deviceNumber, Date start, Date end,
                                                                        int pageIndex, int pageSize);

    int queryDeviceEventReportPageCount(String deviceNumber, Date start, Date end);

    List<DeviceEventReportDto> queryDeviceEventReportPageDetail(String deviceNumber, Date start, Date end, int
            pageIndex, int pageSize);

    int queryDeviceUpgradeResultReportPageCount(String deviceNumber, Date start, Date end);

    List<UpgradeResultReportDto> queryDeviceUpgradeResultReportPageDetail(String deviceNumber, Date start, Date end,
                                                                          int pageIndex, int pageSize);

    String getDriverName(String licenses);
}
