package mmp.gps.protocol.kangkaisi;


public class KangkaisiPacket {

    public final byte[] HEADER = new byte[]{(byte) 120, (byte) 120};
    public final byte[] TAIL = new byte[]{(byte) 13, (byte) 10};
    private short PacketLength;
    private short ProtocolNumber;
    private IPacket body;
    private int serialNumber;
    private int xor;
    private int state;
    private int header;
    private int tail;


    public int getHeader() {
        return this.header;
    }

    public void setHeader(int header) {
        this.header = header;
    }

    public KangkaisiPacket() {
    }

    public KangkaisiPacket(IPacket body) {
        this.body = body;
    }

    public short getPacketLength() {
        return this.PacketLength;
    }

    public void setPacketLength(short packetLength) {
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
        return 10 + (this.body == null ? 0 : this.body.size());
    }

    public void from(byte[] src) throws Exception {
        ByteIO io = new ByteIO(src);
        int lenght = src.length;
        this.header = io.getUshort();
        this.PacketLength = io.getUbyte();
        this.ProtocolNumber = io.getUbyte();
        if (lenght == this.PacketLength + 5) {
            this.state = 1;
            int bodyLength = this.PacketLength - 5;
            if (bodyLength > 0) {
                byte[] by = io.getBytes(bodyLength);
                this.body.from(by);
            }

            this.serialNumber = io.getUshort();
            this.xor = io.getUshort();
            this.tail = io.getUshort();
        } else {
            this.state = 0;
        }

    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        this.setPacketLength((short) (5 + (this.body == null ? 0 : this.body.size())));
        ByteIO io = new ByteIO(data);
        io.put(this.HEADER);
        io.putUbyte(this.PacketLength);
        io.putUbyte(this.ProtocolNumber);
        if (this.body != null) {
            io.put(this.body.array());
        }

        io.putUshort(this.serialNumber);
        io.putUshort(KangkaisiUtil.GetCrc16(data, this.PacketLength - 1));
        io.put(this.TAIL);
        return data;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
