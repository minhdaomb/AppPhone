package com.example.androidnetworking.lab2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.androidnetworking.R;
import com.example.androidnetworking.lab2.DAO.DatabaseBackroudTask;
import com.example.androidnetworking.model.Person;
import com.example.androidnetworking.myVollay.vollaySingleton;

import org.json.JSONObject;

public class bai4Activity extends AppCompatActivity {
    private TextView tv1;
    private Button btn1,btnb1,btnb2,btnb3,btnb4;
    private EditText edt1,edt2,edt3;
    private  String url="http://10.0.2.2:8081/demo.php?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.bai4_lab2);
        tv1= (TextView)findViewById (R.id.tv1);
        edt1=(EditText)findViewById (R.id.edt1);
        edt2=(EditText)findViewById (R.id.edt2);
        edt3=(EditText)findViewById (R.id.edt3);
        btn1=(Button)findViewById (R.id.btn1);
        btnb1=(Button)findViewById (R.id.btnbai1);
        btnb2=(Button)findViewById (R.id.btnbai2);
        btnb3=(Button)findViewById (R.id.btnbai3);
        btnb4=(Button)findViewById (R.id.btnbai4);


        btnb1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (bai4Activity.this,MainActivity.class);
                startActivity (i);
                finish ();
            }
        });
        btnb2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (bai4Activity.this,bai2Activity.class);
                startActivity (i);
                finish ();
            }
        });
        btnb3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (bai4Activity.this,bai3Activity.class);
                startActivity (i);
                finish ();
            }
        });
        btnb4.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (bai4Activity.this,bai4Activity.class);
                startActivity (i);
                finish ();
            }
        });


        btn1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                int a = Integer.parseInt (edt1.getText ().toString ());
                int b = Integer.parseInt (edt2.getText ().toString ());
                int c = Integer.parseInt (edt3.getText ().toString ());
                url += "a="+a+"&b=" + b + "&c=" +c;
                DatabaseBackroudTask databaseBackroudTask = new DatabaseBackroudTask (bai4Activity.this);
                databaseBackroudTask.execute (url);
            }
        });
    }
}