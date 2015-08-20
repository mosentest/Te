package org.moziqi.util;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by moziqi on 15-8-7.
 */
public class GeneralApplication extends Application {

    public static RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getHttpQueues() {
        return mRequestQueue;
    }
}
