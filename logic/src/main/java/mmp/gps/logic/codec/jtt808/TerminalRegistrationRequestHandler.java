package mmp.gps.logic.codec.jtt808;

import mmp.gps.domain.device.DeviceRegistrationDto;
import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.gateway.RawData;
import mmp.gps.logic.contract.ICommandHandler;
import mmp.gps.logic.service.DeviceService;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.body.TerminalRegistrationRequestBody;
import org.springframework.beans.factory.annotation.Autowired;

public class TerminalRegistrationRequestHandler implements ICommandHandler {

    @Autowired
    private DeviceService deviceService;
    private Object[] keys = new Object[]{Integer.valueOf(256)};


    public Object[] getKeys() {
        return this.keys;
    }

    public void process(RawData raw, byte[] data, BulkDataDto bulk) {
        TerminalRegistrationRequestBody body = new TerminalRegistrationRequestBody();
        JTT808Packet packet = new JTT808Packet(body);

        try {

            packet.from(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DeviceRegistrationDto dto = new DeviceRegistrationDto();
        dto.number = packet.getNumber();
        dto.cityId = body.getCityId();
        dto.factoryId = new String(body.getFactoryId());
        dto.deviceId = new String(body.getDeviceId());
        dto.vehicleId = body.getVehicleId();
        dto.model = new String(body.getModel());
        dto.provinceId = body.getProvinceId();
        dto.vehiclePlateColor = body.getVehiclePlateColor();
        this.deviceService.regist(dto);
    }
}
