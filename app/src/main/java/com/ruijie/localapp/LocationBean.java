package com.ruijie.localapp;

import java.util.Random;

public class LocationBean {

    public static Double staticLongitude = 0.0;
    public static Double staticAltitude = 0.0;;

    private Double longitude;
    private Double altitude;
    private String remark;

    public LocationBean() {
    }

    public LocationBean(Double longitude, Double altitude) {
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public LocationBean(Double longitude, Double altitude, String remark) {
        this.longitude = longitude;
        this.altitude = altitude;
        this.remark = remark;
    }

    public Double getLongitudeReal() {
        return longitude;
    }

    public Double getAltitudeReal() {
        return altitude;
    }

    public Double getLongitude() {
        if(staticLongitude > 1){
            return staticLongitude+getRandom();
        }
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getAltitude() {
        if(staticAltitude > 1){
            return staticAltitude+getRandom();
        }
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private Random random =new Random();
    private double getRandom(){
        int r1 = random.nextInt(999999999);
        double a1 = r1/100000000000000.0;
        return a1;
    }
}