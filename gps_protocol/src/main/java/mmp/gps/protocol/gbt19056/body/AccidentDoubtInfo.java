package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.BytesUtil;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class AccidentDoubtInfo implements IPacket {

    private DrivingTime end = new DrivingTime();
    private String license;
    private byte[] content = new byte[200];
    private Locate locate = new Locate();


    public DrivingTime getEnd() {
        return this.end;
    }

    public void setEnd(DrivingTime end) {
        this.end = end;
    }

    public String getLicense() {
        return this.license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public byte[] getContent() {
        return this.content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Locate getLocate() {
        return this.locate;
    }

    public void setLocate(Locate locate) {
        this.locate = locate;
    }

    public int size() {
        return 234;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.end.from(io.getBytes(6));
        this.license = (new String(io.getBytes(18), Charsets.ASCII)).trim();
        io.get(this.content);
        this.locate.from(io.getBytes(10));
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.put(this.end.array());
        io.put(BytesUtil.fixed(this.license, Charsets.ASCII, 18));
        io.put(this.content);
        io.put(this.locate.array());
        return data;
    }
}
