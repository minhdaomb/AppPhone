package com.example.androidnetworking.lap1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.androidnetworking.R;

public class bai2_1 extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_bai2_1);
        new Handler ().postDelayed (new Runnable () {
            @Override
            public void run() {
                Intent intent = new Intent (bai2_1.this, bau2_2.class);
                startActivity (intent);
                finish ();
            }
        },SPLASH_TIME_OUT);
    }
}