package mmp.gps.protocol.jtt808.body;


public class OverspeedAddonInfo {

    private byte type;
    private Long areaId;


    public byte getType() {
        return this.type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public Long getAreaId() {
        return this.areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }
}
