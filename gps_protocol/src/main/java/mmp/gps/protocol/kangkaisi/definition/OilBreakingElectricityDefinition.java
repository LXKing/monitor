package mmp.gps.protocol.kangkaisi.definition;


public class OilBreakingElectricityDefinition {

    private int exorbitantSpeedDelayExecution;
    private int unfixedPositionDelayExecution;
    private int disconnectOilAndElectricity;
    private int oilAndElectricityConnect;


    public int getExorbitantSpeedDelayExecution() {
        return this.exorbitantSpeedDelayExecution;
    }

    public void setExorbitantSpeedDelayExecution(int exorbitantSpeedDelayExecution) {
        this.exorbitantSpeedDelayExecution = exorbitantSpeedDelayExecution;
    }

    public int getUnfixedPositionDelayExecution() {
        return this.unfixedPositionDelayExecution;
    }

    public void setUnfixedPositionDelayExecution(int unfixedPositionDelayExecution) {
        this.unfixedPositionDelayExecution = unfixedPositionDelayExecution;
    }

    public int getDisconnectOilAndElectricity() {
        return this.disconnectOilAndElectricity;
    }

    public void setDisconnectOilAndElectricity(int disconnectOilAndElectricity) {
        this.disconnectOilAndElectricity = disconnectOilAndElectricity;
    }

    public int getOilAndElectricityConnect() {
        return this.oilAndElectricityConnect;
    }

    public void setOilAndElectricityConnect(int oilAndElectricityConnect) {
        this.oilAndElectricityConnect = oilAndElectricityConnect;
    }

    public String toString() {
        return "OilBreakingElectricityDefinition [exorbitantSpeedDelayExecution=" + this.exorbitantSpeedDelayExecution + ", unfixedPositionDelayExecution=" + this.unfixedPositionDelayExecution + ", DisconnectOilAndElectricity=" + this.disconnectOilAndElectricity + ", oilAndElectricityConnect=" + this.oilAndElectricityConnect + "]";
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

        this.exorbitantSpeedDelayExecution = Integer.parseInt(String.valueOf(binaryStr.charAt(4)));
        this.unfixedPositionDelayExecution = Integer.parseInt(String.valueOf(binaryStr.charAt(5)));
        this.disconnectOilAndElectricity = Integer.parseInt(String.valueOf(binaryStr.charAt(6)));
        this.oilAndElectricityConnect = Integer.parseInt(String.valueOf(binaryStr.charAt(7)));
    }

    public int to() {
        String a = "0000" + this.exorbitantSpeedDelayExecution + this.unfixedPositionDelayExecution + this.disconnectOilAndElectricity + "  " + this.oilAndElectricityConnect;
        int d = Integer.parseInt(a, 2);
        return d;
    }
}
