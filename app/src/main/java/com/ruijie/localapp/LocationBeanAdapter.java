package com.ruijie.localapp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

public class LocationBeanAdapter extends BaseAdapter {

    private List<LocationBean> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public LocationBeanAdapter(Context context, List<LocationBean> data) {
        //传入的data，就是我们要在listview中显示的信息
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }
    //这里定义了一个类，用来表示一个item里面包含的东西，像我的就是一个imageView和三个TextView，按自己需要来
    public class Info {

        public TextView longitudeTV;
        public TextView altitudeTV;
        public TextView remarkTV;




    }
    //所有要返回的数量，Id，信息等，都在data里面，从data里面取就好
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    //跟actvity中的oncreat()差不多，目的就是给item布局中的各个控件对应好，并添加数据
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Info info = new Info();
        convertView = layoutInflater.inflate(R.layout.listview_locationbean, null);

        //设置数据
        info.longitudeTV = convertView.findViewById(R.id.longitudeTV);
        info.longitudeTV.setText(data.get(position).getLongitude()+"");
        info.altitudeTV = convertView.findViewById(R.id.altitudeTV);
        info.altitudeTV.setText(data.get(position).getAltitude()+"");
        info.remarkTV = convertView.findViewById(R.id.remarkTV);
        info.remarkTV.setText(data.get(position).getRemark());



        return convertView;
    }

}
