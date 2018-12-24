package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class LocationInformationBaseInfo implements IPacket {

    private long alarms;
    private long status;
    private long lat;
    private long lng;
    private int altitude;
    private int speed;
    private int angle;
    private GpsTime gpstime;


    public long getAlarms() {
        return this.alarms;
    }

    public void setAlarms(long alarms) {
        this.alarms = alarms;
    }

    public long getStatus() {
        return this.status;
    }

    public void setStatus(long status) {
        this.status = status;
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

    public int getAltitude() {
        return this.altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAngle() {
        return this.angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public GpsTime getGpstime() {
        return this.gpstime;
    }

    public void setGpstime(GpsTime gpstime) {
        this.gpstime = gpstime;
    }

    public int size() {
        return 28;
    }

    public String toString() {
        return "LocationInformationBaseInfo [alarms=" + this.alarms + ", status=" + this.status + ", lat=" + this.lat + ", lng=" + this.lng + ", altitude=" + this.altitude + ", speed=" + this.speed + ", angle=" + this.angle + ", gpstime=" + this.gpstime + "]";
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.alarms = io.getUint();
        this.status = io.getUint();
        this.lat = io.getUint();
        this.lng = io.getUint();
        this.altitude = io.getUshort();
        this.speed = io.getUshort();
        this.angle = io.getUshort();
        this.gpstime = new GpsTime();
        this.gpstime.from(io.getBytes(6));
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUint(this.alarms);
        io.putUint(this.status);
        io.putUint(this.lat);
        io.putUint(this.lng);
        io.putUshort(this.altitude);
        io.putUshort(this.speed);
        io.putUshort(this.angle);
        io.put(this.gpstime.array());
        return io.array();
    }
}
