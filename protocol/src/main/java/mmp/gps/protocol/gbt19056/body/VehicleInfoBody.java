package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.BytesUtil;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class VehicleInfoBody implements IPacket {

    private String vehicleIdcode;
    private String plateNumber;
    private String category;


    public String getVehicleIdcode() {
        return this.vehicleIdcode;
    }

    public void setVehicleIdcode(String vehicleIdcode) {
        this.vehicleIdcode = vehicleIdcode;
    }

    public String getPlateNumber() {
        return this.plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int size() {
        return 41;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.vehicleIdcode = (new String(io.getBytes(17), Charsets.ASCII)).trim();
        this.plateNumber = (new String(io.getBytes(12), Charsets.GB2312)).trim();
        this.category = (new String(io.getBytes(12), Charsets.GB2312)).trim();
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.put(BytesUtil.fixed(this.vehicleIdcode, Charsets.ASCII, 17));
        io.put(BytesUtil.fixed(this.plateNumber, Charsets.GB2312, 12));
        io.put(BytesUtil.fixed(this.category, Charsets.GB2312, 12));
        return data;
    }
}
