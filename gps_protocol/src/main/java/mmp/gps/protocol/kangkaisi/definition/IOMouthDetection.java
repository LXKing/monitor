package mmp.gps.protocol.kangkaisi.definition;


public class IOMouthDetection {

    private int IO;
    private int triggerCondition;
    private int gateState;


    public int getIO() {
        return this.IO;
    }

    public void setIO(int iO) {
        this.IO = iO;
    }

    public int getTriggerCondition() {
        return this.triggerCondition;
    }

    public void setTriggerCondition(int triggerCondition) {
        this.triggerCondition = triggerCondition;
    }

    public int getGateState() {
        return this.gateState;
    }

    public void setGateState(int gateState) {
        this.gateState = gateState;
    }

    public String toString() {
        return "IOMouthDetection [IO=" + this.IO + ", triggerCondition=" + this.triggerCondition + ", gateState=" + this.gateState + "]";
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

        this.IO = Integer.parseInt(String.valueOf(binaryStr.charAt(5)));
        this.triggerCondition = Integer.parseInt(String.valueOf(binaryStr.charAt(6)));
        this.gateState = Integer.parseInt(String.valueOf(binaryStr.charAt(7)));
    }

    public int to() {
        String a = "00000" + this.IO + this.triggerCondition + this.gateState;
        int d = Integer.parseInt(a, 2);
        return d;
    }
}
