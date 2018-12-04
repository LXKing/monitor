package mmp.gps.protocol.kangkaisi.body;

import mmp.gps.protocol.kangkaisi.ByteIO;
import mmp.gps.protocol.kangkaisi.IPacket;
import mmp.gps.protocol.kangkaisi.StringUtils;

public class LocationBody implements IPacket {

    private GpsTime time;
    private int gpsS;
    private int gpsl;
    private short gpsSum;
    private long lat;
    private long lng;
    private short speed;
    private int state;
    private int mcc;
    private short mnc;
    private int lac;
    private int cell;
    private short acc;
    private short reportingMode;
    private short gpsRealTime;
    private Long mileageStatistics;


    public GpsTime getTime() {
        return this.time;
    }

    public void setTime(GpsTime time) {
        this.time = time;
    }

    public short getgpsSum() {
        return this.gpsSum;
    }

    public void setgPSsum(short gpsSum) {
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

    public short getAcc() {
        return this.acc;
    }

    public void setAcc(short acc) {
        this.acc = acc;
    }

    public short getReportingMode() {
        return this.reportingMode;
    }

    public void setReportingMode(short reportingMode) {
        this.reportingMode = reportingMode;
    }

    public short getGpsRealTime() {
        return this.gpsRealTime;
    }

    public void setGpsRealTime(short gpsRealTime) {
        this.gpsRealTime = gpsRealTime;
    }

    public Long getMileageStatistics() {
        return this.mileageStatistics;
    }

    public void setMileageStatistics(Long mileageStatistics) {
        this.mileageStatistics = mileageStatistics;
    }

    public short getGpsSum() {
        return this.gpsSum;
    }

    public void setGpsSum(short gpsSum) {
        this.gpsSum = gpsSum;
    }

    public int getGpsS() {
        return this.gpsS;
    }

    public void setGpsS(int gpsS) {
        this.gpsS = gpsS;
    }

    public int getGpsl() {
        return this.gpsl;
    }

    public void setGpsl(int gpsl) {
        this.gpsl = gpsl;
    }

    public String toString() {
        return "LocationBody [time=" + this.time + ", gpsS=" + this.gpsS + ", gpsl=" + this.gpsl + ", gpsSum=" + this.gpsSum + ", lat=" + this.lat + ", lng=" + this.lng + ", speed=" + this.speed + ", state=" + this.state + ", mcc=" + this.mcc + ", mnc=" + this.mnc + ", lac=" + this.lac + ", cell=" + this.cell + ", acc=" + this.acc + ", reportingMode=" + this.reportingMode + ", gpsRealTime=" + this.gpsRealTime + ", mileageStatistics=" + this.mileageStatistics + "]";
    }

    public int size() {
        return 33;
    }

    public void from(byte[] src) {
        int l = src.length;
        ByteIO io = new ByteIO(src);
        this.time = new GpsTime();
        this.time.from(io.getBytes(6));
        this.gpsSum = io.getUbyte();
        String s = StringUtils.numToHex16(this.gpsSum);
        this.gpsl = Integer.parseInt(String.valueOf(s.charAt(0)), 16);
        this.gpsS = Integer.parseInt(String.valueOf(s.charAt(1)), 16);
        this.lat = io.getUint();
        this.lng = io.getUint();
        this.speed = io.getUbyte();
        this.state = io.getUshort();
        this.mcc = io.getUshort();
        this.mnc = io.getUbyte();
        this.lac = io.getUshort();
        this.cell = io.getMint();
        this.acc = io.getUbyte();
        this.reportingMode = io.getUbyte();
        this.gpsRealTime = io.getUbyte();
        if (l == this.size()) {
            this.mileageStatistics = Long.valueOf(io.getUint());
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
        io.putUshort(this.mcc);
        io.putUbyte(this.mnc);
        io.putUshort(this.lac);
        io.putMint(this.cell);
        io.putUbyte(this.acc);
        io.putUbyte(this.reportingMode);
        io.putUbyte(this.gpsRealTime);
        io.putUint(this.mileageStatistics.longValue());
        return io.array();
    }
}
