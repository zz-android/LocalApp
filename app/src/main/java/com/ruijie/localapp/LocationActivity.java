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
import android.widget.Button;
import android.widget.EditText;
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
    private EditText speedET,tagET;
    private Button setBtn;
    private ListView locationBeanListView;
    private LocationBeanAdapter locationBeanAdapter;
    public static List<LocationBean> locationBeanList;
    public static Integer TAG = 0;
    public static int nowlocation = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mContext =this;

        speedET = findViewById(R.id.speedET);
        tagET = findViewById(R.id.tagET);
        tagET.setText(TAG+"");
        setBtn = findViewById(R.id.setBtn);
        speedET.setText(LocationBean.UPDATE_FREQ+"");
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int speed = Integer.parseInt(speedET.getText()+"");
                LocationBean.UPDATE_FREQ = speed;

                TAG = Integer.parseInt(tagET.getText()+"");

            }
        });



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
                nowlocation = position;

            }
        });

        locationBeanList.add(new LocationBean(119.26404,26.04180,"仓山万达"));
        locationBeanList.add(new LocationBean(119.3068,26.1126,"电建二公司"));
        locationBeanList.add(new LocationBean(119.3196,26.1434,"五四北泰和"));

        locationBeanList.add(new LocationBean(119.34290,26.02215,"吉若",3));
        locationBeanList.add(new LocationBean(119.3513,26.0425,"*花海1",2));
        locationBeanList.add(new LocationBean(119.34389,26.04670,"*花海2",2));
        locationBeanList.add(new LocationBean(119.3431,26.0461,"花海2-2",2));
        locationBeanList.add(new LocationBean(119.34579,26.04472,"花海3",3));

        locationBeanList.add(new LocationBean(119.1884,26.0568,"福大1",1));
        locationBeanList.add(new LocationBean(119.1882,26.0576,"福大2",1));
        locationBeanList.add(new LocationBean(119.1881,26.0597,"福大3",1));
        locationBeanList.add(new LocationBean(119.1868,26.0634,"福大4",2));
        locationBeanList.add(new LocationBean(119.1867,26.0643,"福大5",2));
        locationBeanList.add(new LocationBean(119.1861,26.0659,"江1",3));
        locationBeanList.add(new LocationBean(119.1864,26.0665,"江2",3));
        locationBeanList.add(new LocationBean(119.1822,26.0689,"医科1",1));
        locationBeanList.add(new LocationBean(119.1814,26.0703,"医科2",2));


        locationBeanList.add(new LocationBean(119.2945,26.0847,"三方1",3));
        locationBeanList.add(new LocationBean(119.29010,26.08740,"三方2",2));
        locationBeanList.add(new LocationBean(119.2904,26.0861,"三方3",1));
        locationBeanList.add(new LocationBean(119.2945,26.0868,"三方4",2));
        locationBeanList.add(new LocationBean(119.2890,26.0857,"三方5",3));

        locationBeanList.add(new LocationBean(119.3015,26.0814,"于山",1));
        locationBeanList.add(new LocationBean(119.3029,26.0785,"五一",1));
        locationBeanList.add(new LocationBean(119.3031,26.0779,"五一2",1));
        locationBeanList.add(new LocationBean(119.3042,26.0779,"五一3",1));

        locationBeanList.add(new LocationBean(119.30550,26.03733,"*师大",1));
        locationBeanList.add(new LocationBean(119.30270,26.03283,"师大2",2));
        locationBeanList.add(new LocationBean(119.3011,26.03227,"*师大3",3));
        locationBeanList.add(new LocationBean(119.3031,26.0340,"师大4",3));
        locationBeanList.add(new LocationBean(119.3033,26.0348,"师大5",3));
        locationBeanList.add(new LocationBean(119.3021,26.0349,"师大6",3));



        locationBeanList.add(new LocationBean(119.2399,26.0859,"农大1",1));
        locationBeanList.add(new LocationBean(119.2418,26.0847,"生物",2));
        locationBeanList.add(new LocationBean(119.2398,26.0934,"经管",1));
        locationBeanList.add(new LocationBean(119.2386,26.0934,"行政",2));
        locationBeanList.add(new LocationBean(119.2400,26.0892,"农大2",3));
        locationBeanList.add(new LocationBean(119.2374,26.0908,"农大3",1));
        locationBeanList.add(new LocationBean(119.2319,26.0850,"农大4",1));
        locationBeanList.add(new LocationBean(119.2311,26.0842,"农大5",3));
        locationBeanList.add(new LocationBean(119.2297,26.0842,"农大6",3));
        locationBeanList.add(new LocationBean(119.2277,26.0876,"农大7",3));
        locationBeanList.add(new LocationBean(119.2277,26.0888,"农大8",1));
        locationBeanList.add(new LocationBean(119.4183,25.9944,"慈航",1));
        locationBeanList.add(new LocationBean(119.2939,26.1530,"森林1",1));
        locationBeanList.add(new LocationBean(119.2885,26.1521,"森林2",3));


        locationBeanList.add(new LocationBean(119.29410,26.08842,"东百",4));
        locationBeanList.add(new LocationBean(119.2910,26.0885,"冰心",4));
        locationBeanList.add(new LocationBean(119.2962,26.0808,"冠亚",4));
        locationBeanList.add(new LocationBean(119.3000,26.0832,"榕城酒店",4));
        locationBeanList.add(new LocationBean(119.3023,26.0803,"于山",4));
        locationBeanList.add(new LocationBean(119.3031,26.0771,"剧院",4));
        locationBeanList.add(new LocationBean(119.3073,26.0771,"先施",4));
        locationBeanList.add(new LocationBean(119.3102,26.0776,"闽都",4));
        locationBeanList.add(new LocationBean(119.3113,26.0747,"海潮",4));
        locationBeanList.add(new LocationBean(119.3080,26.0738,"龙庭",4));



        locationBeanList.add(new LocationBean(117.020438,25.05950,"龙岩"));
        locationBeanList.add(new LocationBean(117.64486,26.27405,"三明"));
        locationBeanList.add(new LocationBean(118.18219,26.64074,"南平"));
        locationBeanList.add(new LocationBean(117.72303,24.46459,"漳州"));
        locationBeanList.add(new LocationBean(119.58540,26.67444,"宁德"));
        locationBeanList.add(new LocationBean(119.12509,25.32325,"莆田"));

        
        locationBeanList.add(new LocationBean(119.3647,26.0263,"*规划"));
        locationBeanList.add(new LocationBean(119.2957,26.1536,"*森林公园"));
        locationBeanList.add(new LocationBean(119.3547,25.9800,"*书院"));
        locationBeanList.add(new LocationBean(119.4689,25.9586,"*营前工业区"));
        locationBeanList.add(new LocationBean(119.4186,25.9477,"*下洋"));
        locationBeanList.add(new LocationBean(119.4133,25.9460,"*下洋2"));


        locationBeanList.add(new LocationBean(120.14879,30.25769,"杭州"));
        locationBeanList.add(new LocationBean(116.34967,39.95718,"北京"));
        locationBeanList.add(new LocationBean(106.48772,29.53881,"重庆"));
        locationBeanList.add(new LocationBean(126.69108,45.76292,"哈尔滨"));
        locationBeanList.add(new LocationBean(108.97154,34.29408,"西安"));

        locationBeanList.add(new LocationBean(119.2821,26.0892,"湖头"));
        locationBeanList.add(new LocationBean(119.2959,26.0894,"coco1"));
        locationBeanList.add(new LocationBean(119.2912,26.0879,"coco2"));
        locationBeanList.add(new LocationBean(119.2996,26.0904,"井大"));


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