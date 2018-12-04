package mmp.gps.protocol.kangkaisi.body;

import mmp.gps.protocol.kangkaisi.ByteIO;
import mmp.gps.protocol.kangkaisi.IPacket;

import java.math.BigInteger;

public class AlarmDataMultiFenceBody implements IPacket {

    private String time;
    private short gpsSum;
    private long lat;
    private long lng;
    private short speed;
    private int state;
    private short lbs;
    private int mcc;
    private short mnc;
    private int lac;
    private int cell;
    private short terminalInformationContent;
    private short voltageLevel;
    private short gsm;
    private int language;
    private short fencingNumber;
    private long mileage;


    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public short getGpsSum() {
        return this.gpsSum;
    }

    public void setGpsSum(short gpsSum) {
        this.gpsSum = gpsSum;
    }

    public long getLat() {
        return this.lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLng() {
        return this.lng;
    }

    public void setLng(long lng) {
        this.lng = lng;
    }

    public short getSpeed() {
        return this.speed;
    }

    public void setSpeed(short speed) {
        this.speed = speed;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public short getLbs() {
        return this.lbs;
    }

    public void setLbs(short lbs) {
        this.lbs = lbs;
    }

    public int getMcc() {
        return this.mcc;
    }

    public void setMcc(int mcc) {
        this.mcc = mcc;
    }

    public short getMnc() {
        return this.mnc;
    }

    public void setMnc(short mnc) {
        this.mnc = mnc;
    }

    public int getLac() {
        return this.lac;
    }

    public void setLac(int lac) {
        this.lac = lac;
    }

    public int getCell() {
        return this.cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public short getTerminalInformationContent() {
        return this.terminalInformationContent;
    }

    public void setTerminalInformationContent(short terminalInformationContent) {
        this.terminalInformationContent = terminalInformationContent;
    }

    public short getVoltageLevel() {
        return this.voltageLevel;
    }

    public void setVoltageLevel(short voltageLevel) {
    }

    public short getGSM() {
        return this.gsm;
    }

    public void setGSM(short gsm) {
    }

    public int getLanguage() {
        return this.language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public short getFencingNumber() {
        return this.fencingNumber;
    }

    public void setFencingNumber(short fencingNumber) {
    }

    public long getMileage() {
        return this.mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public String toString() {
        return "AlarmDataMultiFenceBody [time=" + this.time + ", gpsSum=" + this.gpsSum + ", lat=" + this.lat + ", lng=" + this.lng + ", speed=" + this.speed + ", state=" + this.state + ", lbs=" + this.lbs + ", mcc=" + this.mcc + ", mnc=" + this.mnc + ", lac=" + this.lac + ", cell=" + this.cell + ", terminalInformationContent=" + this.terminalInformationContent + ", VoltageLevel=" + this.voltageLevel + ", GSM=" + this.gsm + ", language=" + this.language + ", FencingNumber=" + this.fencingNumber + ", mileage=" + this.mileage + "]";
    }

    public int size() {
        return 37;
    }

    public void from(byte[] src) {
        int l = src.length;
        ByteIO io = new ByteIO(src);
        BigInteger bi = new BigInteger(io.getBytes(6));
        this.time = bi.toString(16);
        this.gpsSum = io.getUbyte();
        this.lat = io.getUint();
        this.lng = io.getUint();
        this.speed = io.getUbyte();
        this.state = io.getUshort();
        this.lbs = io.getUbyte();
        this.mcc = io.getUshort();
        this.mnc = io.getUbyte();
        this.lac = io.getUshort();
        this.cell = io.getMint();
        this.terminalInformationContent = io.getUbyte();
        this.voltageLevel = io.getUbyte();
        this.gsm = io.getUbyte();
        this.language = io.getUshort();
        this.fencingNumber = io.getUbyte();
        if (l == this.size()) {
            this.mileage = io.getUint();
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        BigInteger bi = new BigInteger(this.time, 16);
        byte[] numbers = bi.toByteArray();
        io.put(numbers);
        io.putUbyte(this.gpsSum);
        io.putUint(this.lat);
        io.putUint(this.lng);
        io.putUbyte(this.speed);
        io.putUshort(this.state);
        io.putUbyte(this.lbs);
        io.putUshort(this.mcc);
        io.putUbyte(this.mnc);
        io.putUshort(this.lac);
        io.putMint(this.cell);
        io.putUbyte(this.terminalInformationContent);
        io.putUbyte(this.voltageLevel);
        io.putUbyte(this.gsm);
        io.putUshort(this.language);
        io.putUbyte(this.fencingNumber);
        io.putUint(this.mileage);
        return io.array();
    }
}
