package mmp.gps.protocol.kangkaisi.body;

import mmp.gps.protocol.kangkaisi.ByteIO;
import mmp.gps.protocol.kangkaisi.IPacket;

public class ServerReplyAlarmChineseBody implements IPacket {

    private short instructionLength;
    private String serverFlagBit;
    private String ALARMSMS;
    private String sign;
    private String addressContent;
    private String delimiter;
    private String phoneNumber;
    private String delimiter2;


    public short getInstructionLength() {
        return this.instructionLength;
    }

    public void setInstructionLength(short instructionLength) {
        this.instructionLength = instructionLength;
    }

    public String getServerFlagBit() {
        return this.serverFlagBit;
    }

    public void setServerFlagBit(String serverFlagBit) {
        this.serverFlagBit = serverFlagBit;
    }

    public String getALARMSMS() {
        return this.ALARMSMS;
    }

    public void setALARMSMS(String aLARMSMS) {
        this.ALARMSMS = aLARMSMS;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAddressContent() {
        return this.addressContent;
    }

    public void setAddressContent(String addressContent) {
        this.addressContent = addressContent;
    }

    public String getDelimiter() {
        return this.delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDelimiter2() {
        return this.delimiter2;
    }

    public void setDelimiter2(String delimiter2) {
        this.delimiter2 = delimiter2;
    }

    public String toString() {
        return "ServerReplyAlarmChineseBody [instructionLength=" + this.instructionLength + ", serverFlagBit=" + this.serverFlagBit + ", ALARMSMS=" + this.ALARMSMS + ", sign=" + this.sign + ", addressContent=" + this.addressContent + ", delimiter=" + this.delimiter + ", phoneNumber=" + this.phoneNumber + ", delimiter2=" + this.delimiter2 + "]";
    }

    public int size() {
        return 40 + this.addressContent.getBytes().length;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.instructionLength = io.getUbyte();
        byte[] ServerFlagBitby = io.getBytes(4);
        this.serverFlagBit = new String(ServerFlagBitby, 0, ServerFlagBitby.length);
        byte[] ALARMSMSby = io.getBytes(8);
        this.ALARMSMS = new String(ALARMSMSby, 0, ALARMSMSby.length);
        byte[] signby = io.getBytes(2);
        this.sign = new String(signby, 0, signby.length);
        byte[] addressContentby = io.getBytes(src.length - 40);
        this.addressContent = new String(addressContentby, 0, addressContentby.length);
        byte[] delimiterby = io.getBytes(2);
        this.delimiter = new String(delimiterby, 0, delimiterby.length);
        byte[] phoneNumberby = io.getBytes(21);
        this.phoneNumber = new String(phoneNumberby, 0, phoneNumberby.length);
        byte[] delimiter2by = io.getBytes(2);
        this.delimiter2 = new String(delimiter2by, 0, delimiter2by.length);
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        this.instructionLength = (short) (40 + this.addressContent.getBytes().length);
        io.putUbyte(this.instructionLength);
        io.put(this.serverFlagBit.getBytes());
        io.put(this.ALARMSMS.getBytes());
        io.put(this.sign.getBytes());
        io.put(this.addressContent.getBytes());
        io.put(this.delimiter.getBytes());
        io.put(this.phoneNumber.getBytes());
        io.put(this.delimiter2.getBytes());
        return io.array();
    }
}
