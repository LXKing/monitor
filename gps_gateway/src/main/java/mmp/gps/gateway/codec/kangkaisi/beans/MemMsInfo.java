package mmp.gps.gateway.codec.kangkaisi.beans;

import org.apache.mina.core.session.IoSession;

public class MemMsInfo {

    private String id;
    private IoSession tcp;
    private IoSession udp;
    private long lastTcpHb;
    private long lastUdpHb;
    private byte onlineStatus;
    private short GSM;
    private short VoltageLevel;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IoSession getTcp() {
        return this.tcp;
    }

    public void setTcp(IoSession tcp) {
        this.tcp = tcp;
    }

    public IoSession getUdp() {
        return this.udp;
    }

    public void setUdp(IoSession udp) {
        this.udp = udp;
    }

    public long getLastTcpHb() {
        return this.lastTcpHb;
    }

    public void setLastTcpHb(long lastTcpHb) {
        this.lastTcpHb = lastTcpHb;
    }

    public long getLastUdpHb() {
        return this.lastUdpHb;
    }

    public void setLastUdpHb(long lastUdpHb) {
        this.lastUdpHb = lastUdpHb;
    }

    public byte getOnlineStatus() {
        return this.onlineStatus;
    }

    public void setOnlineStatus(byte onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public short getGSM() {
        return this.GSM;
    }

    public void setGSM(short gSM) {
        this.GSM = gSM;
    }

    public short getVoltageLevel() {
        return this.VoltageLevel;
    }

    public void setVoltageLevel(short voltageLevel) {
        this.VoltageLevel = voltageLevel;
    }

    public String toString() {
        return "MemMsInfo [id=" + this.id + ", tcp=" + this.tcp + ", udp=" + this.udp + ", lastTcpHb=" + this.lastTcpHb + ", lastUdpHb=" + this.lastUdpHb + ", onlineStatus=" + this.onlineStatus + ", GSM=" + this.GSM + ", VoltageLevel=" + this.VoltageLevel + "]";
    }
}
