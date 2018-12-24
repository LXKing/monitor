package mmp.gps.protocol.kangkaisi;

public class KangkaisiPacket79 {

    public final byte[] HEADER = new byte[]{(byte) 121, (byte) 121};
    public final byte[] TAIL = new byte[]{(byte) 13, (byte) 10};
    private int PacketLength;
    private short ProtocolNumber;
    private IPacket body;
    private int serialNumber;
    private int xor;
    private int header;
    private int tail;


    public int getHeader() {
        return this.header;
    }

    public void setHeader(int header) {
        this.header = header;
    }

    public KangkaisiPacket79() {
    }

    public KangkaisiPacket79(IPacket body) {
        this.body = body;
    }

    public int getPacketLength() {
        return this.PacketLength;
    }

    public void setPacketLength(int packetLength) {
        this.PacketLength = packetLength;
    }

    public short getProtocolNumber() {
        return this.ProtocolNumber;
    }

    public void setProtocolNumber(short protocolNumber) {
        this.ProtocolNumber = protocolNumber;
    }

    public IPacket getBody() {
        return this.body;
    }

    public void setBody(IPacket body) {
        this.body = body;
    }

    public int getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getXor() {
        return this.xor;
    }

    public void setXor(int xor) {
        this.xor = xor;
    }

    public int getTail() {
        return this.tail;
    }

    public void setTail(int tail) {
        this.tail = tail;
    }

    private int size() {
        return 11 + (this.body == null ? 0 : this.body.size());
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.header = io.getUshort();
        this.PacketLength = io.getUshort();
        this.ProtocolNumber = io.getUbyte();
        int bodyLength = this.PacketLength - 5;
        if (bodyLength > 0) {
            byte[] by = io.getBytes(bodyLength);
            this.body.from(by);
        }

        this.serialNumber = io.getUshort();
        this.xor = io.getUshort();
        this.tail = io.getUshort();
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        this.setPacketLength((short) (5 + (this.body == null ? 0 : this.body.size())));
        ByteIO io = new ByteIO(data);
        io.put(this.HEADER);
        io.putUshort(this.PacketLength);
        io.putUbyte(this.ProtocolNumber);
        if (this.body != null) {
            io.put(this.body.array());
        }

        io.putUshort(this.serialNumber);
        io.putUshort(KangkaisiUtil.GetCrc16(data, this.PacketLength - 1));
        io.put(this.TAIL);
        return data;
    }

    public String toString() {
        return "KangkaisiPacket [PacketLength=" + this.PacketLength + ", ProtocolNumber=" + this.ProtocolNumber + ", body=" + this.body + ", serialNumber=" + this.serialNumber + ", xor=" + this.xor + ", header=" + this.header + ", tail=" + this.tail + "]";
    }
}
