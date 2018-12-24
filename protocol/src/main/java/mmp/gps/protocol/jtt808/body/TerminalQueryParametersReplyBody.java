package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TerminalQueryParametersReplyBody implements IPacket {

    private int responseSerialNumber;
    private short total;
    public List parameters;


    public int getResponseSerialNumber() {
        return this.responseSerialNumber;
    }

    public void setResponseSerialNumber(int responseSerialNumber) {
        this.responseSerialNumber = responseSerialNumber;
    }

    public short getTotal() {
        return this.total;
    }

    public void setTotal(short total) {
        this.total = total;
    }

    public List getParameters() {
        return this.parameters;
    }

    public void setParameters(List parameters) {
        this.total = (short) (parameters == null ? 0 : parameters.size());
        this.parameters = parameters;
    }

    public int size() {
        int parameterSize = 0;
        TerminalParameterInfo info;
        if (this.parameters != null) {
            for (Iterator var2 = this.parameters.iterator(); var2.hasNext(); parameterSize += info.size()) {
                info = (TerminalParameterInfo) var2.next();
            }
        }

        return 3 + parameterSize;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.responseSerialNumber = io.getUshort();
        this.total = io.getUbyte();
        this.parameters = new ArrayList();

        while (io.hasRemaining()) {
            TerminalParameterInfo info = new TerminalParameterInfo();
            info.from(io);
            this.parameters.add(info);
        }

    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.putUshort(this.responseSerialNumber);
        io.putUbyte((short) (this.parameters == null ? 0 : this.parameters.size()));
        if (this.parameters != null) {
            Iterator var3 = this.parameters.iterator();

            while (var3.hasNext()) {
                TerminalParameterInfo info = (TerminalParameterInfo) var3.next();
                io.put(info.array());
            }
        }

        return data;
    }
}
