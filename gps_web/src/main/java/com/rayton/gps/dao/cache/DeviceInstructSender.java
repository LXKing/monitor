package com.rayton.gps.dao.cache;

import com.rayton.gps.service.InstructService;
import org.jboss.netty.util.internal.ConcurrentHashMap;

import java.util.*;
import java.util.Map.Entry;

public class DeviceInstructSender {
    private InstructService instructDao = new InstructService();

    private String deviceNumber;
    private Date sent;
    private Map<String, DeviceInstruct> instructs = new ConcurrentHashMap<String, DeviceInstruct>();

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }

    public Map<String, DeviceInstruct> getInstructs() {
        return instructs;
    }

    public void sendNext() {
        long now = new Date().getTime();
        long last = sent.getTime();
        long seconds = (now - last) / 1000;
        if (seconds < 10)
            return;

        DeviceInstruct top = top();
        if (top == null)
            return;
        try {
            instructDao.send(top.getSerialNumber(), top.getDeviceNumber(), top.getUnid(), top.getUser(), top.getCommand(), top
                    .getName(), top.getParams());
        } catch (Exception e) {
            e.printStackTrace();
        }

        sent = new Date();
    }

    private DeviceInstruct top() {
        // 这里将map.entrySet()转换成list
        List<Entry<String, DeviceInstruct>> list = new ArrayList<Entry<String, DeviceInstruct>>(instructs.entrySet());
        // 然后通过比较器来实现排序
        Collections.sort(list, new Comparator<Entry<String, DeviceInstruct>>() {
            // 升序排序
            public int compare(Entry<String, DeviceInstruct> o1, Entry<String, DeviceInstruct> o2) {
                return o1.getValue().getSendTime().compareTo(o2.getValue().getSendTime());
            }

        });

        if (list.size() > 0)
            return list.get(0).getValue();
        return null;
    }
}
