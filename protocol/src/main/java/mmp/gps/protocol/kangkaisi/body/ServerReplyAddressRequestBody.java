package mmp.gps.protocol.kangkaisi.body;

import mmp.gps.protocol.kangkaisi.ByteIO;
import mmp.gps.protocol.kangkaisi.IPacket;

import java.io.UnsupportedEncodingException;

public class ServerReplyAddressRequestBody implements IPacket {

    private short instructionLength;
    private long serverFlagBit;
    private String addressRequest;
    private String delimiter1;
    private String addressContent;
    private String delimiter2;
    private String phoneNumber;
    private String delimiter3;


    public short getInstructionLength() {
        return this.instructionLength;
    }

    public void setInstructionLength(short instructionLength) {
        this.instructionLength = instructionLength;
    }

    public long getServerFlagBit() {
        return this.serverFlagBit;
    }

    public void setServerFlagBit(long serverFlagBit) {
        this.serverFlagBit = serverFlagBit;
    }

    public String getAddressRequest() {
        return this.addressRequest;
    }

    public void setAddressRequest(String addressRequest) {
        this.addressRequest = addressRequest;
    }

    public String getDelimiter1() {
        return this.delimiter1;
    }

    public void setDelimiter1(String delimiter1) {
        this.delimiter1 = delimiter1;
    }

    public String getAddressContent() {
        return this.addressContent;
    }

    public void setAddressContent(String addressContent) {
        this.addressContent = addressContent;
    }

    public String getDelimiter2() {
        return this.delimiter2;
    }

    public void setDelimiter2(String delimiter2) {
        this.delimiter2 = delimiter2;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDelimiter3() {
        return this.delimiter3;
    }

    public void setDelimiter3(String delimiter3) {
        this.delimiter3 = delimiter3;
    }

    public String toString() {
        return "ServerReplyAddressRequestBody [InstructionLength=" + this.instructionLength + ", ServerFlagBit=" + this.serverFlagBit + ", addressRequest=" + this.addressRequest + ", delimiter1=" + this.delimiter1 + ", addressContent=" + this.addressContent + ", delimiter2=" + this.delimiter2 + ", phoneNumber=" + this.phoneNumber + ", delimiter3=" + this.delimiter3 + "]";
    }

    public int size() {
        return 39 + this.addressContent.getBytes().length;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.instructionLength = io.getUbyte();
        this.serverFlagBit = io.getUint();
        byte[] addressRequestby = io.getBytes(7);
        this.addressRequest = new String(addressRequestby, 0, addressRequestby.length);
        byte[] delimiterby1 = io.getBytes(2);
        this.delimiter1 = new String(delimiterby1, 0, delimiterby1.length);
        byte[] addressContentby = io.getBytes(src.length - 39);

        try {
            this.addressContent = new String(addressContentby, 0, addressContentby.length, "UNICODE");
        } catch (UnsupportedEncodingException var9) {
            var9.printStackTrace();
        }

        byte[] delimiterby2 = io.getBytes(2);
        this.delimiter2 = new String(delimiterby2, 0, delimiterby2.length);
        byte[] phoneNumberby = io.getBytes(21);
        this.phoneNumber = new String(phoneNumberby, 0, phoneNumberby.length);
        byte[] delimiterby3 = io.getBytes(2);
        this.delimiter2 = new String(delimiterby3, 0, delimiterby3.length);
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        this.instructionLength = (short) (39 + this.addressContent.getBytes().length);
        io.putUbyte(this.instructionLength);
        io.putUint(this.serverFlagBit);
        io.put(this.addressRequest.getBytes());
        io.put(this.delimiter1.getBytes());

        try {
            io.put(this.addressContent.getBytes("UNICODE"));
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
        }

        io.put(this.delimiter2.getBytes());
        io.put(this.phoneNumber.getBytes());
        io.put(this.delimiter3.getBytes());
        return io.array();
    }
}
