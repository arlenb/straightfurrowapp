package com.arlenburroughs.straightfurrowconsulting.tools;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Arlen on 2/10/16.
 */
public class DefaultRequestQueue {

    private static RequestQueue defaultQueue;

    public synchronized static RequestQueue getDefaultQueue(Context context) {
        return defaultQueue==null ? defaultQueue=Volley.newRequestQueue(context) : defaultQueue;
    }

}
