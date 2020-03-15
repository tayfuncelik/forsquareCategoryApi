package com.example.fs.entity;

/**
 * holds geometry of area
 */
public class GeoCodeGeometry implements FoursquareEntity {

	private static final long serialVersionUID = -2738451613022734596L;

	GeoCodeGeoPt center;

	GeoCodeBounds bounds;

	public GeoCodeGeometry(GeoCodeGeoPt center, GeoCodeBounds bounds) {
		super();
		this.center = center;
		this.bounds = bounds;
	}

	public GeoCodeGeometry() {
		super();
	}

	public GeoCodeGeoPt getCenter() {
		return center;
	}

	public void setCenter(GeoCodeGeoPt center) {
		this.center = center;
	}

	public GeoCodeBounds getBounds() {
		return bounds;
	}

	public void setBounds(GeoCodeBounds bounds) {
		this.bounds = bounds;
	}
	
	
	
	
}
