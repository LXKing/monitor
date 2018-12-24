package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PacketResendRequestBody implements IPacket {

    private int matchSerialNumber;
    private short total;
    private List pages;


    public int getMatchSerialNumber() {
        return this.matchSerialNumber;
    }

    public void setMatchSerialNumber(int matchSerialNumber) {
        this.matchSerialNumber = matchSerialNumber;
    }

    public short getTotal() {
        return this.total;
    }

    public void setTotal(short total) {
        this.total = total;
    }

    public List getPages() {
        return this.pages;
    }

    public void setPages(List pages) {
        this.pages = pages;
    }

    public int size() {
        return 3 + (this.pages == null ? 0 : this.pages.size() * 2);
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.matchSerialNumber = io.getUshort();
        this.total = io.getUbyte();
        if (this.total > 0) {
            this.pages = new ArrayList(this.total);

            while (io.hasRemaining()) {
                this.pages.add(Integer.valueOf(io.getUshort()));
            }
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUshort(this.matchSerialNumber);
        io.putUbyte(this.total);
        if (this.pages != null && this.pages.size() > 0) {
            Iterator var2 = this.pages.iterator();

            while (var2.hasNext()) {
                Integer page = (Integer) var2.next();
                io.putUshort(page.intValue());
            }
        }

        return io.array();
    }
}
