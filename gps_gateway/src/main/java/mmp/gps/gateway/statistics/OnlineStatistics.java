package mmp.gps.gateway.statistics;


import mmp.gps.domain.statistics.DeviceOnlineOfflineReport;
import mmp.gps.gateway.domain.DataSender;
import org.jboss.netty.util.internal.ConcurrentHashMap;

import java.util.Date;
import java.util.Map;

public class OnlineStatistics {

    private static Map onoff = new ConcurrentHashMap();


    public static DeviceOnlineOffline get(Object number) {
        DeviceOnlineOffline device = (DeviceOnlineOffline) onoff.getOrDefault(number, (Object) null);
        if (device == null) {
            device = new DeviceOnlineOffline();
            device.setNumber(number.toString());
            onoff.put(number, device);
        }

        return device;
    }

    public static void onlineCount(Object number) {
        Date now = new Date();
        DeviceOnlineOffline device = get(number);
        if (device.isOn()) {
            device.setOn(false);
            device.setOffline(now);
            Date onlineTime = device.getOnline();
            int onlineSeconds = 0;
            if (onlineTime != null) {
                onlineSeconds = (int) ((now.getTime() - onlineTime.getTime()) / 1000L);
            }

            DeviceOnlineOfflineReport report = new DeviceOnlineOfflineReport();
            report.number = device.getNumber();
            report.onTime = onlineSeconds;
            report.time = now;
            report.on = false;
            DataSender.getCurrent().send(report);
        }
    }

    public static void offlineCount(Object number) {
        Date now = new Date();
        DeviceOnlineOffline device = get(number);
        if (!device.isOn()) {
            device.setOn(true);
            device.setOnline(now);
            Date offlineTime = device.getOffline();
            int offlineSeconds = 0;
            if (offlineTime != null) {
                offlineSeconds = (int) ((now.getTime() - offlineTime.getTime()) / 1000L);
            }

            DeviceOnlineOfflineReport report = new DeviceOnlineOfflineReport();
            report.number = device.getNumber();
            report.offTime = offlineSeconds;
            report.time = now;
            report.on = true;
            DataSender.getCurrent().send(report);
        }
    }
}
