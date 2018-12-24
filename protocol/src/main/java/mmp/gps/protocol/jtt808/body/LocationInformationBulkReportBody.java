package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LocationInformationBulkReportBody implements IPacket {

    private int total;
    private byte type;
    private List list = new ArrayList();


    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public byte getType() {
        return this.type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public List getList() {
        return this.list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int size() {
        int listSize = 0;
        LocationInformationReportBody locationInformationReportBody;
        if (this.list != null) {
            for (Iterator var2 = this.list.iterator(); var2.hasNext(); listSize += 2 + locationInformationReportBody.size()) {
                locationInformationReportBody = (LocationInformationReportBody) var2.next();
            }
        }

        return 3 + listSize;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.total = io.getUshort();
        this.type = io.get();

        while (io.hasRemaining()) {
            int bodySize = io.getUshort();
            byte[] bodyData = io.getBytes(bodySize);
            LocationInformationReportBody body = new LocationInformationReportBody();
            body.from(bodyData);
            this.list.add(body);
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUshort(this.list == null ? 0 : this.list.size());
        io.put(this.type);
        if (this.list != null) {
            Iterator var2 = this.list.iterator();

            while (var2.hasNext()) {
                LocationInformationReportBody body = (LocationInformationReportBody) var2.next();
                io.putUshort(body.size());
                io.put(body.array());
            }
        }

        return io.array();
    }
}
