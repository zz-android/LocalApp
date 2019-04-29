package com.ruijie.localapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ruijie.localapp.dialog.FloatingImageDisplayService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LocationActivity extends Activity {
    private Context mContext;

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
        locationBeanList.add(new LocationBean(119.34579,26.04472,"花海陶"));
        locationBeanList.add(new LocationBean(119.34290,26.02215,"吉若1"));
        locationBeanList.add(new LocationBean(119.34405,26.02265,"吉若2"));
        locationBeanList.add(new LocationBean(119.29542,26.08603,"三方"));
        locationBeanList.add(new LocationBean(119.29440,26.08523,"三方陶1"));
        locationBeanList.add(new LocationBean(119.29010,26.08740,"三方陶2"));
        locationBeanList.add(new LocationBean(119.26404,26.04180,"仓山万达"));
        locationBeanList.add(new LocationBean(119.30550,26.03733,"师大"));
        locationBeanList.add(new LocationBean(119.30270,26.03283,"师大2"));
        locationBeanList.add(new LocationBean(0.0,0.0,"0 0 停止"));
        locationBeanList.add(new LocationBean(119.24866,26.09100,"映辉楼"));

        locationBeanList.add(new LocationBean(117.020438,25.05950,"龙岩"));
        locationBeanList.add(new LocationBean(117.64486,26.27405,"三明"));
        locationBeanList.add(new LocationBean(118.18219,26.64074,"南平"));
        locationBeanList.add(new LocationBean(117.72303,24.46459,"漳州"));
        locationBeanList.add(new LocationBean(119.58540,26.67444,"宁德"));
        locationBeanList.add(new LocationBean(119.12509,25.32325,"莆田"));

        locationBeanList.add(new LocationBean(120.14879,30.25769,"杭州"));
        locationBeanList.add(new LocationBean(116.34967,39.95718,"北京"));
        locationBeanList.add(new LocationBean(106.48772,29.53881,"重庆"));
        locationBeanList.add(new LocationBean(126.69108,45.76292,"哈尔滨"));
        locationBeanList.add(new LocationBean(108.97154,34.29408,"西安"));
        //locationBeanList.add(new LocationBean(117.020438,25.05950,"龙岩-"));


        locationBeanAdapter.notifyDataSetChanged();

    }

    public void startFloatingDisplayClick(View view) {

//        if (!Settings.canDrawOverlays(this)) {
//            Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT);
//            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 1);
//        } else {
//            startService(new Intent(MainActivity.this, FloatingImageDisplayService.class));
//        }
        if (FloatingImageDisplayService.isStarted) {
            return;
        }
        if(LocationBean.staticLongitude == 0 && LocationBean.staticAltitude == 0){
            LocationBean.staticLongitude = 119.29542;
            LocationBean.staticAltitude = 26.08603;
        }
        startService(new Intent(mContext, FloatingImageDisplayService.class));
    }
}