package mmp.gps.logic.codec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import mmp.gps.common.util.PackageUtil;
import mmp.gps.domain.device.Device;
import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.gateway.RawData;
import mmp.gps.domain.log.DataLogDto;
import mmp.gps.logic.cache.Devices;
import mmp.gps.logic.contract.ICommandHandler;
import mmp.gps.logic.contract.IRawDataParser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class JTT808Parser implements IRawDataParser {

    private HashMap commandHandlers = new HashMap();


    public int getProtocolKind() {
        return 1;
    }

    public JTT808Parser() {
        List classess = PackageUtil.getSubClasses("mmp.gps.logic.codec.jtt808", ICommandHandler.class);
        Iterator var2 = classess.iterator();

        while (var2.hasNext()) {
            Class c = (Class) var2.next();

            try {
                ICommandHandler e = (ICommandHandler) c.newInstance();
                Object[] keys = e.getKeys();
                Object[] var6 = keys;
                int var7 = keys.length;

                for (int var8 = 0; var8 < var7; ++var8) {
                    Object key = var6[var8];
                    this.commandHandlers.put(key, e);
                }
            } catch (InstantiationException var10) {
                var10.printStackTrace();
            } catch (IllegalAccessException var11) {
                var11.printStackTrace();
            }
        }

    }

    public void parse(RawData raw, BulkDataDto bulk) {
        byte[] data = Base64.decode(raw.getTop());
        int command = (data[1] & 255) << 8 | data[2] & 255;
        Device device = Devices.getCurrent().get(raw.getDn(), raw.getType());
        device.setOnline();
        if (device.isDebugging()) {
            DataLogDto handler = new DataLogDto();
            handler.number = device.getNumber();
            handler.raw = raw.getTop();
            handler.time = raw.getTime();
            bulk.add(handler);
            List after = raw.getAfter();
            if (after != null) {
                Iterator var8 = after.iterator();

                while (var8.hasNext()) {
                    String row = (String) var8.next();
                    handler = new DataLogDto();
                    handler.number = device.getNumber();
                    handler.raw = row;
                    handler.time = raw.getTime();
                    bulk.add(handler);
                }
            }
        }

        ICommandHandler handler1 = (ICommandHandler) this.commandHandlers.get(Integer.valueOf(command));
        if (handler1 != null) {
            handler1.process(raw, data, bulk);
        }
    }
}
