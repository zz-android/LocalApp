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

        locationBeanList.add(new LocationBean(119.1851,26.0545,"福公寓43",3,true));
        locationBeanList.add(new LocationBean(119.1857,26.0537,"福公寓41",2,true));
        locationBeanList.add(new LocationBean(119.1876,26.0539,"福公寓31",2,true));
        locationBeanList.add(new LocationBean(119.1884,26.0549,"福公寓25",1,true));
        locationBeanList.add(new LocationBean(119.1898,26.0547,"福操场西北",3,true));
        locationBeanList.add(new LocationBean(119.1901,26.0534,"福操场西南",2,true));
        locationBeanList.add(new LocationBean(119.1914,26.0532,"福操场东南",1,true));
        locationBeanList.add(new LocationBean(119.1915,26.0547,"福操场东北",1,true));
        locationBeanList.add(new LocationBean(119.1930,26.0562,"福行政南",1,true));
        locationBeanList.add(new LocationBean(119.1933,26.0582,"福行政北",2,true));
        locationBeanList.add(new LocationBean(119.1928,26.0591,"福行政2",1,true));
        locationBeanList.add(new LocationBean(119.1923,26.0577,"福行政西北",1,true));
        locationBeanList.add(new LocationBean(119.1916,26.0569,"福行政西",1,true));
        locationBeanList.add(new LocationBean(119.1884,26.0568,"福公寓24",1,true));
        locationBeanList.add(new LocationBean(119.1882,26.0576,"福公寓19",1,true));
        locationBeanList.add(new LocationBean(119.1881,26.0597,"福京元餐厅",2,true));
        locationBeanList.add(new LocationBean(119.1866,26.0609,"福公寓4",1,true));
        //locationBeanList.add(new LocationBean(119.1843,26.0601,"福公寓57",3,true));
        locationBeanList.add(new LocationBean(119.1850,26.0619,"福阿庆",1,true));
        locationBeanList.add(new LocationBean(119.1850,26.0636,"江公寓23",2,true));
        locationBeanList.add(new LocationBean(119.1829,26.0648,"江公寓11",1,true));
        locationBeanList.add(new LocationBean(119.1863,26.0635,"江嘿店",2,true));
        locationBeanList.add(new LocationBean(119.1879,26.0634,"福嘿店对面",3,true));
        locationBeanList.add(new LocationBean(119.1876,26.0646,"福嘿店北",1,true));
        locationBeanList.add(new LocationBean(119.1890,26.0648,"福建筑西2",1,true));
        locationBeanList.add(new LocationBean(119.1904,26.0648,"福建筑西1",1,true));
        locationBeanList.add(new LocationBean(119.1897,26.0664,"江教学4东南",1,true));
        locationBeanList.add(new LocationBean(119.1905,26.0675,"江教学4东",1,true));
        locationBeanList.add(new LocationBean(119.1887,26.0671,"江教学4西南",1,true));
        locationBeanList.add(new LocationBean(119.1857,26.0690,"江公管",1,true));
        locationBeanList.add(new LocationBean(119.1869,26.0704,"江长安路",1,true));
        locationBeanList.add(new LocationBean(119.1862,26.0713,"医药学院",1,true));
        locationBeanList.add(new LocationBean(119.1875,26.0722,"江东南门南",1,true));
        locationBeanList.add(new LocationBean(119.1866,26.0728,"江东南门西",1,true));
