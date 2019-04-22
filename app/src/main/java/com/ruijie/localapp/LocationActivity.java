package com.ruijie.localapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LocationActivity extends Activity {

    private ListView locationBeanListView;
    private LocationBeanAdapter locationBeanAdapter;
    private List<LocationBean> locationBeanList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        locationBeanListView = (ListView) findViewById(R.id.locationBeanListView);

        locationBeanList = new ArrayList<LocationBean>();
        locationBeanAdapter = new LocationBeanAdapter(this,locationBeanList);
        locationBeanListView.setAdapter(locationBeanAdapter);

        locationBeanList.add(new LocationBean(119.24251,26.06000));
        locationBeanList.add(new LocationBean(119.24527,26.05615));
        locationBeanAdapter.notifyDataSetChanged();

    }

}