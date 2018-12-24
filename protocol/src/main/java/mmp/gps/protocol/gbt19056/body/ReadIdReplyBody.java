package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.BytesUtil;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class ReadIdReplyBody implements IPacket {

    private String ccc;
    private String model;
    private byte year;
    private byte month;
    private byte day;
    private long sn;
    private byte[] spares = new byte[5];


    public String getCcc() {
        return this.ccc;
    }

    public void setCcc(String ccc) {
        this.ccc = ccc;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public byte getYear() {
        return this.year;
    }

    public void setYear(byte year) {
        this.year = year;
    }

    public byte getMonth() {
        return this.month;
    }

    public void setMonth(byte month) {
        this.month = month;
    }

    public byte getDay() {
        return this.day;
    }

    public void setDay(byte day) {
        this.day = day;
    }

    public long getSn() {
        return this.sn;
    }

    public void setSn(long sn) {
        this.sn = sn;
    }

    public byte[] getSpares() {
        return this.spares;
    }

    public void setSpares(byte[] spares) {
        this.spares = spares;
    }

    public int size() {
        return 35;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.ccc = (new String(io.getBytes(7), Charsets.ASCII)).trim();
        this.model = (new String(io.getBytes(16), Charsets.ASCII)).trim();
        this.year = io.get();
        this.month = io.get();
        this.day = io.get();
        this.sn = io.getUint();
        this.spares = io.getBytes(5);
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.put(BytesUtil.fixed(this.ccc, Charsets.ASCII, 7));
        io.put(BytesUtil.fixed(this.model, Charsets.ASCII, 16));
        io.put(this.year);
        io.put(this.month);
        io.put(this.day);
        io.putUint(this.sn);
        io.put(this.spares);
        return data;
    }
}
