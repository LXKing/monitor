package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class TerminalLinkServerInfo implements IPacket {

    private byte flag;
    private String authenticationCode;
    private String apn;
    private String account;
    private String password;
    private String address;
    private int tcpPort;
    private int udpPort;
    private int timeout;


    public byte getFlag() {
        return this.flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public String getAuthenticationCode() {
        return this.authenticationCode;
    }

    public void setAuthenticationCode(String authenticationCode) {
        this.authenticationCode = authenticationCode;
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

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int size() {
        byte total = 0;
        int var2 = total + 1;
        if (this.flag == 0) {
            if (this.authenticationCode != null) {
                var2 += this.authenticationCode.getBytes(Charsets.GBK).length;
            }

            ++var2;
            if (this.apn != null) {
                var2 += this.apn.getBytes(Charsets.GBK).length;
            }

            ++var2;
            if (this.account != null) {
                var2 += this.account.getBytes(Charsets.GBK).length;
            }

            ++var2;
            if (this.password != null) {
                var2 += this.password.getBytes(Charsets.GBK).length;
            }

            ++var2;
            if (this.address != null) {
                var2 += this.address.getBytes(Charsets.GBK).length;
            }

            ++var2;
            var2 += 2;
            ++var2;
            var2 += 2;
            ++var2;
            var2 += 2;
        }

        return var2;
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
                            this.flag = io.get();
                        }
                        break;
                    case 1:
                        data = tmp.getRemaining();
                        if (data != null) {
                            this.authenticationCode = new String(data, Charsets.GBK);
                        }
                        break;
                    case 2:
                        data = tmp.getRemaining();
                        if (data != null) {
                            this.apn = new String(data, Charsets.GBK);
                        }
                        break;
                    case 3:
                        data = tmp.getRemaining();
                        if (data != null) {
                            this.account = new String(data, Charsets.GBK);
                        }
                        break;
                    case 4:
                        data = tmp.getRemaining();
                        if (data != null) {
                            this.password = new String(data, Charsets.GBK);
                        }
                        break;
                    case 5:
                        data = tmp.getRemaining();
                        if (data != null) {
                            this.address = new String(data, Charsets.GBK);
                        }
                        break;
                    case 6:
                        this.tcpPort = tmp.getUshort();
                        break;
                    case 7:
                        this.udpPort = tmp.getUshort();
                        break;
                    case 8:
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
        io.put(this.flag);
        if (this.flag == 0) {
            if (this.authenticationCode != null) {
                io.put(this.authenticationCode.getBytes(Charsets.GBK));
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
            io.putUshort(this.timeout);
        }

        return io.array();
    }
}
