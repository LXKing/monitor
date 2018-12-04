package mmp.gps.gateway.domain;


public class InstructInfo {

    private String plateSerialNumber;
    private String command;


    public String getPlateSerialNumber() {
        return this.plateSerialNumber;
    }

    public void setPlateSerialNumber(String plateSerialNumber) {
        this.plateSerialNumber = plateSerialNumber;
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public InstructInfo() {
    }

    public InstructInfo(String psn, String cmm) {
        this.plateSerialNumber = psn;
        this.command = cmm;
    }
}
