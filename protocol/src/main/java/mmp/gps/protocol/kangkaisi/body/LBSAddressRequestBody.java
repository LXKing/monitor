package mmp.gps.protocol.kangkaisi.body;

import mmp.gps.protocol.kangkaisi.ByteIO;
import mmp.gps.protocol.kangkaisi.IPacket;

import java.math.BigInteger;

public class LBSAddressRequestBody implements IPacket {

    private int mcc;
    private short mnc;
    private int lac;
    private int cell;
    private String telephone;
    private int language;


    public int getMcc() {
        return this.mcc;
    }

    public void setMcc(int mcc) {
        this.mcc = mcc;
    }

    public short getMnc() {
        return this.mnc;
    }

    public void setMnc(short mnc) {
        this.mnc = mnc;
    }

    public int getLac() {
        return this.lac;
    }

    public void setLac(int lac) {
        this.lac = lac;
    }

    public int getCell() {
        return this.cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getLanguage() {
        return this.language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public String toString() {
        return "LBSAddressRequestBody [mcc=" + this.mcc + ", mnc=" + this.mnc + ", lac=" + this.lac + ", cell=" + this.cell + ", telephone=" + this.telephone + ", language=" + this.language + "]";
    }

    public int size() {
        return 31;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.mcc = io.getUshort();
        this.mnc = io.getUbyte();
        this.lac = io.getUshort();
        this.cell = io.getMint();
        BigInteger bi = new BigInteger(io.getBytes(21));
        this.telephone = bi.toString(16);
        this.language = io.getUshort();
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUshort(this.mcc);
        io.putUbyte(this.mnc);
        io.putUshort(this.lac);
        io.putMint(this.cell);
        BigInteger bi = new BigInteger(this.telephone, 16);
        byte[] numbers = bi.toByteArray();
        io.put(numbers);
        io.putUshort(this.language);
        return io.array();
    }
}
