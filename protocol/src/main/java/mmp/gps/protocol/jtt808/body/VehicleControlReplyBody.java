package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class VehicleControlReplyBody implements IPacket {

    private int responseSerialNumber;
    private LocationInformationReportBody locateInfo;


    public int getResponseSerialNumber() {
        return this.responseSerialNumber;
    }

    public void setResponseSerialNumber(int responseSerialNumber) {
        this.responseSerialNumber = responseSerialNumber;
    }

    public LocationInformationReportBody getLocateInfo() {
        return this.locateInfo;
    }

    public void setLocateInfo(LocationInformationReportBody locateInfo) {
        this.locateInfo = locateInfo;
    }

    public int size() {
        return 2 + (this.locateInfo == null ? 0 : this.locateInfo.size());
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.responseSerialNumber = io.getUshort();
        this.locateInfo = new LocationInformationReportBody();
        this.locateInfo.from(io.getRemaining());
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.putUshort(this.responseSerialNumber);
        io.put(this.locateInfo.array());
        return data;
    }
}
