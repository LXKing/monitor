package mmp.gps.domain.faultcode;

import java.sql.Timestamp;

public class FaultCodeDto {
    public String id;
    public short level;
    public short modeId;
    public short code1;
    public short code2;
    public short code3;
    public String brand;
    public String pcode;
    public String contentC;
    public String contentE;
    public String solution;
    public byte clear;
    public boolean notifyOwner;
    public boolean notifyAdvisor;
    public String sensors;
    public Timestamp editTime;
}
