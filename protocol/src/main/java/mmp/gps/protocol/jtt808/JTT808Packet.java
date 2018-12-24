package mmp.gps.protocol.jtt808;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.math.BigInteger;

public class JTT808Packet {

    public final byte HEADER = 126;
    public final byte TAIL = 126;

    private int state;

    /*
     * 标识位 0x7e
     * */
    private byte header;


    /*
     * 消息头
     * */

    // 消息 ID
    private int command;
    // 是否加密
    private int flag;
    // 终端手机号 BCD[n] 8421 码，n 字节
    private String number;
    // 消息流水号
    private int serialNumber;
    // 消息包封装项 如果消息体属性中相关标识位确定消息分包处理，则该项有内容，否则无该项
    private int pages;
    private int page;

    /*
     * 消息包（整个包，包含消息体）
     * */
    private IPacket body;

    /*
     * 检验码
     * */

    private short xor;

    /*
     * 标识位 0x7e
     * */
    private byte tail;


    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public JTT808Packet() {
    }

    public JTT808Packet(IPacket body) {
        this.body = body;
    }

    public byte getHeader() {
        return this.header;
    }

    public void setHeader(byte header) {
        this.header = header;
    }

    public int getCommand() {
        return this.command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public boolean isPaging() {
        return (this.flag & 8192) == 8192;
    }

    public void setPaging(boolean paging) {
        if (paging) {
            // \u后面必须跟4个十六进制数字（不足四位前面用零补齐）
            // this.flag = this.flag & 0xdfff;
            this.flag &= '\udfff';
        } else {
            this.flag |= 8192;
        }

    }

    // 100 0000 0000
    public boolean isEncrypting() {
        return (this.flag & 1024) == 1024;
    }

    public void setEncrypting(boolean encrypting) {
        if (encrypting) {
            this.flag |= 1024;
        } else {
            // 1110 0011 1111 1111
            this.flag &= '\ue3ff';
        }

    }

    // 11 1111 1111
    public int getBodyLength() {
        return this.flag & 1023;
    }

    public void setBodyLength(int bodyLength) {
        // 1111 1100 0000 0000
        this.flag &= '\ufc00';
        this.flag |= bodyLength;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public IPacket getBody() {
        return this.body;
    }

    public void setBody(IPacket body) {
        this.body = body;
    }

    public short getXor() {
        return this.xor;
    }

    public void setXor(short xor) {
        this.xor = xor;
    }

    public byte getTail() {
        return this.tail;
    }

    public void setTail(byte tail) {
        this.tail = tail;
    }


    private int size() {
        return (this.isPaging() ? 19 : 15) + (this.body == null ? 0 : this.body.size());
    }

    public void from(byte[] src) throws Exception {
        ByteIO io = new ByteIO(src);
        this.header = io.get();
        this.command = io.getUshort();
        this.flag = io.getUshort();
        BigInteger bi = new BigInteger(io.getBytes(6));
        this.number = bi.toString(16);
        this.serialNumber = io.getUshort();
        if (this.isPaging()) {
            this.pages = io.getUshort();
            this.page = io.getUshort();
        }

        int bodyLength = this.getBodyLength();
        if (bodyLength > 0) {
            byte[] by = io.getBytes(bodyLength);
            this.body.from(by);
        }

        this.xor = io.getUbyte();
        this.tail = io.get();
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        this.setBodyLength(this.body == null ? 0 : this.body.size());
        ByteIO io = new ByteIO(data);
        io.put((byte) HEADER);
        io.putUshort(this.command);
        io.putUshort(this.flag);
        BigInteger bi = new BigInteger(this.number, 16);
        byte[] numbers = bi.toByteArray();
        io.put(numbers);
        io.putUshort(this.serialNumber);
        if (this.isPaging()) {
            io.putUshort(this.pages);
            io.putUshort(this.page);
        }

        if (this.body != null) {
            io.put(this.body.array());
        }

        io.putUbyte(this.xor);
        io.put((byte) TAIL);
        JTT808Util.xor(data);
        return JTT808Util.escape(data);
    }
}


