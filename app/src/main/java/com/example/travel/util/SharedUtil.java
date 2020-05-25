package com.example.travel.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedUtil {
    Context cnt;
    SharedPreferences spf;

    public SharedUtil(Context cnt2) {
        this.cnt = cnt2;
        this.spf = cnt2.getSharedPreferences("login", 0);
    }

    public void writedata(String Key, String Value) {
        Editor ed = this.spf.edit();
        ed.putString(Key, Value);
        ed.commit();
    }

    public String readdata(String Key, String def) {
        return this.spf.getString(Key, def);
    }
}
