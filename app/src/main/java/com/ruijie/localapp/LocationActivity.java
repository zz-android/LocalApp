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

        locationBeanList.add(new LocationBean(119.26404,26.04180,"仓山万达"));
        locationBeanList.add(new LocationBean(119.34290,26.02215,"吉若"));
        locationBeanList.add(new LocationBean(119.2959,26.0894,"coco1"));
        locationBeanList.add(new LocationBean(119.2912,26.0879,"coco2"));

        locationBeanList.add(new LocationBean(119.35139,26.04160,"花海雷"));
        locationBeanList.add(new LocationBean(119.34389,26.04670,"花海木"));
        locationBeanList.add(new LocationBean(119.34579,26.04472,"花海陶"));


        locationBeanList.add(new LocationBean(119.29410,26.08842,"三方"));
        locationBeanList.add(new LocationBean(119.29440,26.08523,"三方陶1"));
        locationBeanList.add(new LocationBean(119.29010,26.08740,"三方陶2"));
        locationBeanList.add(new LocationBean(119.2904,26.0861,"三方3"));
        locationBeanList.add(new LocationBean(119.2949,26.0873,"三方4"));
        locationBeanList.add(new LocationBean(119.2890,26.0857,"三方5"));

        locationBeanList.add(new LocationBean(119.30550,26.03733,"师大"));
        locationBeanList.add(new LocationBean(119.30270,26.03283,"师大2"));
        locationBeanList.add(new LocationBean(119.3015,26.03223,"师大3"));
        locationBeanList.add(new LocationBean(119.3031,26.0340,"师大4"));
        locationBeanList.add(new LocationBean(119.3033,26.0348,"师大5"));
        locationBeanList.add(new LocationBean(119.3021,26.0349,"师大6"));

        locationBeanList.add(new LocationBean(119.2400,26.0847,"农大1"));
        locationBeanList.add(new LocationBean(119.2400,26.0892,"农大2"));
        locationBeanList.add(new LocationBean(119.2374,26.0905,"农大3"));
        locationBeanList.add(new LocationBean(119.2319,26.0850,"农大4"));
        locationBeanList.add(new LocationBean(119.2311,26.0842,"农大5"));
        locationBeanList.add(new LocationBean(119.2297,26.0842,"农大6"));
        locationBeanList.add(new LocationBean(119.2277,26.0876,"农大7"));
        locationBeanList.add(new LocationBean(119.2277,26.0888,"农大8"));
        locationBeanList.add(new LocationBean(119.2418,26.0847,"生物"));
        locationBeanList.add(new LocationBean(119.2403,26.0934,"经管"));
        locationBeanList.add(new LocationBean(119.2386,26.0934,"行政"));




        locationBeanList.add(new LocationBean(117.020438,25.05950,"龙岩"));
        locationBeanList.add(new LocationBean(117.64486,26.27405,"三明"));
        locationBeanList.add(new LocationBean(118.18219,26.64074,"南平"));
        locationBeanList.add(new LocationBean(117.72303,24.46459,"漳州"));
        locationBeanList.add(new LocationBean(119.58540,26.67444,"宁德"));
        locationBeanList.add(new LocationBean(119.12509,25.32325,"莆田"));

        locationBeanList.add(new LocationBean(119.34405,26.02265,"吉若2"));
        locationBeanList.add(new LocationBean(120.14879,30.25769,"杭州"));
        locationBeanList.add(new LocationBean(116.34967,39.95718,"北京"));
        locationBeanList.add(new LocationBean(106.48772,29.53881,"重庆"));
        locationBeanList.add(new LocationBean(126.69108,45.76292,"哈尔滨"));
        locationBeanList.add(new LocationBean(108.97154,34.29408,"西安"));
        locationBeanList.add(new LocationBean(88.8886,29.26349,"日喀则市"));
        locationBeanList.add(new LocationBean(84.8597,45.6005,"克拉玛依"));
        locationBeanList.add(new LocationBean(124.64642,51.9533,"大兴安"));
        locationBeanList.add(new LocationBean(105.9205,26.2490,"安顺"));
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
//        if(LocationBean.staticLongitude == 0 && LocationBean.staticAltitude == 0){
//            LocationBean.staticLongitude = 119.29542;
//            LocationBean.staticAltitude = 26.08603;
//        }
        startService(new Intent(mContext, FloatingImageDisplayService.class));
    }
}