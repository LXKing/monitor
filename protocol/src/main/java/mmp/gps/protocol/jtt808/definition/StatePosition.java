package mmp.gps.protocol.jtt808.definition;


public class StatePosition {

    private int acc;
    private int location;
    private int lat;
    private int lng;
    private int state;
    private int encryption;
    private int vehicle;
    private int oil;
    private int circuit;
    private int doorUnlock;
    private int door1;
    private int door2;
    private int door3;
    private int door4;
    private int door5;
    private int gps;
    private int bigDipper;
    private int gLONASS;
    private int galileo;


    public int getAcc() {
        return this.acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public int getLocation() {
        return this.location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getLat() {
        return this.lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLng() {
        return this.lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getEncryption() {
        return this.encryption;
    }

    public void setEncryption(int encryption) {
        this.encryption = encryption;
    }

    public int getVehicle() {
        return this.vehicle;
    }

    public void setVehicle(int vehicle) {
        this.vehicle = vehicle;
    }

    public int getOil() {
        return this.oil;
    }

    public void setOil(int oil) {
        this.oil = oil;
    }

    public int getCircuit() {
        return this.circuit;
    }

    public void setCircuit(int circuit) {
        this.circuit = circuit;
    }

    public int getDoorUnlock() {
        return this.doorUnlock;
    }

    public void setDoorUnlock(int doorUnlock) {
        this.doorUnlock = doorUnlock;
    }

    public int getDoor1() {
        return this.door1;
    }

    public void setDoor1(int door1) {
        this.door1 = door1;
    }

    public int getDoor2() {
        return this.door2;
    }

    public void setDoor2(int door2) {
        this.door2 = door2;
    }

    public int getDoor3() {
        return this.door3;
    }

    public void setDoor3(int door3) {
        this.door3 = door3;
    }

    public int getDoor4() {
        return this.door4;
    }

    public void setDoor4(int door4) {
        this.door4 = door4;
    }

    public int getDoor5() {
        return this.door5;
    }

    public void setDoor5(int door5) {
        this.door5 = door5;
    }

    public int getGps() {
        return this.gps;
    }

    public void setGps(int gps) {
        this.gps = gps;
    }

    public int getBigDipper() {
        return this.bigDipper;
    }

    public void setBigDipper(int bigDipper) {
        this.bigDipper = bigDipper;
    }

    public int getgLONASS() {
        return this.gLONASS;
    }

    public void setgLONASS(int gLONASS) {
        this.gLONASS = gLONASS;
    }

    public int getGalileo() {
        return this.galileo;
    }

    public void setGalileo(int galileo) {
        this.galileo = galileo;
    }

    public String toString() {
        return "StatePosition [acc=" + this.acc + ", location=" + this.location + ", lat=" + this.lat + ", lng=" + this.lng + ", state=" + this.state + ", encryption=" + this.encryption + ", vehicle=" + this.vehicle + ", oil=" + this.oil + ", circuit=" + this.circuit + ", doorUnlock=" + this.doorUnlock + ", door1=" + this.door1 + ", door2=" + this.door2 + ", door3=" + this.door3 + ", door4=" + this.door4 + ", door5=" + this.door5 + ", gps=" + this.gps + ", bigDipper=" + this.bigDipper + ", gLONASS=" + this.gLONASS + ", galileo=" + this.galileo + "]";
    }

    public void from(int TerminalInformationContent) {
        String binaryStr = Integer.toBinaryString(TerminalInformationContent);
        System.out.println("binaryStr" + binaryStr);
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

        this.acc = Integer.parseInt(String.valueOf(binaryStr.charAt(31)));
        this.location = Integer.parseInt(String.valueOf(binaryStr.charAt(30)));
        this.lat = Integer.parseInt(String.valueOf(binaryStr.charAt(29)));
        this.lng = Integer.parseInt(String.valueOf(binaryStr.charAt(28)));
        this.gps = Integer.parseInt(String.valueOf(binaryStr.charAt(13)));
    }

    public int to() {
        String a = "0000000000000" + this.gps + "00000000000000" + this.lng + this.lat + this.location + this.acc;
        int d = Integer.parseInt(a, 2);
        return d;
    }

    public static void main(String[] args) {
        StatePosition statePosition = new StatePosition();
        statePosition.setAcc(0);
        statePosition.setLat(1);
        statePosition.setLng(0);
        statePosition.setLocation(1);
        statePosition.setGps(1);
        System.out.println("statePosition.to() : " + statePosition.to());
        String str = (statePosition.to() & 1) == 1 ? "点火" : "熄火";
        System.out.println("str : " + str);
        System.out.println("statePosition.from() : " + statePosition);
    }
}
