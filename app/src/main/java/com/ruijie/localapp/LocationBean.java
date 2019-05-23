package com.ruijie.localapp;

import java.util.Random;

public class LocationBean {
    public static Integer UPDATE_FREQ = 350;
    public static Double MOVE_STEP = 0.00001;

    public static Double staticLongitude = 0.0;
    public static Double staticAltitude = 0.0;
    public static boolean MOVEING = true;

    private Double longitude;
    private Double altitude;
    private String remark;
    private Integer tag = 0;
    private boolean moveGo = false;

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
        this.tag = 0;
    }
    public LocationBean(Double longitude, Double altitude, String remark,Integer tag) {
        this.longitude = longitude;
        this.altitude = altitude;
        this.remark = remark;
        this.tag = tag;
    }
    public LocationBean(Double longitude, Double altitude, String remark,Integer tag,boolean moveGo) {
        this.longitude = longitude;
        this.altitude = altitude;
        this.remark = remark;
        this.tag = tag;
        this.moveGo = moveGo;
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

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    private Random random =new Random();
    private double getRandom(){
        int r1 = random.nextInt(999999999);
        double a1 = r1/100000000000000.0;
        return a1;
    }

    public boolean getMoveGo() {
        return moveGo;
    }

    public void setMoveGo(boolean moveGo) {
        this.moveGo = moveGo;
    }
}