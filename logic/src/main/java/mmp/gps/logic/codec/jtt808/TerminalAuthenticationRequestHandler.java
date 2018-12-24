package mmp.gps.logic.codec.jtt808;

import mmp.gps.domain.device.Device;
import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.gateway.RawData;
import mmp.gps.logic.cache.Devices;
import mmp.gps.logic.contract.ICommandHandler;

public class TerminalAuthenticationRequestHandler implements ICommandHandler {

    private Object[] keys = new Object[]{Integer.valueOf(258)};


    public Object[] getKeys() {
        return this.keys;
    }

    public void process(RawData raw, byte[] data, BulkDataDto bulk) {
        Device device = Devices.getCurrent().get(raw.getDn(), raw.getType());
        device.setOnline();
    }
}
