package com.edata.monitor.domain.statistics;

import java.util.Date;

public class HistoryOnlineOfflineCount {
	private String motorcadeId;

	public String getMotorcadeId() {
		return motorcadeId;
	}

	public void setMotorcadeId(String motorcadeId) {
		this.motorcadeId = motorcadeId;
	}

	private String motorcade;

	public String getMotorcade() {
		return motorcade;
	}

	public void setMotorcade(String motorcade) {
		this.motorcade = motorcade;
	}

	private Date start;

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	private Date end;

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	private int total;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	private int online;

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

	private int onlineRate;

	public int getOnlineRate() {
		return onlineRate;
	}

	public void setOnlineRate(int onlineRate) {
		this.onlineRate = onlineRate;
	}

	private int offline;

	public int getOffline() {
		return offline;
	}

	public void setOffline(int offline) {
		this.offline = offline;
	}

	private int offlineRate = 100;

	public int getOfflineRate() {
		return offlineRate;
	}

	public void setOfflineRate(int offlineRate) {
		this.offlineRate = offlineRate;
	}

}
