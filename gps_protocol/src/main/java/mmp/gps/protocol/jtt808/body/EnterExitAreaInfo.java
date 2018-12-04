package mmp.gps.protocol.jtt808.body;


public class EnterExitAreaInfo {

    private byte type;
    private long areaId;
    private byte flag;


    public byte getType() {
        return this.type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public long getAreaId() {
        return this.areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public byte getFlag() {
        return this.flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }
}
