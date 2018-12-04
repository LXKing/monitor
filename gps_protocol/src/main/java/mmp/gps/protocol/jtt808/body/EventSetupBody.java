package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventSetupBody implements IPacket {

    private byte type;
    private short total;
    private List events;


    public byte getType() {
        return this.type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public short getTotal() {
        return this.total;
    }

    public void setTotal(short total) {
        this.total = total;
    }

    public List getEvents() {
        return this.events;
    }

    public void setEvents(List events) {
        this.total = (short) (events == null ? 0 : events.size());
        this.events = events;
    }

    public int size() {
        int eventsSize = 0;
        EventSetupInfo info;
        if (this.events != null) {
            for (Iterator var2 = this.events.iterator(); var2.hasNext(); eventsSize += info.size()) {
                info = (EventSetupInfo) var2.next();
            }
        }

        return 2 + eventsSize;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.type = io.get();
        this.total = (short) io.get();
        this.events = new ArrayList(this.total);

        while (io.hasRemaining()) {
            EventSetupInfo info = new EventSetupInfo();
            info.from(io);
            this.events.add(info);
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.put(this.type);
        io.putUbyte((short) (this.events == null ? 0 : this.events.size()));
        if (this.events != null) {
            Iterator var2 = this.events.iterator();

            while (var2.hasNext()) {
                EventSetupInfo info = (EventSetupInfo) var2.next();
                io.put(info.array());
            }
        }

        return io.array();
    }
}
