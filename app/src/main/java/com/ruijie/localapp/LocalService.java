package com.ruijie.localapp;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

//Android的四大组件，只有定义了，就必须去AndroidManifest.xml中注册一下！！！
public class LocalService extends Service {

    private final String TAG = "LocalService";
    private LocationManager mLocationManager;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable(){
        @Override
        public void run() {
            setNetworkLocation();

            handler.postDelayed(this, LocationBean.UPDATE_FREQ);// 50ms后执行this，即runable
        }
    };
    private Random random =new Random();

//    private static Integer UPDATE_FREQ = 350;
//    private static Double MOVE_STEP = 0.00001;
    private LocationBean locationBeanNow;//当前坐标
    private List<LocationBean> locationBeanList = new ArrayList<LocationBean>();//要移动的坐标
    private Integer gotoLocationTag;
    //必须实现的方法
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind方法被调用");
        return null;
    }

    //Service被创建时调用
    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate方法被调用");

//        locationBeanList.add(new LocationBean(119.35139,26.04360));//花海
//        locationBeanList.add(new LocationBean(119.35139,26.04160));//花海雷凌子
//        locationBeanList.add(new LocationBean(119.347830,26.04445));//花海中途
//        locationBeanList.add(new LocationBean(119.34309,26.04650));//花海2
        locationBeanList.add(new LocationBean(119.29410,26.08842));//三方
        locationBeanList.add(new LocationBean(119.29150,26.08582));//三方
        locationBeanList.add(new LocationBean(119.29480,26.08623));//三方
        locationBeanList.add(new LocationBean(119.29040,26.08472));//三方
        locationBeanList.add(new LocationBean(119.29040,26.08872));//三方


//        locationBeanList.add(new LocationBean(119.30270,26.03283));
//        LocationBean.staticLongitude = locationBeanList.get(0).getLongitude();
//        LocationBean.staticAltitude = locationBeanList.get(0).getAltitude();

        locationBeanNow = new LocationBean(locationBeanList.get(0).getLongitude(),locationBeanList.get(0).getAltitude());
        gotoLocationTag = 0;

        mLocationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        rmNetworkProvider();
        setNewNetworkProvider();
        super.onCreate();
    }

    //Service被启动时调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand方法被调用");
        handler.postDelayed(runnable, LocationBean.UPDATE_FREQ);// 打开定时器，执行runnable操作
        return super.onStartCommand(intent, flags, startId);
    }

    //Service被销毁时调用
    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy方法被调用");

//        if(localServiceThread != null) {
//            localServiceThread.interrupt();
//            localServiceThread = null;
//        }
        handler.removeCallbacks(runnable);
        rmNetworkProvider();
        super.onDestroy();
    }

    private void rmNetworkProvider(){
        try {
            String providerStr = LocationManager.GPS_PROVIDER;
            if (mLocationManager.isProviderEnabled(providerStr)){
                Log.d("test", "now remove NetworkProvider");
//                locationManager.setTestProviderEnabled(providerStr,true);
                mLocationManager.removeTestProvider(providerStr);

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




    private void setNetworkLocation() {
        int r1 = random.nextInt(999999999);
        int r2 = random.nextInt(999999999);
        double a1 = r1/100000000000000.0;
        double a2 = r2/100000000000000.0;

        LocationBean gotoLocation = locationBeanList.get(gotoLocationTag);

        double sin = getSin(gotoLocation.getLongitudeReal(),gotoLocation.getAltitudeReal()
        ,locationBeanNow.getLongitudeReal(),locationBeanNow.getAltitudeReal());
        double cos = getCos(gotoLocation.getLongitudeReal(),gotoLocation.getAltitudeReal()
                ,locationBeanNow.getLongitudeReal(),locationBeanNow.getAltitudeReal());

        if(gotoLocation.getLongitude() > locationBeanNow.getLongitude()){
            locationBeanNow.setLongitude(locationBeanNow.getLongitude() + LocationBean.MOVE_STEP*cos + a1);
        }else{
            locationBeanNow.setLongitude(locationBeanNow.getLongitude() - LocationBean.MOVE_STEP*cos - a1);
        }

        if(gotoLocation.getAltitude() > locationBeanNow.getAltitude()){
            locationBeanNow.setAltitude(locationBeanNow.getAltitude() + LocationBean.MOVE_STEP*sin + a2);
        }else{
            locationBeanNow.setAltitude(locationBeanNow.getAltitude() - LocationBean.MOVE_STEP*sin - a2);
        }

        if (Math.abs(gotoLocation.getLongitude()-locationBeanNow.getLongitude()) < 5*LocationBean.MOVE_STEP
                && Math.abs(gotoLocation.getAltitude() - locationBeanNow.getAltitude())< 5*LocationBean.MOVE_STEP){
            gotoLocationTag++;
            if(gotoLocationTag >= locationBeanList.size()){
                gotoLocationTag = 0;
            }
            Log.e(TAG,"move to next point "+ gotoLocationTag);
        }

        Log.e(TAG,"set local "+locationBeanNow.getAltitude()+" "+locationBeanNow.getLongitude()
        +" sin="+sin+" cos="+cos);

        String providerStr = LocationManager.GPS_PROVIDER;
        try {
            mLocationManager.setTestProviderLocation(providerStr, generateLocation(locationBeanNow.getAltitude(),locationBeanNow.getLongitude()));
        } catch (Exception e) {
            Log.d(TAG, "setNetworkLocation error");
            e.printStackTrace();
        }
    }
    //generate a location
    public Location generateLocation(double lat , double lng) {
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

        return loc;
    }

    private double getSin(double x1,double y1,double x2,double y2){
        double sin = 0.0;
        double distance = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        if(distance>0){
            sin = Math.abs((y2-y1)/distance);
        }
        return sin;
    }

    private double getCos(double x1,double y1,double x2,double y2){
        double cos = 1.0;
        double distance = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        if(distance>0){
            cos = Math.abs((x2-x1)/distance);
        }
        return cos;
    }
}
