package com.ruijie.localapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LocationActivity extends Activity {
    private Context mContext;
    private Random random =new Random();
    private ListView locationBeanListView;
    private LocationBeanAdapter locationBeanAdapter;
    private List<LocationBean> locationBeanList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mContext =this;

        locationBeanListView = (ListView) findViewById(R.id.locationBeanListView);

        locationBeanList = new ArrayList<LocationBean>();
        locationBeanAdapter = new LocationBeanAdapter(this,locationBeanList);
        locationBeanListView.setAdapter(locationBeanAdapter);
        locationBeanListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, locationBeanList.get(position).getRemark(), Toast.LENGTH_SHORT).show();
                LocationBean bean = locationBeanList.get(position);
                double longitude = bean.getLongitudeReal();
                double altitude = bean.getAltitudeReal();
                LocationBean.staticLongitude = longitude;
                LocationBean.staticAltitude = altitude;

            }
        });

        locationBeanList.add(new LocationBean(119.35139,26.04160,"花海雷"));
        locationBeanList.add(new LocationBean(119.34389,26.04670,"花海木"));
        locationBeanList.add(new LocationBean(119.34529,26.04472,"花海陶"));
        locationBeanList.add(new LocationBean(119.34290,26.02215,"吉若1"));
        locationBeanList.add(new LocationBean(119.34405,26.02265,"吉若2"));
        locationBeanList.add(new LocationBean(0.0,0.0,"0 0 停止"));
        locationBeanList.add(new LocationBean(119.24866,26.09100,"映辉楼"));
        locationBeanList.add(new LocationBean(117.020438,25.05950,"龙岩"));
        locationBeanList.add(new LocationBean(120.14879,30.25769,"杭州"));
        locationBeanList.add(new LocationBean(116.34967,39.95718,"北京"));
        locationBeanList.add(new LocationBean(106.48772,29.53881,"重庆"));
        locationBeanList.add(new LocationBean(126.69108,45.76292,"哈尔滨"));
        //locationBeanList.add(new LocationBean(117.020438,25.05950,"龙岩-"));


        locationBeanAdapter.notifyDataSetChanged();

    }

    private double getRandom(){
        int r1 = random.nextInt(999999999);
        double a1 = r1/100000000000000.0;
        return a1;
    }

    public void upClick(View v){
        LocationBean.staticAltitude = LocationBean.staticAltitude + 0.0004;
        Toast.makeText(this, "upClick", Toast.LENGTH_SHORT).show();

    }
    public void downClick(View v){
        LocationBean.staticAltitude = LocationBean.staticAltitude - 0.0004;
        Toast.makeText(this, "downClick", Toast.LENGTH_SHORT).show();

    }
    public void leftClick(View v){
        LocationBean.staticLongitude = LocationBean.staticLongitude - 0.0004;
        Toast.makeText(this, "leftClick", Toast.LENGTH_SHORT).show();

    }
    public void rightClick(View v){
        LocationBean.staticLongitude = LocationBean.staticLongitude + 0.0004;
        Toast.makeText(this, "rightClick", Toast.LENGTH_SHORT).show();
    }
}