package mmp.gps.protocol.kangkaisi.body;

import mmp.gps.protocol.kangkaisi.ByteIO;
import mmp.gps.protocol.kangkaisi.IPacket;

import java.math.BigInteger;

public class LoginBody implements IPacket {

    private String numberID;
    private int typeCode;
    private int timeZoneLanguage;


    public String getNumberID() {
        return this.numberID;
    }

    public void setNumberID(String numberID) {
        this.numberID = numberID;
    }

    public int getTypeCode() {
        return this.typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    public int getTimeZoneLanguage() {
        return this.timeZoneLanguage;
    }

    public void setTimeZoneLanguage(int timeZoneLanguage) {
        this.timeZoneLanguage = timeZoneLanguage;
    }

    public int size() {
        return 12;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        BigInteger bi = new BigInteger(io.getBytes(8));
        this.numberID = bi.toString(16);
        this.typeCode = io.getUshort();
        this.timeZoneLanguage = io.getUshort();
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        BigInteger bi = new BigInteger(this.numberID, 16);
        byte[] numbers = bi.toByteArray();
        io.put(numbers);
        io.putUshort(this.typeCode);
        io.putUshort(this.timeZoneLanguage);
        return io.array();
    }

    public String toString() {
        return "LoginBody [numberID=" + this.numberID + ", typeCode=" + this.typeCode + ", timeZoneLanguage=" + this.timeZoneLanguage + "]";
    }
}
