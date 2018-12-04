package mmp.gps.protocol.kangkaisi.definition;


public class ALM4Definition {

    private int sos;
    private int network;
    private int telephone;
    private int sms;
    private int voice;
    private int network2;
    private int telephone2;
    private int sms2;


    public int getSos() {
        return this.sos;
    }

    public void setSos(int sos) {
        this.sos = sos;
    }

    public int getNetwork() {
        return this.network;
    }

    public void setNetwork(int network) {
        this.network = network;
    }

    public int getTelephone() {
        return this.telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getSms() {
        return this.sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public int getVoice() {
        return this.voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }

    public int getNetwork2() {
        return this.network2;
    }

    public void setNetwork2(int network2) {
        this.network2 = network2;
    }

    public int getTelephone2() {
        return this.telephone2;
    }

    public void setTelephone2(int telephone2) {
        this.telephone2 = telephone2;
    }

    public int getSms2() {
        return this.sms2;
    }

    public void setSms2(int sms2) {
        this.sms2 = sms2;
    }

    public String toString() {
        return "ALM4Definition [sos=" + this.sos + ", network=" + this.network + ", telephone=" + this.telephone + ", sms=" + this.sms + ", voice=" + this.voice + ", network2=" + this.network2 + ", telephone2=" + this.telephone2 + ", sms2=" + this.sms2 + "]";
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

        this.sos = Integer.parseInt(String.valueOf(binaryStr.charAt(0)));
        this.network = Integer.parseInt(String.valueOf(binaryStr.charAt(1)));
        this.telephone = Integer.parseInt(String.valueOf(binaryStr.charAt(2)));
        this.sms = Integer.parseInt(String.valueOf(binaryStr.charAt(3)));
        this.voice = Integer.parseInt(String.valueOf(binaryStr.charAt(4)));
        this.network2 = Integer.parseInt(String.valueOf(binaryStr.charAt(5)));
        this.telephone2 = Integer.parseInt(String.valueOf(binaryStr.charAt(6)));
        this.sms2 = Integer.parseInt(String.valueOf(binaryStr.charAt(7)));
    }

    public int to() {
        String a = String.valueOf(this.sos) + this.network + this.telephone + this.sms + this.voice + this.network2 + this.telephone2 + this.sms2;
        int d = Integer.parseInt(a, 2);
        return d;
    }
}
