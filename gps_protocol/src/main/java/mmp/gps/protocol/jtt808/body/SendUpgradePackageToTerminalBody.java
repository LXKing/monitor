package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class SendUpgradePackageToTerminalBody implements IPacket {

    private byte type;
    private byte[] factoryId;
    private byte verSize;
    private String ver;
    private long dataSize;
    private byte[] data;


    public byte getType() {
        return this.type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte[] getFactoryId() {
        return this.factoryId;
    }

    public void setFactoryId(byte[] factoryId) {
        this.factoryId = factoryId;
    }

    public byte getVerSize() {
        return this.verSize;
    }

    public void setVerSize(byte verSize) {
        this.verSize = verSize;
    }

    public String getVer() {
        return this.ver;
    }

    public void setVer(String ver) {
        this.verSize = (byte) (ver == null ? 0 : ver.getBytes(Charsets.GBK).length);
        this.ver = ver;
    }

    public long getDataSize() {
        return this.dataSize;
    }

    public void setDataSize(long dataSize) {
        this.dataSize = dataSize;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.dataSize = (long) data.length;
        this.data = data;
    }

    public int size() {
        return (int) ((long) (7 + this.verSize + 4) + this.dataSize);
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.type = io.get();
        this.factoryId = io.getBytes(5);
        this.verSize = io.get();
        if (this.verSize > 0) {
            this.ver = new String(io.getBytes(this.verSize), Charsets.GBK);
        }

        this.dataSize = io.getUint();
        this.data = io.getBytes((int) this.dataSize);
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.put(this.type);
        io.put(this.factoryId);
        io.put(this.verSize);
        io.put(this.ver.getBytes(Charsets.GBK));
        io.putUint(this.dataSize);
        io.put(this.data);
        return io.array();
    }
}
