package com.ruijie.localapp.dialog;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruijie.localapp.LocationBean;
import com.ruijie.localapp.R;

/**
 * Created by dongzhong on 2018/5/30.
 */

public class FloatingImageDisplayService extends Service {
    public static boolean isStarted = false;

    private LocationManager mLocationManager;
    private LocationListenerImpl mLocationListenerImpl;
    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;

    private View displayView;
    private Button startOrMoveBtn,goNorthBtn,goEastBtn,goSouthBtn,goWestBtn;
    private TextView longTV,latTV;

    private boolean goEast = false;
    private boolean goWest = false;
    private boolean goNorth = false;
    private boolean goSouth = false;


    private Handler changeImageHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        isStarted = true;
        mLocationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        layoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.width = 340;
        layoutParams.height = 230;
        layoutParams.x = 300;
        layoutParams.y = 300;


        changeImageHandler = new Handler(this.getMainLooper(), changeImageCallback);
        //注册位置监听
        mLocationListenerImpl=new LocationListenerImpl();
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, mLocationListenerImpl);


    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationManager!=null) {
            mLocationManager.removeUpdates(mLocationListenerImpl);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showFloatingWindow();
        return super.onStartCommand(intent, flags, startId);
    }

    private void showFloatingWindow() {
        //if (Settings.canDrawOverlays(this)) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        displayView = layoutInflater.inflate(R.layout.image_display, null);
        displayView.setOnTouchListener(new FloatingOnTouchListener());
        startOrMoveBtn = displayView.findViewById(R.id.startOrMoveBtn);
        goNorthBtn = displayView.findViewById(R.id.goNorthBtn);
        goEastBtn = displayView.findViewById(R.id.goEastBtn);
        goSouthBtn = displayView.findViewById(R.id.goSouthBtn);
        goWestBtn = displayView.findViewById(R.id.goWestBtn);
        longTV = displayView.findViewById(R.id.longTV);
        latTV = displayView.findViewById(R.id.latTV);

        windowManager.addView(displayView, layoutParams);
        changeImageHandler.sendEmptyMessageDelayed(0, 1000);

        startOrMoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("test", "startOrMoveBtn 点击事件");
                LocationBean.MOVEING = false;
                goNorth = false;
                goEast = false;
                goSouth = false;
                goWest = false;
            }
        });
        goNorthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationBean.MOVEING = true;
                goNorth = !goNorth;
                goSouth = false;
//                if(goNorth){
//                    goNorthBtn .setBackgroundColor(Color.rgb(255, 0, 0));
//                }
            }
        });
        goSouthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationBean.MOVEING = true;
                goNorth = false;
                goSouth = !goSouth;
//                if(goSouth){
//                    goSouthBtn .setBackgroundColor(Color.rgb(255, 0, 0));
//                }
            }
        });
        goEastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationBean.MOVEING = true;
                goWest = false;
                goEast = !goEast;
//                if(goEast){
//                    goEastBtn .setBackgroundColor(Color.rgb(255, 0, 0));
//                }
            }
        });
        goWestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationBean.MOVEING = true;
                goWest = !goWest;
                goEast = false;
//                if(goWest){
//                    goWestBtn.setBackgroundColor(Color.rgb(255, 0, 0));
//                }
            }
        });
    }

    private Handler.Callback changeImageCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0) {
                if(goNorth){
                    LocationBean.staticAltitude += LocationBean.MOVE_STEP/2;
                }
                if(goSouth){
                    LocationBean.staticAltitude -= LocationBean.MOVE_STEP/2;
                }
                if(goWest){
                    LocationBean.staticLongitude += LocationBean.MOVE_STEP/2;
                }
                if(goEast){
                    LocationBean.staticLongitude -= LocationBean.MOVE_STEP/2;
                }

                changeImageHandler.sendEmptyMessageDelayed(0, LocationBean.UPDATE_FREQ);

            }

            return false;
        }
    };

    private class FloatingOnTouchListener implements View.OnTouchListener {
        private int x;
        private int y;

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x = (int) event.getRawX();
                    y = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int nowX = (int) event.getRawX();
                    int nowY = (int) event.getRawY();
                    int movedX = nowX - x;
                    int movedY = nowY - y;
                    x = nowX;
                    y = nowY;
                    layoutParams.x = layoutParams.x + movedX;
                    layoutParams.y = layoutParams.y + movedY;
                    windowManager.updateViewLayout(view, layoutParams);
                    break;
                default:
                    break;
            }
            return false;
        }
    }
    private java.text.DecimalFormat df =new  java.text.DecimalFormat("#.0000");
//df.format(你要格式化的数字);
    private class LocationListenerImpl implements LocationListener {
        //当设备位置发生变化时调用该方法
        @Override
        public void onLocationChanged(Location location) {
            if (location!=null) {
                Double longitude=location.getLongitude();
                Double altitude=location.getAltitude();
                longTV.setText(df.format(longitude)+"");
                latTV.setText(" "+df.format(altitude)+"");
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
}
