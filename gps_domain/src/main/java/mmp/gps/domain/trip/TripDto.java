package mmp.gps.domain.trip;

import java.util.Date;

public class TripDto {
    public String number;
    public Date startTime;
    public Date endTime;
    public long mileage;
    public short maxSpeed;
    public short avgSpeed;
    public short overSpeed;
    public short overSpeedTime;
    public short breaks;
    public short speedUp;
    public byte maxEct;
    public short maxRpm;
    public float avgBv;
    public float totalOil;
    public float avgOil;
    public int fatigueTime;
    public int idleTime;

}
