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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        longitudeTV = findViewById(R.id.longitude);
        latitudeTV = findViewById(R.id.latitude);
        mContext=this;

        initLocationManager(mContext);


        rmNetworkProvider();
        setNewNetworkProvider();

        MyThread myThread = new MyThread();
        myThread.start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationManager!=null) {
            mLocationManager.removeUpdates(mLocationListenerImpl);
        }
    }

    class MyThread extends Thread{
        @Override
        public void run(){
            while(true){
                try {
                    Thread.sleep(100);
                    setNetworkLocation();
                }catch (Exception e){
                    Log.e("test","e="+e);
                }

            }
        };
    }

    public void getLocationClick(View v){
        getLocation();
    }
    public void setLocationClick(View v){
        setNetworkLocation();
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
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, mLocationListenerImpl);
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
        } else {
            System.out.println("location==null");
        }
    }


    private void rmNetworkProvider(){
        try {
            String providerStr = LocationManager.GPS_PROVIDER;
            if (mLocationManager.isProviderEnabled(providerStr)){
                Log.d("test", "now remove NetworkProvider");
//                locationManager.setTestProviderEnabled(providerStr,true);
                mLocationManager.removeTestProvider(providerStr);

                //获取可用的位置信息Provider.即passive,network,gps中的一个或几个
                List<String> providerList=mLocationManager.getProviders(true);
                for (Iterator<String> iterator = providerList.iterator(); iterator.hasNext();) {
                    String provider = (String) iterator.next();
                    Log.d("test","provider="+provider);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("test", "rmNetworkProvider error");
        }
    }
    private void setNewNetworkProvider(){
        String providerStr = LocationManager.GPS_PROVIDER;
        try {
            mLocationManager.addTestProvider(providerStr, false, false,
                    false, false, false, false,
                    false, 1, Criteria.ACCURACY_FINE);
            Log.d("test","addTestProvider[network] success");
//            locationManager.setTestProviderStatus("network", LocationProvider.AVAILABLE, null,
//                    System.currentTimeMillis());
        }catch (SecurityException e){
            Log.e("test","setNewNetworkProvider error "+e);
        }
        if (!mLocationManager.isProviderEnabled(providerStr)){
            Log.d("test", "now  setTestProviderEnabled[network]");
            mLocationManager.setTestProviderEnabled(providerStr,true);
        }
    }

    Random random =new Random(10);
    int i = 0;
    private void setNetworkLocation() {
        //百度地图：26.0230909289,119.3518455973
        //腾讯高德：26.0173409510,119.3453031778
        //default location 30.5437233 104.0610342 成都长虹科技大厦


        int r1 = random.nextInt(999999999);
        int r2 = random.nextInt(999999999);
        double a1 = r1/100000000000000.0;
        double a2 = r2/100000000000000.0;
        double lat = 26.02030  + a1+ 0.000001*i;//+ 0.000001
        double lng = 119.34030 + a2+ 0.000001*i;
        Log.e("test","local"+lat+" "+lng);
        i++;
        String providerStr = LocationManager.GPS_PROVIDER;
        try {
            mLocationManager.setTestProviderLocation(providerStr, generateLocation(lat,lng));
        } catch (Exception e) {
            Log.d("test", "setNetworkLocation error");
            e.printStackTrace();
        }
    }
    //generate a location
    public Location generateLocation(double lat ,double lng) {
        Location loc = new Location("gps");
        loc.setAccuracy(2.0F);
        loc.setAltitude(lat);
        loc.setBearing(1.0F);
        Bundle bundle = new Bundle();
        bundle.putInt("satellites", 7);
        loc.setExtras(bundle);

        loc.setLatitude(lat);
        loc.setLongitude(lng);

        loc.setTime(System.currentTimeMillis());
        loc.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());

//        if (Build.VERSION.SDK_INT >= 17) {
//            loc.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
//        }
        return loc;
    }

}