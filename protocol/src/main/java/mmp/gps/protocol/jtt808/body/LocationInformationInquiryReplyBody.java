package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class LocationInformationInquiryReplyBody implements IPacket {

    private int responseSerialNumber;
    private LocationInformationBaseInfo baseInfo;
    private LocationInformationAddonInfo addonInfo;


    public int getResponseSerialNumber() {
        return this.responseSerialNumber;
    }

    public void setResponseSerialNumber(int responseSerialNumber) {
        this.responseSerialNumber = responseSerialNumber;
    }

    public LocationInformationBaseInfo getBaseInfo() {
        return this.baseInfo;
    }

    public void setBaseInfo(LocationInformationBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public LocationInformationAddonInfo getAddonInfo() {
        return this.addonInfo;
    }

    public void setAddonInfo(LocationInformationAddonInfo addonInfo) {
        this.addonInfo = addonInfo;
    }

    public int size() {
        return 30 + (this.addonInfo == null ? 0 : this.addonInfo.size());
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.responseSerialNumber = io.getUshort();
        this.baseInfo = new LocationInformationBaseInfo();
        this.baseInfo.from(io.getBytes(28));
        if (io.hasRemaining()) {
            this.addonInfo = new LocationInformationAddonInfo();
            this.addonInfo.from(io.getRemaining());
        }

    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.putUshort(this.responseSerialNumber);
        if (this.baseInfo != null) {
            io.put(this.baseInfo.array());
        }

        if (this.addonInfo != null) {
            io.put(this.addonInfo.array());
        }

        return data;
    }
}
