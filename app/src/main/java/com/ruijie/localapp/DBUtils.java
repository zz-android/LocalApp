package com.ruijie.localapp;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DBUtils {
    private static final String TAG = "DBUtils";
    private static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); //加载驱动
            String ip = "zhost.top";
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + ip + ":3306/local_db",
                    "root", "123456");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    public static HashMap<String, String> getDiviceAuthInfo(String diviceId) {
        HashMap<String, String> map = new HashMap<>();
        Connection conn = getConnection();
        try {
            Statement st = conn.createStatement();
            String sql = "select * from auth where device_id = '" + diviceId + "'";
            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                return null;
            } else {
                int cnt = res.getMetaData().getColumnCount();
                //res.last(); int rowCnt = res.getRow(); res.first();
                res.next();
                for (int i = 1; i <= cnt; ++i) {
                    String field = res.getMetaData().getColumnName(i);
                    map.put(field, res.getString(field));
                }
                conn.close();
                st.close();
                res.close();
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, " 数据操作异常");
            return null;
        }
    }
}
