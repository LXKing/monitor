package mmp.gps.protocol.kangkaisi.definition;


public class TerminalInformationContent {

    private int OilAndElectricity;
    private int Gps;
    private int type;
    private int PowerSupply;
    private int acc;
    private int Fortification;


    public int getOilAndElectricity() {
        return this.OilAndElectricity;
    }

    public void setOilAndElectricity(int oilAndElectricity) {
        this.OilAndElectricity = oilAndElectricity;
    }

    public int getGps() {
        return this.Gps;
    }

    public void setGps(int gps) {
        this.Gps = gps;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPowerSupply() {
        return this.PowerSupply;
    }

    public void setPowerSupply(int powerSupply) {
        this.PowerSupply = powerSupply;
    }

    public int getAcc() {
        return this.acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public int getFortification() {
        return this.Fortification;
    }

    public void setFortification(int fortification) {
        this.Fortification = fortification;
    }

    public String toString() {
        return "TerminalInformationContent [OilAndElectricity=" + this.OilAndElectricity + ", Gps=" + this.Gps + ", type=" + this.type + ", PowerSupply=" + this.PowerSupply + ", acc=" + this.acc + ", Fortification=" + this.Fortification + "]";
    }

    public void from(int str) {
        String binaryStr = Integer.toBinaryString(str);
        switch (binaryStr.length()) {
            case 0:
                binaryStr = "00000000" + binaryStr;
                break;
            case 1:
                binaryStr = "0000000" + binaryStr;
                break;
            case 2:
                binaryStr = "000000" + binaryStr;
                break;
            case 3:
                binaryStr = "00000" + binaryStr;
                break;
            case 4:
                binaryStr = "0000" + binaryStr;
                break;
            case 5:
                binaryStr = "000" + binaryStr;
                break;
            case 6:
                binaryStr = "00" + binaryStr;
                break;
            case 7:
                binaryStr = "0" + binaryStr;
        }

        this.OilAndElectricity = Integer.parseInt(String.valueOf(binaryStr.charAt(0)));
        this.Gps = Integer.parseInt(String.valueOf(binaryStr.charAt(1)));
        this.type = Integer.parseInt(binaryStr.substring(2, 4));
        this.PowerSupply = Integer.parseInt(String.valueOf(binaryStr.charAt(5)));
        this.acc = Integer.parseInt(String.valueOf(binaryStr.charAt(6)));
        this.Fortification = Integer.parseInt(String.valueOf(binaryStr.charAt(7)));
    }

    public int to() {
        String a = String.valueOf(this.OilAndElectricity) + this.Gps + this.type + this.PowerSupply + this.acc + this.Fortification;
        int d = Integer.parseInt(a, 2);
        return d;
    }
}
