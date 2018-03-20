package com.edata.monitor.domain.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jboss.netty.util.internal.ConcurrentHashMap;

import com.edata.monitor.service.InstructService;

public class DeviceInstructSender {
	private InstructService instructDao = new InstructService();

	private String deviceNumber;

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	private Date sent;

	public Date getSent() {
		return sent;
	}

	public void setSent(Date sent) {
		this.sent = sent;
	}

	private Map<String, DeviceInstruct> instructs = new ConcurrentHashMap<String, DeviceInstruct>();

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
			instructDao.send(top.getSerialNumber(), top.getDeviceNumber(), top.getUnid(), top.getUser(),top.getCommand(), top.getName(), top.getParams());
		} catch (Exception e) {
			e.printStackTrace();
		}

		sent = new Date();
	}

	private DeviceInstruct top() {
		// 这里将map.entrySet()转换成list
		List<Map.Entry<String, DeviceInstruct>> list = new ArrayList<Map.Entry<String, DeviceInstruct>>(instructs.entrySet());
		// 然后通过比较器来实现排序
		Collections.sort(list, new Comparator<Map.Entry<String, DeviceInstruct>>() {
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
