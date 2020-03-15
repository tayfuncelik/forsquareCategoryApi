package com.example.fs.entity;

/**
 * Base class for all "count" entities
 */
public class Count implements FoursquareEntity {

    private static final long serialVersionUID = -471761138324979612L;

    public Long getCount() {
        return count;
    }

    protected Long count;

    @Override
    public String toString() {
        return "Count{" +
                "count=" + count +
                '}';
    }
}
