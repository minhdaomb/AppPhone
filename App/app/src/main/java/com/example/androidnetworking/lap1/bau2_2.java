package com.example.androidnetworking.lap1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidnetworking.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class bau2_2 extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgLoad;
    private Button btnLoad,btnb1,btnb2,btnb3;
    private ProgressDialog progressDialog;
    private String url = "https://fptshop.com.vn/Uploads/images/image001(1265).jpg";
    private Bitmap bitmap = null;
    private TextView tvMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_bau2_2);
        imgLoad = (ImageView)findViewById (R.id.imageViewb2);
        btnLoad=(Button)findViewById (R.id.buttonb2);
        tvMessage=(TextView)findViewById (R.id.textViewb2);
        btnb1 = (Button) findViewById (R.id.btnbai1);
        btnb2 = (Button) findViewById (R.id.btnbai2);
        btnb3 = (Button) findViewById (R.id.btnbai3);
        btnLoad.setOnClickListener (this::onClick);

        btnb1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (bau2_2.this,bai1.class);
                startActivity (i);
                finish ();
            }
        });
        btnb2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent q = new Intent (bau2_2.this,bai2_1.class);
                startActivity (q);
                finish ();
            }
        });
        btnb3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (bau2_2.this,bai1.class);
                startActivity (i);
                finish ();
            }
        });
    }

    private Handler messageHandler = new Handler (){
        public void handleMessage(Message msg){
            super.handleMessage (msg);
            Bundle bundle = msg.getData ();
            String message = bundle.getString ("message");
            tvMessage.setText (message);
            imgLoad.setImageBitmap (bitmap);
            progressDialog.dismiss ();
        }
    };

    @Override
    public void onClick(View view) {
        progressDialog = ProgressDialog.show (bau2_2.this,"","Dowloading...");
        Runnable aRunnable = new Runnable () {
            @Override
            public void run() {
                bitmap = dowloadBitmap(url);
                Message msg = messageHandler.obtainMessage ();
                Bundle bundle = new Bundle ();
                String threadMessage = "Image downloaded";
                bundle.putString ("message", threadMessage);
                msg.setData (bundle);
                messageHandler.sendMessage (msg);
            }
        };
        Thread thread = new Thread (aRunnable);
        thread.start ();
    }
    private Bitmap dowloadBitmap(String link){
        try {
            URL url = new URL (link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection ();
            connection.connect ();
            InputStream inputStream = connection.getInputStream ();
            Bitmap bitmap = BitmapFactory.decodeStream (inputStream);
            return bitmap;
        }catch (Exception e){
            e.printStackTrace ();
        }return null;
    }
    
}