package mmp.gps.logic.contract;

import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.gateway.RawData;

public interface IRawDataParser {

    int getProtocolKind();

    void parse(RawData var1, BulkDataDto var2);
}
