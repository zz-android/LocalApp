package com.ruijie.localapp;

public class LocationBean {

    private Double longitude;
    private Double altitude;

    public LocationBean() {
    }

    public LocationBean(Double longitude, Double altitude) {
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }
}
