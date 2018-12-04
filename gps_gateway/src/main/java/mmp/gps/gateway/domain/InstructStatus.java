package mmp.gps.gateway.domain;


public class InstructStatus {

    private String serialNumber;
    private String result;


    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public InstructStatus() {
    }

    public InstructStatus(String serialNumber, String result) {
        this.serialNumber = serialNumber;
        this.result = result;
    }
}
