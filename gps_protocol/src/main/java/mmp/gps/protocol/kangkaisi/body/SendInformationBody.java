package mmp.gps.protocol.kangkaisi.body;

import mmp.gps.protocol.kangkaisi.ByteIO;
import mmp.gps.protocol.kangkaisi.IPacket;

import java.util.Arrays;

public class SendInformationBody implements IPacket {

    private short informationType;
    private byte[] dataContent;


    public short getInformationType() {
        return this.informationType;
    }

    public void setInformationType(short informationType) {
        this.informationType = informationType;
    }

    public byte[] getDataContent() {
        return this.dataContent;
    }

    public void setDataContent(byte[] dataContent) {
        this.dataContent = dataContent;
    }

    public String toString() {
        return "SendInformationBody [informationType=" + this.informationType + ", dataContent=" + Arrays.toString(this.dataContent) + "]";
    }

    public int size() {
        return 1 + this.dataContent.length;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.informationType = io.getUbyte();
        this.dataContent = io.getBytes(src.length - 1);
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUbyte(this.informationType);
        io.put(this.dataContent);
        return io.array();
    }
}
