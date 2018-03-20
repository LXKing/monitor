package com.edata.monitor.dao.instruct;

import java.util.List;

public interface ISyncInstructDao {

	List<DeviceInAreaInfoDto> loadUnfinishedAreaInDevice();

	void updateLog(String serialNumber);

}
