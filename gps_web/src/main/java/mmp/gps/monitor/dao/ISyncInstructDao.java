package mmp.gps.monitor.dao;

import mmp.gps.domain.instruct.DeviceInAreaInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ISyncInstructDao {

    List<DeviceInAreaInfoDto> loadUnfinishedAreaInDevice();

    void updateLog(String serialNumber);

}
