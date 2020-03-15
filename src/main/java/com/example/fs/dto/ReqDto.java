package com.example.fs.dto;

public class ReqDto {
    private String near;
    private String ll;
    private String v;
    private String query;

    public String getNear() {
        return near;
    }

    public void setNear(String near) {
        this.near = near;
    }

    public String getLl() {
        return ll;
    }

    public void setLl(String ll) {
        this.ll = ll;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
