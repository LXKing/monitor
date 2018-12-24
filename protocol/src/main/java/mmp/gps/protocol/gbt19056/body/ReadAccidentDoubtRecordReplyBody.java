package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadAccidentDoubtRecordReplyBody implements IPacket {

    private List accidentDoubts = new ArrayList();


    public List getAccidentDoubts() {
        return this.accidentDoubts;
    }

    public void setAccidentDoubts(List accidentDoubts) {
        this.accidentDoubts = accidentDoubts;
    }

    public int size() {
        return this.accidentDoubts == null ? 0 : this.accidentDoubts.size() * 234;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);

        while (io.hasRemaining()) {
            AccidentDoubtInfo info = new AccidentDoubtInfo();
            info.from(io.getBytes(234));
            this.accidentDoubts.add(info);
        }

    }

    public byte[] array() {
        if (this.accidentDoubts != null && this.accidentDoubts.size() > 0) {
            byte[] data = new byte[this.size()];
            ByteIO io = new ByteIO(data);
            Iterator var3 = this.accidentDoubts.iterator();

            while (var3.hasNext()) {
                AccidentDoubtInfo info = (AccidentDoubtInfo) var3.next();
                io.put(info.array());
            }

            return data;
        } else {
            return null;
        }
    }
}
