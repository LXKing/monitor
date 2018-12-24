package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class TerminalRegistrationReplyBody implements IPacket {

    private int responseSerialNumber;
    private byte result;
    private String authenticationCode;


    public int getResponseSerialNumber() {
        return this.responseSerialNumber;
    }

    public void setResponseSerialNumber(int responseSerialNumber) {
        this.responseSerialNumber = responseSerialNumber;
    }

    public byte getResult() {
        return this.result;
    }

    public void setResult(byte result) {
        this.result = result;
    }

    public String getAuthenticationCode() {
        return this.authenticationCode;
    }

    public void setAuthenticationCode(String authenticationCode) {
        this.authenticationCode = authenticationCode;
    }

    public int size() {
        return 3 + (this.authenticationCode == null ? 0 : this.authenticationCode.getBytes(Charsets.GBK).length);
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.responseSerialNumber = io.getUshort();
        this.result = io.get();
        int size = io.size() - io.position();
        if (size > 0) {
            byte[] number = new byte[size];
            this.authenticationCode = new String(number, Charsets.GBK);
        }

    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.putUshort(this.responseSerialNumber);
        io.put(this.result);
        if (this.authenticationCode != null && this.authenticationCode.length() > 0) {
            io.put(this.authenticationCode.getBytes(Charsets.GBK));
        }

        return data;
    }
}
