package com.example.fs.dto;

import com.example.fs.entity.CompactVenue;
import com.example.fs.entity.FoursquareEntity;
import com.example.fs.entity.GeoCode;
import com.example.fs.entity.VenueGroup;

public class VenuesSearchResult implements FoursquareEntity {

    private static final long serialVersionUID = -4811474739114637413L;

    private CompactVenue[] venues;
    private VenueGroup[] groups;
    private GeoCode geocode;


    public VenuesSearchResult(CompactVenue[] venues, VenueGroup[] groups) {
        this.venues = venues;
        this.groups = groups;
    }

    public VenuesSearchResult(CompactVenue[] venues, VenueGroup[] groups, GeoCode geocode) {
        this(venues, groups);
        this.geocode = geocode;
    }

    public CompactVenue[] getVenues() {
        return venues;
    }

    public VenueGroup[] getGroups() {
        return groups;
    }

    public GeoCode getGeocode() {
        return geocode;
    }

    public void setGeocode(GeoCode geocode) {
        this.geocode = geocode;
    }

}
