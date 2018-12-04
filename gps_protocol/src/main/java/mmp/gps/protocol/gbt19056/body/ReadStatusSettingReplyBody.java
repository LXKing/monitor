package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.BytesUtil;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class ReadStatusSettingReplyBody implements IPacket {

    private DrivingTime time = new DrivingTime();
    private short flag;
    private String name0;
    private String name1;
    private String name2;
    private String name3;
    private String name4;
    private String name5;
    private String name6;
    private String name7;


    public DrivingTime getTime() {
        return this.time;
    }

    public void setTime(DrivingTime time) {
        this.time = time;
    }

    public short getFlag() {
        return this.flag;
    }

    public void setFlag(short flag) {
        this.flag = flag;
    }

    public String getName0() {
        return this.name0;
    }

    public void setName0(String name0) {
        this.name0 = name0;
    }

    public String getName1() {
        return this.name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return this.name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return this.name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getName4() {
        return this.name4;
    }

    public void setName4(String name4) {
        this.name4 = name4;
    }

    public String getName5() {
        return this.name5;
    }

    public void setName5(String name5) {
        this.name5 = name5;
    }

    public String getName6() {
        return this.name6;
    }

    public void setName6(String name6) {
        this.name6 = name6;
    }

    public String getName7() {
        return this.name7;
    }

    public void setName7(String name7) {
        this.name7 = name7;
    }

    public int size() {
        return 87;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.time.from(io.getBytes(6));
        this.flag = io.getUbyte();
        this.name0 = (new String(io.getBytes(10), Charsets.GB2312)).trim();
        this.name1 = (new String(io.getBytes(10), Charsets.GB2312)).trim();
        this.name2 = (new String(io.getBytes(10), Charsets.GB2312)).trim();
        this.name3 = (new String(io.getBytes(10), Charsets.GB2312)).trim();
        this.name4 = (new String(io.getBytes(10), Charsets.GB2312)).trim();
        this.name5 = (new String(io.getBytes(10), Charsets.GB2312)).trim();
        this.name6 = (new String(io.getBytes(10), Charsets.GB2312)).trim();
        this.name7 = (new String(io.getBytes(10), Charsets.GB2312)).trim();
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.put(this.time.array());
        io.putUbyte(this.flag);
        io.put(BytesUtil.fixed(this.name0, Charsets.GB2312, 10));
        io.put(BytesUtil.fixed(this.name1, Charsets.GB2312, 10));
        io.put(BytesUtil.fixed(this.name2, Charsets.GB2312, 10));
        io.put(BytesUtil.fixed(this.name3, Charsets.GB2312, 10));
        io.put(BytesUtil.fixed(this.name4, Charsets.GB2312, 10));
        io.put(BytesUtil.fixed(this.name5, Charsets.GB2312, 10));
        io.put(BytesUtil.fixed(this.name6, Charsets.GB2312, 10));
        io.put(BytesUtil.fixed(this.name7, Charsets.GB2312, 10));
        return data;
    }
}
