package com.edata.monitor.domain.statistics;

public class CurrentOnlineOfflineCount {
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
