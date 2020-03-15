package com.example.fs.entity;

/**
 * Class for holding a lat/lng geopoint
 */
public class GeoCodeGeoPt implements FoursquareEntity {

	private static final long serialVersionUID = -4137525658469489516L;
	
	public Double lat;
	public Double lon;
	

	public GeoCodeGeoPt() {}
	
	public GeoCodeGeoPt(Double lat, Double lon) {
		this.lat = lat;
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}
	
	
	
}
