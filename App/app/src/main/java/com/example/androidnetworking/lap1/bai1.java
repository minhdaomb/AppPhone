package com.example.androidnetworking.lap1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidnetworking.R;

import java.io.IOException;
import java.net.URL;

public class bai1 extends AppCompatActivity implements View.OnClickListener {
    private Button btnLoad,btnb1,btnb2,btnb3;
    private ImageView imageView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_bai1);
        btnLoad = (Button) findViewById (R.id.buttonb1);
        imageView = (ImageView) findViewById (R.id.imageView);
        textView = (TextView) findViewById (R.id.textView4);
        btnb1 = (Button) findViewById (R.id.btnbai1);
        btnb2 = (Button) findViewById (R.id.btnbai2);
        btnb3 = (Button) findViewById (R.id.btnbai3);

        btnb1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (bai1.this,bai1.class);
                startActivity (i);
                finish ();
            }
        });
        btnb2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent q = new Intent (bai1.this,bai2_1.class);
                startActivity (q);
                finish ();
            }
        });
        btnb3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (bai1.this,bau2_2.class);
                startActivity (i);
                finish ();
            }
        });

        btnLoad.setOnClickListener (this::onClick);
    }

    private Bitmap loadImageFromNetWork(String link){
        URL url;
        Bitmap bmp = null;
        try{
            url = new URL(link);
            bmp = BitmapFactory.decodeStream (url.openConnection ().getInputStream ());
        }catch (IOException e){
            e.printStackTrace ();
        }
        return bmp;
    }

    @Override
    public void onClick(View view) {
        final Thread myThread = new Thread (new Runnable () {
            @Override
            public void run() {
                final Bitmap bitmap = loadImageFromNetWork ("https://fptshop.com.vn/Uploads/images/image001(1265).jpg");
                imageView.post (new Runnable () {
                    @Override
                    public void run() {
                        Toast.makeText (bai1.this,"đang load hình",Toast.LENGTH_SHORT).show ();
                        SystemClock.sleep (4000);
                        textView.setText ("Image Dowloaded");
                        imageView.setImageBitmap (bitmap);
                    }
                });
            }
        });
        myThread.start ();
    }


}