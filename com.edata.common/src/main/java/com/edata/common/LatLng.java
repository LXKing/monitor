package com.edata.common;

public class LatLng {
	public double lat;
	public double lng;

	public static LatLng of(double lat, double lng) {
		LatLng o = new LatLng();
		o.lat = lat;
		o.lng = lng;
		return o;
	}

	@Override
	public String toString() {
		return String.format("{\"lat\":%s,\"lng\":%s}", lat, lng);
	}
}