//        locationBeanList.add(new LocationBean(119.1827,26.0661,"江天天烤鱼1",3,true));
//        locationBeanList.add(new LocationBean(119.1812,26.0665,"医天天烤鱼2",3,true));
//        locationBeanList.add(new LocationBean(119.1792,26.0677,"医文印广告",3,true));
//        locationBeanList.add(new LocationBean(119.1779,26.0696,"医文印北",3,true));
//        locationBeanList.add(new LocationBean(119.1783,26.0700,"医文印北2",3,true));
        locationBeanList.add(new LocationBean(119.34290,26.02215,"吉若",3));
        locationBeanList.add(new LocationBean(119.3513,26.0425,"*花海1",2));
        locationBeanList.add(new LocationBean(119.34389,26.04670,"*花海2",2));
        locationBeanList.add(new LocationBean(119.30550,26.03733,"*师大",1));
        locationBeanList.add(new LocationBean(119.3011,26.03227,"*师大3",3));
        locationBeanList.add(new LocationBean(119.4183,25.9944,"慈航",1));
        locationBeanList.add(new LocationBean(119.2939,26.1530,"森林1",1));
        locationBeanList.add(new LocationBean(119.2885,26.1521,"森林2",3));
        locationBeanList.add(new LocationBean(119.3484,26.0394,"*观音"));
        locationBeanList.add(new LocationBean(119.3516,26.0355,"*闽江世纪"));
        locationBeanList.add(new LocationBean(119.3542,26.0408,"*蔚蓝"));
        locationBeanList.add(new LocationBean(119.3567,26.0310,"*会展"));
        locationBeanList.add(new LocationBean(119.3647,26.0263,"*规划"));
        locationBeanList.add(new LocationBean(119.2957,26.1536,"*森林公园"));
        locationBeanList.add(new LocationBean(119.3562,25.9802,"*书院"));
        locationBeanList.add(new LocationBean(119.4689,25.9586,"*营前工业区"));
        locationBeanList.add(new LocationBean(119.4186,25.9477,"*下洋"));
        locationBeanList.add(new LocationBean(119.4133,25.9460,"*下洋2"));


        locationBeanList.add(new LocationBean(119.3431,26.0461,"花海2-2",2));
        locationBeanList.add(new LocationBean(119.34579,26.04472,"花海3",3));


        locationBeanList.add(new LocationBean(119.2945,26.0847,"三方1",3));
        locationBeanList.add(new LocationBean(119.29010,26.08740,"三方2",2));
        locationBeanList.add(new LocationBean(119.2904,26.0861,"三方3",1));
        locationBeanList.add(new LocationBean(119.2945,26.0868,"三方4",2));
        locationBeanList.add(new LocationBean(119.2890,26.0857,"三方5",3));

        locationBeanList.add(new LocationBean(119.3015,26.0814,"于山",1));
        locationBeanList.add(new LocationBean(119.3029,26.0785,"五一",1));
        locationBeanList.add(new LocationBean(119.3031,26.0779,"五一2",1));
        locationBeanList.add(new LocationBean(119.3042,26.0779,"五一3",1));






        locationBeanList.add(new LocationBean(119.2399,26.0859,"农大1",1));




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





        locationBeanList.add(new LocationBean(120.14879,30.25769,"杭州"));
        locationBeanList.add(new LocationBean(116.34967,39.95718,"北京"));
        locationBeanList.add(new LocationBean(106.48772,29.53881,"重庆"));
        locationBeanList.add(new LocationBean(126.69108,45.76292,"哈尔滨"));
        locationBeanList.add(new LocationBean(108.97154,34.29408,"西安"));

        locationBeanList.add(new LocationBean(119.2821,26.0892,"湖头"));
        locationBeanList.add(new LocationBean(119.2959,26.0894,"coco1"));
        locationBeanList.add(new LocationBean(119.2912,26.0879,"coco2"));
        locationBeanList.add(new LocationBean(119.2996,26.0904,"井大"));

        locationBeanList.add(new LocationBean(119.26404,26.04180,"仓山万达"));
        locationBeanList.add(new LocationBean(119.3068,26.1126,"电建二公司"));
        locationBeanList.add(new LocationBean(119.3196,26.1434,"五四北泰和"));


        locationBeanAdapter.notifyDataSetChanged();
        if (!FloatingImageDisplayService.isStarted) {
            startService(new Intent(mContext, FloatingImageDisplayService.class));
        }

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