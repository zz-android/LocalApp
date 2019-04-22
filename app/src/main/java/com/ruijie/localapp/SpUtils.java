package com.ruijie.localapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SpUtils {
    private static SharedPreferences sp;
    public static void putLocationBean(Context ctx, List<LocationBean> bookList) {
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(bookList);
        editor.putString("LocationBean", json);
        editor.commit();
    }

    public static List<LocationBean> getLocationBean(Context ctx) {
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", MODE_PRIVATE);
        }
        Gson gson = new Gson();
        String json = sp.getString("LocationBean", null);
        Type type = new TypeToken<List<LocationBean>>() {
        }.getType();
        List<LocationBean> arrayList = gson.fromJson(json, type);
        return arrayList;
    }


}
