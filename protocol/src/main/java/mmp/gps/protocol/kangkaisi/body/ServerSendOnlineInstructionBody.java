package mmp.gps.protocol.kangkaisi.body;

import mmp.gps.protocol.kangkaisi.ByteIO;
import mmp.gps.protocol.kangkaisi.IPacket;

public class ServerSendOnlineInstructionBody implements IPacket {

    private short instructionlength;
    private long serverFlag;
    private String instructionContent;
    private int language;


    public short getInstructionlength() {
        return this.instructionlength;
    }

    public void setInstructionlength(short instructionlength) {
        this.instructionlength = instructionlength;
    }

    public long getServerFlag() {
        return this.serverFlag;
    }

    public void setServerFlag(long serverFlag) {
        this.serverFlag = serverFlag;
    }

    public String getInstructionContent() {
        return this.instructionContent;
    }

    public void setInstructionContent(String instructionContent) {
        this.instructionContent = instructionContent;
    }

    public int getLanguage() {
        return this.language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public String toString() {
        return "ServerSendOnlineInstructionBody [instructionlength=" + this.instructionlength + ", serverFlag=" + this.serverFlag + ", instructionContent=" + this.instructionContent + ", language=" + this.language + "]";
    }

    public int size() {
        byte[] numbers = this.instructionContent.getBytes();
        return 7 + numbers.length;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.instructionlength = io.getUbyte();
        this.serverFlag = io.getUint();
        byte[] by = io.getBytes(src.length - 1 - 2 - 4);
        this.instructionContent = new String(by, 0, by.length);
        this.language = io.getUshort();
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        this.instructionlength = (short) (4 + this.instructionContent.getBytes().length);
        io.putUbyte(this.instructionlength);
        io.putUint(this.serverFlag);
        io.put(this.instructionContent.getBytes());
        io.putUshort(this.language);
        return io.array();
    }
}
