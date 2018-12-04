package mmp.gps.protocol.kangkaisi.body;

import mmp.gps.protocol.kangkaisi.ByteIO;
import mmp.gps.protocol.kangkaisi.IPacket;
import mmp.gps.protocol.kangkaisi.definition.TerminalInformationContent;

public class AlarmDataBody implements IPacket {

    private GpsTime time;
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
    private short alarm;
    private short language;
    private Long mileage;
    private TerminalInformationContent terminalInformationContentclass;


    public GpsTime getTime() {
        return this.time;
    }

    public void setTime(GpsTime time) {
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
        this.voltageLevel = voltageLevel;
    }

    public short getGsm() {
        return this.gsm;
    }

    public void setGsm(short gsm) {
        this.gsm = gsm;
    }

    public short getAlarm() {
        return this.alarm;
    }

    public void setAlarm(short alarm) {
        this.alarm = alarm;
    }

    public short getLanguage() {
        return this.language;
    }

    public void setLanguage(short language) {
        this.language = language;
    }

    public Long getMileage() {
        return this.mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public TerminalInformationContent getTerminalInformationContentclass() {
        return this.terminalInformationContentclass;
    }

    public void setTerminalInformationContentclass(TerminalInformationContent terminalInformationContentclass) {
        this.terminalInformationContentclass = terminalInformationContentclass;
    }

    public String toString() {
        return "AlarmDataBody [time=" + this.time + ", gpsSum=" + this.gpsSum + ", lat=" + this.lat + ", lng=" + this.lng + ", speed=" + this.speed + ", state=" + this.state + ", lbs=" + this.lbs + ", mcc=" + this.mcc + ", mnc=" + this.mnc + ", lac=" + this.lac + ", cell=" + this.cell + ", terminalInformationContent=" + this.terminalInformationContent + ", voltageLevel=" + this.voltageLevel + ", gsm=" + this.gsm + ", alarm=" + this.alarm + ", language=" + this.language + ", mileage=" + this.mileage + ", terminalInformationContentclass=" + this.terminalInformationContentclass + "]";
    }

    public int size() {
        return 36;
    }

    public void from(byte[] src) {
        this.terminalInformationContentclass = new TerminalInformationContent();
        int l = src.length;
        ByteIO io = new ByteIO(src);
        this.time = new GpsTime();
        this.time.from(io.getBytes(6));
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
        this.terminalInformationContentclass.from(this.terminalInformationContent);
        this.voltageLevel = io.getUbyte();
        this.gsm = io.getUbyte();
        this.alarm = io.getUbyte();
        this.language = io.getUbyte();
        if (l == this.size()) {
            this.mileage = Long.valueOf(io.getUint());
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.put(this.time.array());
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
        io.putUbyte(this.alarm);
        io.putUbyte(this.language);
        io.putUint(this.mileage.longValue());
        return io.array();
    }
}
