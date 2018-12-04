package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class DriverInfoReportBody implements IPacket {

    private byte status;
    private GpsTime time;
    private byte result;
    private byte driverNameSize;
    private String driverName;
    private String certificateNumber;
    private byte issuingAgencySize;
    private String issuingAgencyName;
    private byte validYear1;
    private byte validYear2;
    private byte validMonth;
    private byte validDay;


    public byte getStatus() {
        return this.status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public GpsTime getTime() {
        return this.time;
    }

    public void setTime(GpsTime time) {
        this.time = time;
    }

    public byte getResult() {
        return this.result;
    }

    public void setResult(byte result) {
        this.result = result;
    }

    public byte getDriverNameSize() {
        return this.driverNameSize;
    }

    public void setDriverNameSize(byte nameSize) {
        this.driverNameSize = nameSize;
    }

    public String getDriverName() {
        return this.driverName;
    }

    public void setDriverName(String name) {
        this.driverName = name;
        if (name != null) {
            this.driverNameSize = (byte) this.driverName.getBytes(Charsets.GBK).length;
        }

    }

    public String getCertificateNumber() {
        return this.certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public byte getIssuingAgencySize() {
        return this.issuingAgencySize;
    }

    public void setIssuingAgencySize(byte issuingAgencySize) {
        this.issuingAgencySize = issuingAgencySize;
    }

    public String getIssuingAgencyName() {
        return this.issuingAgencyName;
    }

    public void setIssuingAgencyName(String issuingAgencyName) {
        this.issuingAgencyName = issuingAgencyName;
        if (issuingAgencyName != null) {
            this.issuingAgencySize = (byte) this.issuingAgencyName.getBytes(Charsets.GBK).length;
        }

    }

    public byte getValidYear1() {
        return this.validYear1;
    }

    public void setValidYear1(byte validYear1) {
        this.validYear1 = validYear1;
    }

    public byte getValidYear2() {
        return this.validYear2;
    }

    public void setValidYear2(byte validYear2) {
        this.validYear2 = validYear2;
    }

    public byte getValidMonth() {
        return this.validMonth;
    }

    public void setValidMonth(byte validMonth) {
        this.validMonth = validMonth;
    }

    public byte getValidDay() {
        return this.validDay;
    }

    public void setValidDay(byte validDay) {
        this.validDay = validDay;
    }

    public int size() {
        return this.result == 0 ? 9 + this.driverNameSize + 20 + 1 + this.issuingAgencySize + 4 : 9;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.status = io.get();
        this.time = new GpsTime();
        this.time.from(io.getBytes(6));
        this.result = io.get();
        if (this.result == 0) {
            this.driverNameSize = io.get();
            if (this.driverNameSize > 0) {
                this.driverName = new String(io.getBytes(this.driverNameSize), Charsets.GBK);
            }

            this.certificateNumber = new String(io.getBytes(20), Charsets.GBK);
            this.issuingAgencySize = io.get();
            if (this.issuingAgencySize > 0) {
                this.issuingAgencyName = new String(io.getBytes(this.issuingAgencySize), Charsets.GBK);
            }

            this.validYear1 = io.get();
            this.validYear2 = io.get();
            this.validMonth = io.get();
            this.validDay = io.get();
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.put(this.status);
        io.put(this.time.array());
        io.put(this.result);
        if (this.result == 0) {
            io.put(this.driverNameSize);
            io.put(this.driverName.getBytes(Charsets.GBK));
            byte[] certificateNumberBytes = this.certificateNumber.getBytes(Charsets.GBK);
            if (certificateNumberBytes.length < 20) {
                byte[] fill = new byte[20 - certificateNumberBytes.length];
                io.put(certificateNumberBytes);
                io.put(fill);
            } else if (certificateNumberBytes.length > 20) {
                io.put(certificateNumberBytes, 0, 20);
            } else {
                io.put(certificateNumberBytes);
            }

            io.put(this.issuingAgencySize);
            io.put(this.issuingAgencyName.getBytes(Charsets.GBK));
            io.put(this.validYear1);
            io.put(this.validYear2);
            io.put(this.validMonth);
            io.put(this.validDay);
        }

        return io.array();
    }
}
