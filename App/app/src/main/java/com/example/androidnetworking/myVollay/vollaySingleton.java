package com.example.androidnetworking.myVollay;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class vollaySingleton {
    private static vollaySingleton instance;
    private RequestQueue requestQueue;
    private static Context context;

    private vollaySingleton(Context _context){
        context = _context;
        requestQueue = getRequestQueue();
    }

    public static synchronized vollaySingleton getInstance(Context _context){
        if(instance == null)instance = new vollaySingleton (_context);
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue (context.getApplicationContext ());
        }
        return requestQueue;
    }
    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue ().add (request);
    }
}
