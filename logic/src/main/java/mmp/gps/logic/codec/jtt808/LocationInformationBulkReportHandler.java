package mmp.gps.logic.codec.jtt808;

import mmp.gps.common.util.DateFormats;
import mmp.gps.domain.device.Device;
import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.gateway.RawData;
import mmp.gps.domain.track.Track;
import mmp.gps.domain.track.TrackDto;
import mmp.gps.logic.cache.Devices;
import mmp.gps.logic.contract.ICommandHandler;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.body.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class LocationInformationBulkReportHandler implements ICommandHandler {

    private Object[] keys = new Object[]{Integer.valueOf(1796)};


    public Object[] getKeys() {
        return this.keys;
    }

    public void process(RawData raw, byte[] data, BulkDataDto bulk) {
        Device device = Devices.getCurrent().get(raw.getDn(), raw.getType());
        LocationInformationBulkReportBody body = new LocationInformationBulkReportBody();
        JTT808Packet packet = new JTT808Packet(body);

        try {

            packet.from(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List list = body.getList();
        Iterator var8 = list.iterator();

        while (var8.hasNext()) {
            LocationInformationReportBody report = (LocationInformationReportBody) var8.next();
            LocationInformationBaseInfo baseInfo = report.getBaseInfo();
            LocationInformationAddonInfo addonInfo = report.getAddonInfo();
            TrackDto dto = new TrackDto();
            dto.dn = device.getNumber();
            dto.a = baseInfo.getAlarms();
            dto.alt = baseInfo.getAltitude();
            dto.d = baseInfo.getAngle();
            dto.st = new Date();
            dto.o = 1;
            GpsTime gpstime = baseInfo.getGpstime();
            String gpsTimeString = gpstime.toString();

            try {
                dto.gt = DateFormats.DateTimeFormat.parse(gpsTimeString);
                long track = dto.gt.getTime();
                long vss = dto.st.getTime();
                long io = (track - vss) / 1000L;
                if (io > 300L) {
                    dto.val = 0;
                } else if (io < -2592000L) {
                    dto.val = 0;
                } else {
                    dto.val = 1;
                }
            } catch (Exception var27) {
                dto.gt = new Date();
                dto.val = 0;
            }

            dto.lat = (double) baseInfo.getLat() * 1.0E-6D;
            dto.lng = (double) baseInfo.getLng() * 1.0E-6D;
            dto.sp = (float) baseInfo.getSpeed() * 0.1F;
            dto.s = baseInfo.getStatus();
            if (addonInfo != null) {
                Long track1 = addonInfo.getMileage();
                if (track1 != null) {
                    dto.m = (double) track1.longValue() * 0.1D;
                }

                Integer oilMass = addonInfo.getOilMass();
                if (oilMass != null) {
                    dto.oil = (float) oilMass.intValue() * 0.1F;
                }

                Integer vss1 = addonInfo.getVss();
                if (vss1 != null) {
                    dto.vss = (float) vss1.intValue() * 0.1F;
                }

                OverspeedAddonInfo over = addonInfo.getOverspeedAddonInfo();
                if (over != null) {
                    Long io1 = over.getAreaId();
                    if (io1 != null) {
                        dto.oid = io1.longValue();
                    }

                    dto.ovt = over.getType();
                }

                EnterExitAreaInfo io2 = addonInfo.getEnterExitAreaInfo();
                if (io2 != null) {
                    dto.iot = io2.getType();
                    dto.iid = io2.getAreaId();
                    dto.iof = io2.getFlag();
                }

                RouteDrivingInfo route = addonInfo.getRouteDrivingInfo();
                if (route != null) {
                    dto.rid = route.getRouteId();
                    dto.rt = route.getSeconds();
                    dto.rf = route.getResult();
                }

                Integer alarmId = addonInfo.getAlarmId();
                if (alarmId != null) {
                    dto.aid = alarmId.intValue();
                }

                Long exStatus = addonInfo.getExtendStatus();
                if (exStatus != null) {
                    dto.exs = exStatus.longValue();
                }

                Integer ioStatus = addonInfo.getIoStatus();
                if (ioStatus != null) {
                    dto.ios = ioStatus.intValue();
                }

                Long analog = addonInfo.getAnalogData();
                if (analog != null) {
                    dto.ad0 = (int) (analog.longValue() >> 16);
                    dto.ad1 = (int) (analog.longValue() & 65535L);
                }

                Short network = addonInfo.getNetworkSignal();
                if (network != null) {
                    dto.net = network.shortValue();
                }

                Short satellites = addonInfo.getSatellites();
                if (satellites != null) {
                    dto.sat = satellites.shortValue();
                }
            }

            if (dto.val == 1 && dto.lat != 0.0D && dto.lng != 0.0D) {
                bulk.add(dto);
                Track track2 = new Track();
                track2.setA(dto.a);
                track2.setD(dto.d);
                track2.setVal(dto.val);
                track2.setLat(dto.lat);
                track2.setLng(dto.lng);
                track2.setO(dto.o);
                track2.setM(dto.m);
                track2.setS(dto.s);
                track2.setSp(dto.sp);
                track2.setGt(dto.gt);
                track2.setSt(dto.st);
                track2.setOil(dto.oil);
                track2.setVss(dto.vss);
                track2.setOvt(dto.ovt);
                track2.setOid(dto.oid);
                track2.setIot(dto.iot);
                track2.setIid(dto.iid);
                track2.setIof(dto.iof);
                track2.setRid(dto.rid);
                track2.setRt(dto.rt);
                track2.setRf(dto.rf);
                track2.setAid(dto.aid);
                track2.setExs(dto.exs);
                track2.setIos(dto.ios);
                track2.setAd0(dto.ad0);
                track2.setAd1(dto.ad1);
                track2.setNet(dto.net);
                track2.setSat(dto.sat);
                device.setTrack(track2);
            }
        }

    }
}
