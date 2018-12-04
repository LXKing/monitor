package mmp.gps.protocol.kangkaisi.definition;

import mmp.gps.protocol.kangkaisi.ByteIO;
import mmp.gps.protocol.kangkaisi.IPacket;

import java.math.BigInteger;

public class ICCIDInformation implements IPacket {

    private String IMEI;
    private String IMSI;
    private String ICCID;


    public String getIMEI() {
        return this.IMEI;
    }

    public void setIMEI(String iMEI) {
        this.IMEI = iMEI;
    }

    public String getIMSI() {
        return this.IMSI;
    }

    public void setIMSI(String iMSI) {
        this.IMSI = iMSI;
    }

    public String getICCID() {
        return this.ICCID;
    }

    public void setICCID(String iCCID) {
        this.ICCID = iCCID;
    }

    public String toString() {
        return "ICCIDInformation [IMEI=" + this.IMEI + ", IMSI=" + this.IMSI + ", ICCID=" + this.ICCID + "]";
    }

    public int size() {
        return 26;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        BigInteger bi = new BigInteger(io.getBytes(8));
        this.IMEI = bi.toString(16);
        BigInteger bi2 = new BigInteger(io.getBytes(10));
        this.IMSI = bi2.toString(16);
        BigInteger bi3 = new BigInteger(io.getBytes(10));
        this.ICCID = bi3.toString(16);
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        BigInteger bi = new BigInteger(this.IMEI, 16);
        byte[] numbers = bi.toByteArray();
        io.put(numbers);
        BigInteger bi2 = new BigInteger(this.IMSI, 16);
        byte[] numbers2 = bi2.toByteArray();
        io.put(numbers2);
        BigInteger bi3 = new BigInteger(this.ICCID, 16);
        byte[] numbers3 = bi3.toByteArray();
        io.put(numbers3);
        return io.array();
    }
}
