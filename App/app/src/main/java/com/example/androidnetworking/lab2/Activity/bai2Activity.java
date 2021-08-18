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
import com.example.androidnetworking.model.Person;
import com.example.androidnetworking.model.diemsv;
import com.example.androidnetworking.myVollay.vollaySingleton;

import org.json.JSONObject;

public class bai2Activity extends AppCompatActivity {
    private TextView tv1;
    private Button btn1,btnb1,btnb2,btnb3,btnb4;
    private EditText edt1,edt2,edt3,edt4,edt5;
    private  String url="http://10.0.2.2:8081/demo2.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main2);
        tv1= (TextView)findViewById (R.id.tv1);
        edt1=(EditText)findViewById (R.id.edt1);
        edt2=(EditText)findViewById (R.id.edt2);
        edt3=(EditText)findViewById (R.id.edt3);
        edt4=(EditText)findViewById (R.id.edt4);
        edt5=(EditText)findViewById (R.id.edt5);
        btn1=(Button)findViewById (R.id.btn1);
//        btnb1=(Button)findViewById (R.id.btnbai1);
//        btnb2=(Button)findViewById (R.id.btnbai2);
//        btnb3=(Button)findViewById (R.id.btnbai3);
//        btnb4=(Button)findViewById (R.id.btnbai4);

//        btnb1.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent (bai2Activity.this,MainActivity.class);
//                startActivity (i);
//                finish ();
//            }
//        });
//        btnb2.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent (bai2Activity.this,bai2Activity.class);
//                startActivity (i);
//                finish ();
//            }
//        });
//        btnb3.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent (bai2Activity.this,bai3Activity.class);
//                startActivity (i);
//                finish ();
//            }
//        });
//        btnb4.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent (bai2Activity.this,bai4Activity.class);
//                startActivity (i);
//                finish ();
//            }
//        });

        btn1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                String name =edt1.getText ().toString ();
                double diema = Integer.parseInt ( edt2.getText ().toString ());
                double diemb = Integer.parseInt ( edt3.getText ().toString ());
                double diemc = Integer.parseInt ( edt4.getText ().toString ());
                double diemd = Integer.parseInt ( edt5.getText ().toString ());
                Double chuvi= Double.valueOf (String.valueOf ((diema+diemb+diemc+diemd)/4));
                diemsv p = new diemsv (name,diema,diemb,diemc,diemd);
                postToSeverByVollay (p);
                tv1.setText (name+"  "+"Điểm trung bình: "+chuvi);
//                int a = Integer.parseInt (edt1.getText ().toString ());
//                int b = Integer.parseInt (edt2.getText ().toString ());
//                int c = Integer.parseInt (edt3.getText ().toString ());
//                url += "a="+a+"&b=" + b + "&c=" +c;
//                DatabaseBackroudTask databaseBackroudTask = new DatabaseBackroudTask (MainActivity.this);
//                databaseBackroudTask.execute (url);
            }
        });
    }
    //Vollay
    private void postToSeverByVollay(diemsv diemsv){
        vollaySingleton.getInstance (this.getApplicationContext ()).getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest (Request.Method.POST,url,null,
                new Response.Listener<JSONObject> () {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String name = response.getString ("name");
                            String diema = response.getString ("diema");
                            String diemb = response.getString ("diemb");
                            String diemc = response.getString ("diemc");
                            String diemd = response.getString ("diemd");
                            diemsv p = new diemsv (name,diema,diemb,diemc,diemd);

                        }catch (Exception e){
                            Log.e("Error","loi o day ne: " + e.getMessage ());
                        }
                    }
                },
                new Response.ErrorListener () {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=UTF-8";
            }

            @Override
            public byte[] getBody() {
                try {
                    JSONObject body = new JSONObject ();
                    body.put("name",diemsv.getName ());
                    body.put ("diema",diemsv.getDiema ());
                    body.put ("diemb",diemsv.getDiemab ());
                    body.put ("diemc",diemsv.getDiemc ());
                    body.put ("diemd",diemsv.getDiemad ());
                    final String requestBody = body.toString ();
                    return  requestBody == null ? null : requestBody.getBytes ("utf-8");

                }catch (Exception e){
                    Log.e("Error","loi o day ne: " + e.getMessage ());
                }
                    return null;
            }
        };
        vollaySingleton.getInstance (this).addToRequestQueue (request);
    }










}