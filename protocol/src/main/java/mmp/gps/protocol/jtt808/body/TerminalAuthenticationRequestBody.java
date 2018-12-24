package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class TerminalAuthenticationRequestBody implements IPacket {

    private String authenticationCode;


    public String getAuthenticationCode() {
        return this.authenticationCode;
    }

    public void setAuthenticationCode(String authenticationCode) {
        this.authenticationCode = authenticationCode;
    }

    public int size() {
        return this.authenticationCode == null ? 0 : this.authenticationCode.getBytes(Charsets.GBK).length;
    }

    public void from(byte[] src) {
        this.authenticationCode = new String(src, Charsets.GBK);
    }

    public byte[] array() {
        return this.authenticationCode == null ? null : this.authenticationCode.getBytes(Charsets.GBK);
    }
}
