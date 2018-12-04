package mmp.gps.protocol.kangkaisi.body;

import mmp.gps.protocol.kangkaisi.ByteIO;
import mmp.gps.protocol.kangkaisi.IPacket;

import java.math.BigInteger;

public class LBSExtendBody implements IPacket {

    private String time;
    private int MCC;
    private short MNC;
    private int LAC;
    private int CI;
    private short RSSI;
    private int NLAC1;
    private int NCI1;
    private short NRSSI1;
    private int NLAC2;
    private int NCI2;
    private short MCCNRSSI2;
    private int NLAC3;
    private int NCI3;
    private short NRSSI3;
    private int NLAC4;
    private int NCI4;
    private short NRSSI4;
    private int NLAC5;
    private int NCI5;
    private short NRSSI5;
    private int NLAC6;
    private int NCI6;
    private short NRSSI6;
    private short timeLead;
    private int language;


    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMCC() {
        return this.MCC;
    }

    public void setMCC(int mCC) {
        this.MCC = mCC;
    }

    public short getMNC() {
        return this.MNC;
    }

    public void setMNC(short mNC) {
        this.MNC = mNC;
    }

    public int getLAC() {
        return this.LAC;
    }

    public void setLAC(int lAC) {
        this.LAC = lAC;
    }

    public int getCI() {
        return this.CI;
    }

    public void setCI(int cI) {
        this.CI = cI;
    }

    public short getRSSI() {
        return this.RSSI;
    }

    public void setRSSI(short rSSI) {
        this.RSSI = rSSI;
    }

    public int getNLAC1() {
        return this.NLAC1;
    }

    public void setNLAC1(int nLAC1) {
        this.NLAC1 = nLAC1;
    }

    public int getNCI1() {
        return this.NCI1;
    }

    public void setNCI1(int nCI1) {
        this.NCI1 = nCI1;
    }

    public short getNRSSI1() {
        return this.NRSSI1;
    }

    public void setNRSSI1(short nRSSI1) {
        this.NRSSI1 = nRSSI1;
    }

    public int getNLAC2() {
        return this.NLAC2;
    }

    public void setNLAC2(int nLAC2) {
        this.NLAC2 = nLAC2;
    }

    public int getNCI2() {
        return this.NCI2;
    }

    public void setNCI2(int nCI2) {
        this.NCI2 = nCI2;
    }

    public short getMCCNRSSI2() {
        return this.MCCNRSSI2;
    }

    public void setMCCNRSSI2(short mCCNRSSI2) {
        this.MCCNRSSI2 = mCCNRSSI2;
    }

    public int getNLAC3() {
        return this.NLAC3;
    }

    public void setNLAC3(int nLAC3) {
        this.NLAC3 = nLAC3;
    }

    public int getNCI3() {
        return this.NCI3;
    }

    public void setNCI3(int nCI3) {
        this.NCI3 = nCI3;
    }

    public short getNRSSI3() {
        return this.NRSSI3;
    }

    public void setNRSSI3(short nRSSI3) {
        this.NRSSI3 = nRSSI3;
    }

    public int getNLAC4() {
        return this.NLAC4;
    }

    public void setNLAC4(int nLAC4) {
        this.NLAC4 = nLAC4;
    }

    public int getNCI4() {
        return this.NCI4;
    }

    public void setNCI4(int nCI4) {
        this.NCI4 = nCI4;
    }

    public short getNRSSI4() {
        return this.NRSSI4;
    }

    public void setNRSSI4(short nRSSI4) {
        this.NRSSI4 = nRSSI4;
    }

    public int getNLAC5() {
        return this.NLAC5;
    }

    public void setNLAC5(int nLAC5) {
        this.NLAC5 = nLAC5;
    }

    public int getNCI5() {
        return this.NCI5;
    }

    public void setNCI5(int nCI5) {
        this.NCI5 = nCI5;
    }

    public short getNRSSI5() {
        return this.NRSSI5;
    }

    public void setNRSSI5(short nRSSI5) {
        this.NRSSI5 = nRSSI5;
    }

    public int getNLAC6() {
        return this.NLAC6;
    }

    public void setNLAC6(int nLAC6) {
        this.NLAC6 = nLAC6;
    }

