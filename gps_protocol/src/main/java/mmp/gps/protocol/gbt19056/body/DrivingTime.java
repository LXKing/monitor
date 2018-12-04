package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class DrivingTime implements IPacket {

    private short year;
    private byte month;
    private byte day;
    private byte hour;
    private byte minute;
    private byte second;


    public short getYear() {
        return this.year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public byte getMonth() {
        return this.month;
    }

    public void setMonth(byte month) {
        this.month = month;
    }

    public byte getDay() {
        return this.day;
    }

    public void setDay(byte day) {
        this.day = day;
    }

    public byte getHour() {
        return this.hour;
    }

    public void setHour(byte hour) {
        this.hour = hour;
    }

    public byte getMinute() {
        return this.minute;
    }

    public void setMinute(byte minute) {
        this.minute = minute;
    }

    public byte getSecond() {
        return this.second;
    }

    public void setSecond(byte second) {
        this.second = second;
    }

    public int size() {
        return 6;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.year = io.getUbyte();
        this.month = io.get();
        this.day = io.get();
        this.hour = io.get();
        this.minute = io.get();
        this.second = io.get();
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUbyte(this.year);
        io.put(this.month);
        io.put(this.day);
        io.put(this.hour);
        io.put(this.minute);
        io.put(this.second);
        return io.array();
    }

    public String toString() {
        StringBuilder gpsTimeBuilder = new StringBuilder(20);
        gpsTimeBuilder.append("20");
        gpsTimeBuilder.append(Integer.toHexString(this.year & 255));
        gpsTimeBuilder.append("-");
        gpsTimeBuilder.append(Integer.toHexString(this.month & 255));
        gpsTimeBuilder.append("-");
        gpsTimeBuilder.append(Integer.toHexString(this.day & 255));
        gpsTimeBuilder.append(" ");
        gpsTimeBuilder.append(Integer.toHexString(this.hour & 255));
        gpsTimeBuilder.append(":");
        gpsTimeBuilder.append(Integer.toHexString(this.minute & 255));
        gpsTimeBuilder.append(":");
        gpsTimeBuilder.append(Integer.toHexString(this.second & 255));
        String gpsTimeString = gpsTimeBuilder.toString();
        return gpsTimeString;
    }

    public static DrivingTime parse(String p) {
        DrivingTime datetime = new DrivingTime();
        if (p != null && !p.isEmpty()) {
            String[] dates = p.split(" ");
            String[] date = dates[0].split("-", 3);
            String[] time = dates[1].split(":", 3);
            String year = date[0];
            if (year.length() == 4) {
                datetime.setYear(Short.parseShort(year.substring(2), 16));
            } else {
                datetime.setYear(Short.parseShort(year, 16));
            }

            datetime.setMonth(Byte.parseByte(date[1], 16));
            datetime.setDay(Byte.parseByte(date[2], 16));
            datetime.setHour(Byte.parseByte(time[0], 16));
            datetime.setMinute(Byte.parseByte(time[1], 16));
            if (time[2].trim().length() > 0) {
                datetime.setSecond(Byte.parseByte(time[2], 16));
            }

            return datetime;
        } else {
            return datetime;
        }
    }
}
