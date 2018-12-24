package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class TerminalWirelessUpgradeInfo implements IPacket {

    private String url;
    private String apn;
    private String account;
    private String password;
    private String address;
    private int tcpPort;
    private int udpPort;
    private byte[] factoryId;
    private String hardwareVer;
    private String firmwareVer;
    private int timeout;


    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApn() {
        return this.apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTcpPort() {
        return this.tcpPort;
    }

    public void setTcpPort(int tcpPort) {
        this.tcpPort = tcpPort;
    }

    public int getUdpPort() {
        return this.udpPort;
    }

    public void setUdpPort(int udpPort) {
        this.udpPort = udpPort;
    }

    public byte[] getFactoryId() {
        return this.factoryId;
    }

    public void setFactoryId(byte[] factoryId) {
        this.factoryId = factoryId;
    }

    public String getHardwareVer() {
        return this.hardwareVer;
    }

    public void setHardwareVer(String hardwareVer) {
        this.hardwareVer = hardwareVer;
    }

    public String getFirmwareVer() {
        return this.firmwareVer;
    }

    public void setFirmwareVer(String firmwareVer) {
        this.firmwareVer = firmwareVer;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int size() {
        int total = 0;
        if (this.url != null) {
            total += this.url.getBytes(Charsets.GBK).length;
        }

        ++total;
        if (this.apn != null) {
            total += this.apn.getBytes(Charsets.GBK).length;
        }

        ++total;
        if (this.account != null) {
            total += this.account.getBytes(Charsets.GBK).length;
        }

        ++total;
        if (this.password != null) {
            total += this.password.getBytes(Charsets.GBK).length;
        }

        ++total;
        if (this.address != null) {
            total += this.address.getBytes(Charsets.GBK).length;
        }

        ++total;
        total += 2;
        ++total;
        total += 2;
        ++total;
        if (this.factoryId != null) {
            total += this.factoryId.length;
        }

        ++total;
        if (this.hardwareVer != null) {
            total += this.hardwareVer.getBytes(Charsets.GBK).length;
        }

        ++total;
        if (this.firmwareVer != null) {
            total += this.firmwareVer.getBytes(Charsets.GBK).length;
        }

        ++total;
        total += 2;
        return total;
    }

    public void from(byte[] src) {
        ByteIO tmp = new ByteIO(200);
        ByteIO io = new ByteIO(src.length + 1);
        io.put(src);
        io.put((byte) 59);
        io.flip();
        byte index = 0;

        while (io.hasRemaining()) {
            byte a = io.get();
            if (a == 59) {
                tmp.flip();
                byte[] data;
                switch (index) {
                    case 0:
                        data = tmp.getRemaining();
                        if (data != null) {
                            this.url = new String(data, Charsets.GBK);
                        }
                        break;
                    case 1:
                        data = tmp.getRemaining();
                        if (data != null) {
                            this.apn = new String(data, Charsets.GBK);
                        }
                        break;
                    case 2:
                        data = tmp.getRemaining();
                        if (data != null) {
                            this.account = new String(data, Charsets.GBK);
                        }
                        break;
                    case 3:
                        data = tmp.getRemaining();
                        if (data != null) {
                            this.password = new String(data, Charsets.GBK);
                        }
                        break;
                    case 4:
                        data = tmp.getRemaining();
                        if (data != null) {
                            this.address = new String(data, Charsets.GBK);
                        }
                        break;
                    case 5:
                        this.tcpPort = tmp.getUshort();
                        break;
                    case 6:
                        this.udpPort = tmp.getUshort();
                        break;
                    case 7:
                        this.factoryId = tmp.getRemaining();
                        break;
                    case 8:
                        data = tmp.getRemaining();
                        if (data != null) {
                            this.hardwareVer = new String(data, Charsets.GBK);
                        }
                        break;
                    case 9:
                        data = tmp.getRemaining();
                        if (data != null) {
                            this.firmwareVer = new String(data, Charsets.GBK);
                        }
                        break;
                    case 10:
                        this.timeout = io.getUshort();
                }

                tmp.clear();
            } else {
                tmp.put(a);
            }
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        if (this.url != null) {
            io.put(this.url.getBytes(Charsets.GBK));
        }

        io.put((byte) 59);
        if (this.apn != null) {
            io.put(this.apn.getBytes(Charsets.GBK));
        }

        io.put((byte) 59);
        if (this.account != null) {
            io.put(this.account.getBytes(Charsets.GBK));
        }

        io.put((byte) 59);
        if (this.password != null) {
            io.put(this.password.getBytes(Charsets.GBK));
        }

        io.put((byte) 59);
        if (this.address != null) {
            io.put(this.address.getBytes(Charsets.GBK));
        }

        io.put((byte) 59);
        io.putUshort(this.tcpPort);
        io.put((byte) 59);
        io.putUshort(this.udpPort);
        io.put((byte) 59);
        if (this.factoryId != null) {
            io.put(this.factoryId);
        }

        io.put((byte) 59);
        if (this.hardwareVer != null) {
            io.put(this.hardwareVer.getBytes(Charsets.GBK));
        }

        io.put((byte) 59);
        if (this.firmwareVer != null) {
            io.put(this.firmwareVer.getBytes(Charsets.GBK));
        }

        io.put((byte) 59);
        io.putUshort(this.timeout);
        return io.array();
    }
}
