package mmp.gps.logic.contract;

import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.gateway.RawData;

public interface ICommandHandler {

    Object[] getKeys();

    void process(RawData var1, byte[] var2, BulkDataDto var3);
}
