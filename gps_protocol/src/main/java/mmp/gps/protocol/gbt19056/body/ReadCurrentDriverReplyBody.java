package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.BytesUtil;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class ReadCurrentDriverReplyBody implements IPacket {

    private String license;


    public String getLicense() {
        return this.license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int size() {
        return 18;
    }

    public void from(byte[] src) {
        this.license = (new String(src, Charsets.GB2312)).trim();
    }

    public byte[] array() {
        return BytesUtil.fixed(this.license, Charsets.GB2312, this.size());
    }
}
