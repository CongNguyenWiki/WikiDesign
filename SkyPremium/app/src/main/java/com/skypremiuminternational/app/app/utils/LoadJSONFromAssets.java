package com.skypremiuminternational.app.app.utils;

import android.content.Context;

import com.skypremiuminternational.app.R;

import java.io.IOException;
import java.io.InputStream;

public class LoadJSONFromAssets {
    public static String loadJSONFromAsset(Context context, String file) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(file);
            int size = is.available();
            byte[] buffer = new byte[size];
                is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    public static String loadJSONFromAsset(Context context, int file) {
        String json = null;
        try {
            InputStream is = context.getResources().openRawResource(R.raw.list_country);
            int size = is.available();
            byte[] buffer = new byte[size];
                is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
