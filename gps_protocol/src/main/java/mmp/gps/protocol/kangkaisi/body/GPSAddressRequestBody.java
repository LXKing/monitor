package mmp.gps.protocol.kangkaisi.body;

import mmp.gps.protocol.kangkaisi.ByteIO;
import mmp.gps.protocol.kangkaisi.IPacket;

import java.math.BigInteger;

public class GPSAddressRequestBody implements IPacket {

    private String time;
    private short gpsSum;
    private long lat;
    private long lng;
    private short speed;
    private int state;
    private String telephone;
    private int language;


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

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
    }

    public int getLanguage() {
        return this.language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public String toString() {
        return "GPSAddressRequestBody [time=" + this.time + ", GpsSum=" + this.gpsSum + ", lat=" + this.lat + ", lng=" + this.lng + ", speed=" + this.speed + ", state=" + this.state + ", Telephone=" + this.telephone + ", language=" + this.language + "]";
    }

    public int size() {
        return 41;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        BigInteger bi = new BigInteger(io.getBytes(6));
        this.time = bi.toString(16);
        this.gpsSum = io.getUbyte();
        this.lat = io.getUint();
        this.lng = io.getUint();
        this.speed = io.getUbyte();
        this.state = io.getUshort();
        BigInteger bi2 = new BigInteger(io.getBytes(21));
        this.telephone = bi2.toString(16);
        this.language = io.getUshort();
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
        BigInteger bi2 = new BigInteger(this.telephone, 16);
        byte[] numbers2 = bi2.toByteArray();
        io.put(numbers2);
        io.putUshort(this.language);
        return io.array();
    }
}
