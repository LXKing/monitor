package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class LocationInformationReportBody implements IPacket {

    private LocationInformationBaseInfo baseInfo;
    private LocationInformationAddonInfo addonInfo;


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
        return (this.baseInfo == null ? 0 : this.baseInfo.size()) + (this.addonInfo == null ? 0 : this.addonInfo.size());
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        byte[] baseData = io.getBytes(28);
        this.baseInfo = new LocationInformationBaseInfo();
        this.baseInfo.from(baseData);
        if (io.hasRemaining()) {
            byte[] addonData = io.getRemaining();
            this.addonInfo = new LocationInformationAddonInfo();
            this.addonInfo.from(addonData);
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        if (this.baseInfo != null) {
            io.put(this.baseInfo.array());
        }

        if (this.addonInfo != null) {
            io.put(this.addonInfo.array());
        }

        return io.array();
    }
}
