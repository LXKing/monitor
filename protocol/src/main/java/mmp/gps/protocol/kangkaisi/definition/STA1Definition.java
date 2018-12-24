package mmp.gps.protocol.kangkaisi.definition;


public class STA1Definition {

    private int fortification;
    private int automaticFortification;
    private int manualFortification;
    private int remoteControlOperation;
    private int disconnectSwitch;
    private int preventionAndDismantlingAlarm;


    public int getFortification() {
        return this.fortification;
    }

    public void setFortification(int fortification) {
        this.fortification = fortification;
    }

    public int getAutomaticFortification() {
        return this.automaticFortification;
    }

    public void setAutomaticFortification(int automaticFortification) {
        this.automaticFortification = automaticFortification;
    }

    public int getManualFortification() {
        return this.manualFortification;
    }

    public void setManualFortification(int manualFortification) {
        this.manualFortification = manualFortification;
    }

    public int getRemoteControlOperation() {
        return this.remoteControlOperation;
    }

    public void setRemoteControlOperation(int remoteControlOperation) {
        this.remoteControlOperation = remoteControlOperation;
    }

    public int getDisconnectSwitch() {
        return this.disconnectSwitch;
    }

    public void setDisconnectSwitch(int disconnectSwitch) {
        this.disconnectSwitch = disconnectSwitch;
    }

    public int getPreventionAndDismantlingAlarm() {
        return this.preventionAndDismantlingAlarm;
    }

    public void setPreventionAndDismantlingAlarm(int preventionAndDismantlingAlarm) {
        this.preventionAndDismantlingAlarm = preventionAndDismantlingAlarm;
    }

    public String toString() {
        return "STA1Definition [fortification=" + this.fortification + ", automaticFortification=" + this.automaticFortification + ", manualFortification=" + this.manualFortification + ", remoteControlOperation=" + this.remoteControlOperation + ", disconnectSwitch=" + this.disconnectSwitch + ", PreventionAndDismantlingAlarm=" + this.preventionAndDismantlingAlarm + "]";
    }

    public void from(int alm1) {
        String binaryStr = Integer.toBinaryString(alm1);
        switch (binaryStr.length()) {
            case 10:
                binaryStr = "000000" + binaryStr;
                break;
            case 11:
                binaryStr = "00000" + binaryStr;
                break;
            case 12:
                binaryStr = "0000" + binaryStr;
                break;
            case 13:
                binaryStr = "000" + binaryStr;
                break;
            case 14:
                binaryStr = "00" + binaryStr;
                break;
            case 15:
                binaryStr = "0" + binaryStr;
        }

        this.fortification = Integer.parseInt(String.valueOf(binaryStr.charAt(0)));
        this.automaticFortification = Integer.parseInt(String.valueOf(binaryStr.charAt(1)));
        this.manualFortification = Integer.parseInt(String.valueOf(binaryStr.charAt(2)));
        this.remoteControlOperation = Integer.parseInt(String.valueOf(binaryStr.charAt(3)));
        this.disconnectSwitch = Integer.parseInt(String.valueOf(binaryStr.charAt(6)));
        this.preventionAndDismantlingAlarm = Integer.parseInt(String.valueOf(binaryStr.charAt(7)));
    }

    public int to() {
        String a = String.valueOf(this.fortification) + this.automaticFortification + this.manualFortification + this.remoteControlOperation + "  " + this.disconnectSwitch + this.preventionAndDismantlingAlarm;
        int d = Integer.parseInt(a, 2);
        return d;
    }
}
