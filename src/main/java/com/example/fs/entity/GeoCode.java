package com.example.fs.entity;

public class GeoCode implements FoursquareEntity {

	private static final long serialVersionUID = 2673015081507846654L;
	
	GeoCodeFeature feature;

	public GeoCode() {
		super();
	}
	

	public GeoCode(GeoCodeFeature feature) {
		super();
		this.feature = feature;
	}

	public GeoCodeFeature getFeature() {
		return feature;
	}

	public void setFeature(GeoCodeFeature feature) {
		this.feature = feature;
	}

	
	
	
}