    public int getNCI6() {
        return this.NCI6;
    }

    public void setNCI6(int nCI6) {
        this.NCI6 = nCI6;
    }

    public short getNRSSI6() {
        return this.NRSSI6;
    }

    public void setNRSSI6(short nRSSI6) {
        this.NRSSI6 = nRSSI6;
    }

    public short gettimeLead() {
        return this.timeLead;
    }

    public void setTimeLead(short timeLead) {
    }

    public int getLanguage() {
        return this.language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public String toString() {
        return "LBSExtend [time=" + this.time + ", MCC=" + this.MCC + ", MNC=" + this.MNC + ", LAC=" + this.LAC + ", CI=" + this.CI + ", RSSI=" + this.RSSI + ", NLAC1=" + this.NLAC1 + ", NCI1=" + this.NCI1 + ", NRSSI1=" + this.NRSSI1 + ", NLAC2=" + this.NLAC2 + ", NCI2=" + this.NCI2 + ", MCCNRSSI2=" + this.MCCNRSSI2 + ", NLAC3=" + this.NLAC3 + ", NCI3=" + this.NCI3 + ", NRSSI3=" + this.NRSSI3 + ", NLAC4=" + this.NLAC4 + ", NCI4=" + this.NCI4 + ", NRSSI4=" + this.NRSSI4 + ", NLAC5=" + this.NLAC5 + ", NCI5=" + this.NCI5 + ", NRSSI5=" + this.NRSSI5 + ", NLAC6=" + this.NLAC6 + ", NCI6=" + this.NCI6 + ", NRSSI6=" + this.NRSSI6 + ", TimeLead=" + this.timeLead + ", language=" + this.language + "]";
    }

    public int size() {
        return 54;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        BigInteger bi = new BigInteger(io.getBytes(6));
        this.time = bi.toString(16);
        this.MCC = io.getUshort();
        this.MNC = io.getUbyte();
        this.LAC = io.getUshort();
        this.CI = io.getMint();
        this.RSSI = io.getUbyte();
        this.NLAC1 = io.getUshort();
        this.NCI1 = io.getMint();
        this.NRSSI1 = io.getUbyte();
        this.NLAC2 = io.getUshort();
        this.NCI2 = io.getMint();
        this.MCCNRSSI2 = io.getUbyte();
        this.NLAC3 = io.getUshort();
        this.NCI3 = io.getMint();
        this.NRSSI3 = io.getUbyte();
        this.NLAC4 = io.getUshort();
        this.NCI4 = io.getMint();
        this.NRSSI4 = io.getUbyte();
        this.NLAC5 = io.getUshort();
        this.NCI5 = io.getMint();
        this.NRSSI5 = io.getUbyte();
        this.NLAC6 = io.getUshort();
        this.NCI6 = io.getMint();
        this.NRSSI6 = io.getUbyte();
        this.timeLead = io.getUbyte();
        this.language = io.getUshort();
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        BigInteger bi = new BigInteger(this.time, 16);
        byte[] numbers = bi.toByteArray();
        io.put(numbers);
        io.putUshort(this.MCC);
        io.putUbyte(this.MNC);
        io.putUshort(this.LAC);
        io.putMint(this.CI);
        io.putUbyte(this.RSSI);
        io.putUshort(this.NLAC1);
        io.putMint(this.NCI1);
        io.putUbyte(this.NRSSI1);
        io.putUshort(this.NLAC2);
        io.putMint(this.NCI2);
        io.putUbyte(this.MCCNRSSI2);
        io.putUshort(this.NLAC3);
        io.putMint(this.NCI3);
        io.putUbyte(this.NRSSI3);
        io.putUshort(this.NLAC4);
        io.putMint(this.NCI4);
        io.putUbyte(this.NRSSI4);
        io.putUshort(this.NLAC5);
        io.putMint(this.NCI5);
        io.putUbyte(this.NRSSI5);
        io.putUshort(this.NLAC6);
        io.putMint(this.NCI6);
        io.putUbyte(this.NRSSI6);
        io.putUbyte(this.timeLead);
        io.putUshort(this.language);
        return io.array();
    }
}
