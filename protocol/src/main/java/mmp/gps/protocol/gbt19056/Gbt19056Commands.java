package mmp.gps.protocol.gbt19056;


public class Gbt19056Commands {

    public static final short ReadVersion = 0;
    public static final short ReadCurrentDriver = 1;
    public static final short ReadCurrentTime = 2;
    public static final short ReadMileage = 3;
    public static final short ReadPulse = 4;
    public static final short ReadVehicleInfo = 5;
    public static final short ReadStatusSetting = 6;
    public static final short ReadId = 7;
    public static final short ReadSpeedRecord = 8;
    public static final short ReadLocateRecord = 9;
    public static final short ReadAccidentDoubtRecord = 16;
    public static final short ReadTimeoutDrivingRecord = 17;
    public static final short ReadLoginLogoutRecord = 18;
    public static final short ReadExternalPowerSupplyRecord = 19;
    public static final short ReadParameterChangeRecord = 20;
    public static final short ReadSpeedStatusLog = 21;
    public static final short SetVehicleInfo = 130;
    public static final short SetInstallDate = 131;
    public static final short SetStatusSetting = 132;
    public static final short SetTime = 194;
    public static final short SetPulse = 195;
    public static final short SetMileage = 196;
    public static final short EnterCheck = 224;
    public static final short MileageErrorCheck = 225;
    public static final short PulseErrorCheck = 226;
    public static final short TimeErrorCheck = 227;
    public static final short ExitCheck = 227;


}
