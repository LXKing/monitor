package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultimediaDataRetrievalReplyBody implements IPacket {

    private int responseSerialNumber;
    private int total;
    private List list = new ArrayList();


    public int getResponseSerialNumber() {
        return this.responseSerialNumber;
    }

    public void setResponseSerialNumber(int responseSerialNumber) {
        this.responseSerialNumber = responseSerialNumber;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getList() {
        return this.list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int size() {
        return 4 + (this.list == null ? 0 : this.list.size() * 35);
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.responseSerialNumber = io.getUshort();
        this.total = io.getUshort();

        while (io.hasRemaining()) {
            MultimediaDataRetrievalReplyInfo info = new MultimediaDataRetrievalReplyInfo();
            info.from(io.getBytes(35));
            this.list.add(info);
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUshort(this.responseSerialNumber);
        io.putUshort(this.list == null ? 0 : this.list.size());
        Iterator var2 = this.list.iterator();

        while (var2.hasNext()) {
            MultimediaDataRetrievalReplyInfo info = (MultimediaDataRetrievalReplyInfo) var2.next();
            io.put(info.array());
        }

        return io.array();
    }
}
