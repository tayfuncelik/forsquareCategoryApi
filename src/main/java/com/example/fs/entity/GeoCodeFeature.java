package com.example.fs.entity;

/**
 * features returned from a geocode response
 */
public class GeoCodeFeature implements FoursquareEntity {

	private static final long serialVersionUID = 1L;

	String cc;
	GeoCodeGeometry geometry;

	String name;

	String displayName;

	Integer woeType;
	
	/**
	 * Attribution, seems to be a list of strings
	 */
	String[] attribution;

	public GeoCodeFeature(String cc, GeoCodeGeometry geometry, String name,
                          String displayName, Integer woeType, String[] attribution) {
		super();
		this.cc = cc;
		this.geometry = geometry;
		this.name = name;
		this.displayName = displayName;
		this.woeType = woeType;
		this.attribution = attribution;
	}

	public GeoCodeFeature() {
		super();
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public GeoCodeGeometry getGeometry() {
		return geometry;
	}

	public void setGeometry(GeoCodeGeometry geometry) {
		this.geometry = geometry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getWoeType() {
		return woeType;
	}

	public void setWoeType(Integer woeType) {
		this.woeType = woeType;
	}

	public String[] getAttribution() {
		return attribution;
	}

	public void setAttribution(String[] attribution) {
		this.attribution = attribution;
	}
	
	
	
}
