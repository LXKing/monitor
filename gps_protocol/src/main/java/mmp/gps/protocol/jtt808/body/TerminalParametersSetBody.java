package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TerminalParametersSetBody implements IPacket {

    private short total;
    private List parameters;


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
        this.parameters = parameters;
    }

    public int size() {
        int parametersSize = 0;
        TerminalParameterInfo info;
        if (this.parameters != null) {
            for (Iterator var2 = this.parameters.iterator(); var2.hasNext(); parametersSize += info.size()) {
                info = (TerminalParameterInfo) var2.next();
            }
        }

        return 1 + parametersSize;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.total = io.getUbyte();
        this.parameters = new ArrayList(this.total);

        while (io.hasRemaining()) {
            TerminalParameterInfo info = new TerminalParameterInfo();
            info.from(io);
            this.parameters.add(info);
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUbyte(this.total);
        if (this.parameters != null) {
            Iterator var2 = this.parameters.iterator();

            while (var2.hasNext()) {
                TerminalParameterInfo info = (TerminalParameterInfo) var2.next();
                io.put(info.array());
            }
        }

        return io.array();
    }
}
