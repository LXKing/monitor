package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InformationMenuSetupBody implements IPacket {

    private short type;
    private short total;
    private List menus;


    public short getType() {
        return this.type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public short getTotal() {
        return this.total;
    }

    public void setTotal(short total) {
        this.total = total;
    }

    public List getMenus() {
        return this.menus;
    }

    public void setMenus(List menus) {
        this.total = (short) (menus == null ? 0 : menus.size());
        this.menus = menus;
    }

    public int size() {
        int emnuSize = 0;
        InformationMenuSetupInfo info;
        if (this.menus != null) {
            for (Iterator var2 = this.menus.iterator(); var2.hasNext(); emnuSize += info.size()) {
                info = (InformationMenuSetupInfo) var2.next();
            }
        }

        return 2 + emnuSize;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.type = io.getUbyte();
        this.total = io.getUbyte();
        this.menus = new ArrayList();
        if (this.total > 0) {
            InformationMenuSetupInfo info = new InformationMenuSetupInfo();
            info.from(io);
            this.menus.add(info);
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUbyte(this.type);
        io.putUbyte(this.total);
        if (this.menus != null) {
            Iterator var2 = this.menus.iterator();

            while (var2.hasNext()) {
                InformationMenuSetupInfo info = (InformationMenuSetupInfo) var2.next();
                io.put(info.array());
            }
        }

        return io.array();
    }
}
