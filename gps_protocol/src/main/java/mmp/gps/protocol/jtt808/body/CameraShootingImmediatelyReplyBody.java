package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CameraShootingImmediatelyReplyBody implements IPacket {

    private int responseSerialNumber;
    private byte result;
    private int total;
    private List idList;


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

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getIdList() {
        return this.idList;
    }

    public void setIdList(List idList) {
        this.idList = idList;
    }

    public int size() {
        return this.result == 0 ? 5 + (this.idList == null ? 0 : this.idList.size() * 4) : 3;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.responseSerialNumber = io.getUshort();
        this.result = io.get();
        if (this.result == 0) {
            this.total = io.getUshort();
            if (this.total > 0) {
                this.idList = new ArrayList();
                this.idList.add(Long.valueOf(io.getUint()));
            }
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUshort(this.responseSerialNumber);
        io.put(this.result);
        if (this.result == 0) {
            io.putUshort(this.idList == null ? 0 : this.idList.size());
            if (this.idList != null) {
                Iterator var2 = this.idList.iterator();

                while (var2.hasNext()) {
                    Long id = (Long) var2.next();
                    io.putUint(id.longValue());
                }
            }
        }

        return io.array();
    }
}
