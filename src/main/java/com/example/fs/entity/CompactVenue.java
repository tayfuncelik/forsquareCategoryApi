package com.example.fs.entity;

import java.util.Arrays;


public class CompactVenue implements FoursquareEntity {

    private static final long serialVersionUID = -7714811839778109046L;

    protected String id;
    protected String name;
    protected Category[] categories;
    protected Boolean verified;
    protected String url;
    protected Integer rating;
    protected Location location;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Category[] getCategories() {
        return categories;
    }

    public Boolean getVerified() {
        return verified;
    }

    public String getUrl() {
        return url;
    }

    public Integer getRating() {
        return rating;
    }


    @Override
    public String toString() {
        return "CompactVenue{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ",\nlocation=" + location +
                ",\ncategories=" + Arrays.toString(categories) +
                ",\nverified=" + verified +
                ",\nurl='" + url + '\'' +
                ",\nrating=" + rating +
                '}';
    }
}
