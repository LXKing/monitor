package mmp.gps.protocol.kangkaisi.body;

import mmp.gps.protocol.kangkaisi.ByteIO;
import mmp.gps.protocol.kangkaisi.IPacket;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GpsTime implements IPacket {

    private byte year;
    private byte month;
    private byte day;
    private byte hour;
    private byte minute;
    private byte second;


    public byte getYear() {
        return this.year;
    }

    public void setYear(byte year) {
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
        this.year = io.get();
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
        gpsTimeBuilder.append(Integer.parseInt(String.valueOf(this.year), 10));
        gpsTimeBuilder.append("-");
        gpsTimeBuilder.append(Integer.parseInt(String.valueOf(this.month), 10));
        gpsTimeBuilder.append("-");
        gpsTimeBuilder.append(Integer.parseInt(String.valueOf(this.day), 10));
        gpsTimeBuilder.append(" ");
        gpsTimeBuilder.append(Integer.parseInt(String.valueOf(this.hour), 10));
        gpsTimeBuilder.append(":");
        gpsTimeBuilder.append(Integer.parseInt(String.valueOf(this.minute), 10));
        gpsTimeBuilder.append(":");
        gpsTimeBuilder.append(Integer.parseInt(String.valueOf(this.second), 10));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetimes = null;

        try {
            datetimes = format.parse(gpsTimeBuilder.toString());
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        datetimes.setHours(datetimes.getHours() + 8);
        return format.format(datetimes).toString();
    }

    public static GpsTime parse(String p) {
        if (p != null && !p.isEmpty()) {
            GpsTime datetime = new GpsTime();
            String[] dates = p.split(" ");
            String[] date = dates[0].split("-", 3);
            String[] time = dates[1].split(":", 3);
            String year = date[0];
            if (year.length() == 4) {
                datetime.setYear(Byte.parseByte(year.substring(2), 16));
            } else {
                datetime.setYear(Byte.parseByte(year, 16));
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
            return new GpsTime();
        }
    }
}
