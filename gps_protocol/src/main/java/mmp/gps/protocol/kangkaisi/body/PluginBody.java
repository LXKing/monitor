package mmp.gps.protocol.kangkaisi.body;

import mmp.gps.protocol.kangkaisi.ByteIO;
import mmp.gps.protocol.kangkaisi.IPacket;

public class PluginBody implements IPacket {

    private short code;
    private byte[] data;


    public int size() {
        return 1 + this.data.length;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.code = io.getUbyte();
        this.data = io.getBytes(src.length - 1);
    }

    public byte[] array() {
        return null;
    }
}
