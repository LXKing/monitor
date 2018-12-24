package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class LocationInformationAddonInfo implements IPacket {

    private Map list = new HashMap();


    public Long getMileage() {
        if (!this.list.containsKey(Short.valueOf((short) 1))) {
            return null;
        } else {
            byte[] data = (byte[]) this.list.get(Short.valueOf((short) 1));
            ByteIO io = new ByteIO(data);
            return Long.valueOf(io.getUint());
        }
    }

    public void setMileage(Long mileage) {
        ByteIO io = new ByteIO(4);
        io.putUint(mileage.longValue());
        this.list.put(Short.valueOf((short) 1), io.array());
    }

    public Integer getOilMass() {
        if (!this.list.containsKey(Short.valueOf((short) 2))) {
            return null;
        } else {
            byte[] data = (byte[]) this.list.get(Short.valueOf((short) 2));
            ByteIO io = new ByteIO(data);
            return Integer.valueOf(io.getUshort());
        }
    }

    public void setOilMass(Integer oilMass) {
        ByteIO io = new ByteIO(2);
        io.putUshort(oilMass.intValue());
        this.list.put(Short.valueOf((short) 2), io.array());
    }

    public Integer getVss() {
        if (!this.list.containsKey(Short.valueOf((short) 3))) {
            return null;
        } else {
            byte[] data = (byte[]) this.list.get(Short.valueOf((short) 3));
            ByteIO io = new ByteIO(data);
            return Integer.valueOf(io.getUshort());
        }
    }

    public void setVss(Integer vss) {
        ByteIO io = new ByteIO(2);
        io.putUshort(vss.intValue());
        this.list.put(Short.valueOf((short) 3), io.array());
    }

    public Integer getAlarmId() {
        if (!this.list.containsKey(Short.valueOf((short) 4))) {
            return null;
        } else {
            byte[] data = (byte[]) this.list.get(Short.valueOf((short) 4));
            ByteIO io = new ByteIO(data);
            return Integer.valueOf(io.getUshort());
        }
    }

    public void setAlarmId(Integer id) {
        ByteIO io = new ByteIO(2);
        io.putUshort(id.intValue());
        this.list.put(Short.valueOf((short) 4), io.array());
    }

    public OverspeedAddonInfo getOverspeedAddonInfo() {
        if (!this.list.containsKey(Short.valueOf((short) 17))) {
            return null;
        } else {
            OverspeedAddonInfo info = new OverspeedAddonInfo();
            byte[] data = (byte[]) this.list.get(Short.valueOf((short) 17));
            ByteIO io = new ByteIO(data);
            info.setType(io.get());
            if (io.hasRemaining()) {
                info.setAreaId(Long.valueOf(io.getUint()));
            }

            return info;
        }
    }

    public void setOverspeedAddonInfo(OverspeedAddonInfo overspeedAddonInfo) {
        ByteIO io = new ByteIO(1);
        if (overspeedAddonInfo.getAreaId() != null) {
            io = new ByteIO(5);
        }

        io.put(overspeedAddonInfo.getType());
        if (overspeedAddonInfo.getAreaId() != null) {
            io.putUint(overspeedAddonInfo.getAreaId().longValue());
        }

        this.list.put(Short.valueOf((short) 17), io.array());
    }

    public EnterExitAreaInfo getEnterExitAreaInfo() {
        if (!this.list.containsKey(Short.valueOf((short) 18))) {
            return null;
        } else {
            EnterExitAreaInfo info = new EnterExitAreaInfo();
            byte[] data = (byte[]) this.list.get(Short.valueOf((short) 18));
            ByteIO io = new ByteIO(data);
            info.setType(io.get());
            info.setAreaId(io.getUint());
            info.setFlag(io.get());
            return info;
        }
    }

    public void setEnterExitAreaInfo(EnterExitAreaInfo enterExitAreaInfo) {
        ByteIO io = new ByteIO(6);
        io.put(enterExitAreaInfo.getType());
        io.putUint(enterExitAreaInfo.getAreaId());
        io.put(enterExitAreaInfo.getFlag());
        this.list.put(Short.valueOf((short) 18), io.array());
    }

    public RouteDrivingInfo getRouteDrivingInfo() {
        if (!this.list.containsKey(Short.valueOf((short) 19))) {
            return null;
        } else {
            RouteDrivingInfo info = new RouteDrivingInfo();
            byte[] data = (byte[]) this.list.get(Short.valueOf((short) 19));
            ByteIO io = new ByteIO(data);
            info.setRouteId(io.getUint());
            info.setSeconds(io.getUshort());
            info.setResult(io.get());
            return info;
        }
    }

    public void setRouteDrivingInfo(RouteDrivingInfo shortTravelTimeInfo) {
        ByteIO io = new ByteIO(7);
        io.putUint(shortTravelTimeInfo.getRouteId());
        io.putUshort(shortTravelTimeInfo.getSeconds());
        io.put(shortTravelTimeInfo.getResult());
        this.list.put(Short.valueOf((short) 19), io.array());
    }

    public Long getExtendStatus() {
        if (!this.list.containsKey(Short.valueOf((short) 37))) {
            return null;
        } else {
            byte[] data = (byte[]) this.list.get(Short.valueOf((short) 37));
            ByteIO io = new ByteIO(data);
            return Long.valueOf(io.getUint());
        }
    }

    public void setExtendStatus(Long status) {
        ByteIO io = new ByteIO(4);
        io.putUint(status.longValue());
        this.list.put(Short.valueOf((short) 37), io.array());
    }

    public Integer getIoStatus() {
        if (!this.list.containsKey(Short.valueOf((short) 42))) {
            return null;
        } else {
            byte[] data = (byte[]) this.list.get(Short.valueOf((short) 42));
            ByteIO io = new ByteIO(data);
            return Integer.valueOf(io.getUshort());
        }
    }

    public void setIoStatus(Integer status) {
        ByteIO io = new ByteIO(2);
        io.putUshort(status.intValue());
        this.list.put(Short.valueOf((short) 42), io.array());
    }

    public Long getAnalogData() {
        if (!this.list.containsKey(Short.valueOf((short) 43))) {
            return null;
        } else {
            byte[] data = (byte[]) this.list.get(Short.valueOf((short) 43));
            ByteIO io = new ByteIO(data);
            return Long.valueOf(io.getUint());
        }
    }

    public void setAnalogData(Long analogQuantity) {
        ByteIO io = new ByteIO(4);
        io.putUint(analogQuantity.longValue());
        this.list.put(Short.valueOf((short) 43), io.array());
    }

    public Short getNetworkSignal() {
        if (!this.list.containsKey(Short.valueOf((short) 48))) {
            return null;
        } else {
            byte[] data = (byte[]) this.list.get(Short.valueOf((short) 48));
            ByteIO io = new ByteIO(data);
            return Short.valueOf(io.getUbyte());
        }
    }

    public void setNetworkSignal(Short networkSignal) {
        ByteIO io = new ByteIO(1);
        io.putUbyte(networkSignal.shortValue());
        this.list.put(Short.valueOf((short) 48), io.array());
    }

    public Short getSatellites() {
        if (!this.list.containsKey(Short.valueOf((short) 49))) {
            return null;
        } else {
            byte[] data = (byte[]) this.list.get(Short.valueOf((short) 49));
            ByteIO io = new ByteIO(data);
            return Short.valueOf(io.getUbyte());
        }
    }

    public void setSatellites(Short satellites) {
        ByteIO io = new ByteIO(1);
        io.putUbyte(satellites.shortValue());
        this.list.put(Short.valueOf((short) 49), io.array());
    }

    public int size() {
        int size = 0;

        Entry entry;
        for (Iterator var3 = this.list.entrySet().iterator(); var3.hasNext(); size += ((byte[]) entry.getValue()).length) {
            entry = (Entry) var3.next();
            size += 2;
        }

        return size;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);

        while (io.hasRemaining()) {
            short id = io.getUbyte();
            short size = io.getUbyte();
            this.list.put(Short.valueOf(id), io.getBytes(size));
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        Iterator var3 = this.list.entrySet().iterator();

        while (var3.hasNext()) {
            Entry entry = (Entry) var3.next();
            io.putUbyte(((Short) entry.getKey()).shortValue());
            io.putUbyte((short) ((byte[]) entry.getValue()).length);
            io.put((byte[]) entry.getValue());
        }

        return io.array();
    }
}
