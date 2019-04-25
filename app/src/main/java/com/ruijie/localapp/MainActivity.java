package com.ruijie.localapp;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {
    private Context mContext;
    private LocationManager mLocationManager;
    private LocationListenerImpl mLocationListenerImpl;
    private TextView longitudeTV;
    private TextView latitudeTV;
    private TextView deviceIdTV;
    private Intent serviceIntent;
    private Intent serviceIntentAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        longitudeTV = findViewById(R.id.longitude);
        latitudeTV = findViewById(R.id.latitude);
        deviceIdTV = findViewById(R.id.deviceIdTV);
        mContext=this;

        String deviceId = android.os.Build.SERIAL;
        deviceIdTV.setText(deviceId);

        initLocationManager(mContext);

        serviceIntent = new Intent();
        serviceIntent.setAction("com.ruijie.localapp.LocalService");
        //Android 5.0之后，隐式调用是除了设置setAction()外，还需要设置setPackage()
        serviceIntent.setPackage("com.ruijie.localapp");
        startService(serviceIntent);

        serviceIntentAuth = new Intent(this,AuthService.class);
        startService(serviceIntentAuth);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationManager!=null) {
            mLocationManager.removeUpdates(mLocationListenerImpl);
        }
    }

    public void getLocationClick(View v){
        getLocation();
    }

    public void startServiceClick(View v){
        startService(serviceIntent);
    }
    public void stopServiceClick(View v){
        stopService(serviceIntent);
    }
    public void locationMgrClick(View v){
        Intent intent = new Intent();
        intent.setClass(this, LocationActivity.class);
        startActivity(intent);
    }

    private void initLocationManager(Context context){

        mLocationManager=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);


        if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "GPS模块正常", Toast.LENGTH_SHORT).show();
            //return;
        }



        //获取可用的位置信息Provider.即passive,network,gps中的一个或几个
        List<String> providerList=mLocationManager.getProviders(true);
        for (Iterator<String> iterator = providerList.iterator(); iterator.hasNext();) {
            String provider = (String) iterator.next();
            System.out.println("provider="+provider);
        }


        //在此采用GPS的方式获取位置信息
        Location location=mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location!=null) {
            double longitude=location.getLongitude();
            double altitude=location.getAltitude();
            longitudeTV.setText(longitude+"");
            latitudeTV.setText(altitude+"");
        } else {
            System.out.println("location==null");
        }
        //注册位置监听
        mLocationListenerImpl=new LocationListenerImpl();
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, mLocationListenerImpl);
    }

    private class LocationListenerImpl implements LocationListener{
        //当设备位置发生变化时调用该方法
        @Override
        public void onLocationChanged(Location location) {
            if (location!=null) {
                showLocation(location);
            }
        }

        //当provider的状态发生变化时调用该方法.比如GPS从可用变为不可用.
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        //当provider被打开的瞬间调用该方法.比如用户打开GPS
        @Override
        public void onProviderEnabled(String provider) {

        }

        //当provider被关闭的瞬间调用该方法.比如关闭打开GPS
        @Override
        public void onProviderDisabled(String provider) {

        }

    }


    private void showLocation(Location location) {
        // 获取经度
        double longitude = location.getLongitude();
        // 获取纬度
        double altitude = location.getAltitude();
        longitudeTV.setText(longitude+"");
        latitudeTV.setText(altitude+"");
    }

    public void getLocation(){
        Location location=mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location!=null) {
            double longitude=location.getLongitude();
            double altitude=location.getAltitude();
            longitudeTV.setText(longitude+"");
            latitudeTV.setText(altitude+"");
            Log.e("local","set local "+longitude+" "+altitude);
        } else {
            System.out.println("location==null");
        }
    }






}