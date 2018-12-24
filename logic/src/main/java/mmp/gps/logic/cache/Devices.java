package mmp.gps.logic.cache;

import mmp.gps.domain.device.Device;
import mmp.gps.domain.device.DeviceCountOnlineDto;
import mmp.gps.domain.locate.LatestDto;
import mmp.gps.domain.track.Track;
import mmp.gps.logic.service.DeviceService;
import mmp.gps.logic.service.LocateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Devices {

    private static DeviceService deviceService;
    private static LocateService locateService;
    private static Devices devices = null;
    public static Map latandlan = new ConcurrentHashMap();
    private HashMap numberMap = null;

    @Autowired(
            required = true
    )
    public Devices(@Qualifier("deviceService") DeviceService deviceService, @Qualifier("locateService") LocateService locateService) {
        deviceService = deviceService;
        locateService = locateService;
        this.init();
    }

    public static Devices getCurrent() {
        return devices;
    }

    private synchronized void init() {
        if (devices == null) {
            devices = this;
            devices.reload();
        }
    }

    public Device get(String number, int protocolKind) {
        Device device = (Device) this.numberMap.get(number);
        if (device == null) {
            device = deviceService.create(number, protocolKind);
            this.numberMap.put(number, device);
        }

        return device;
    }

    public Device get(String number) {
        return (Device) this.numberMap.get(number);
    }

    public synchronized void put(Device device) {
        this.numberMap.put(device.getNumber(), device);
    }

    private void reload() {
        try {
            List ex = deviceService.reload();
            Map latests = locateService.loadLatest();
            this.numberMap = new HashMap(ex.size());
            Iterator var3 = ex.iterator();

            while (var3.hasNext()) {
                Device device = (Device) var3.next();
                this.numberMap.put(device.getNumber(), device);
                LatestDto dto = (LatestDto) latests.get(device.getNumber());
                if (dto != null) {
                    device.setServerTime(dto.st);
                    Track track = new Track();
                    track.setVal((byte) 1);
                    track.setA(dto.a);
                    track.setD(dto.d);
                    track.setLat(dto.lat);
                    track.setLng(dto.lng);
                    track.setM((double) dto.m);
                    track.setS(dto.s);
                    track.setSp((float) dto.sp);
                    track.setGt(dto.gt);
                    track.setSt(dto.st);
                    track.setOil(dto.oil);
                    track.setVss(dto.vss);
                    track.setOvt(dto.ovt);
                    track.setOid(dto.oid);
                    track.setIot(dto.iot);
                    track.setIid(dto.iid);
                    track.setIof(dto.iof);
                    track.setRid(dto.rid);
                    track.setRt(dto.rt);
                    track.setRf(dto.rf);
                    track.setAid(dto.aid);
                    track.setExs(dto.exs);
                    track.setIos(dto.ios);
                    track.setAd0(dto.ad0);
                    track.setAd1(dto.ad1);
                    track.setNet(dto.net);
                    track.setSat(dto.sat);
                    device.setTrack(track);
                }
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    public DeviceCountOnlineDto countAllOnline(int timeout) {
        DeviceCountOnlineDto dto = new DeviceCountOnlineDto();
        int online = 0;
        int total = this.numberMap.size();
        Iterator var5 = this.numberMap.values().iterator();

        while (var5.hasNext()) {
            Device device = (Device) var5.next();
            if (device.isOnline(timeout * 1000) == 1) {
                ++online;
            }
        }

        dto.total = total;
        dto.online = online;
        dto.offline = total - online;
        return dto;
    }

}
