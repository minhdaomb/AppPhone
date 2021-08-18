package com.example.androidnetworking.lab2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.androidnetworking.MyRetrofit.IRetrofitService;
import com.example.androidnetworking.MyRetrofit.Retrofitbuilder;
import com.example.androidnetworking.R;
import com.example.androidnetworking.lab2.DAO.DatabaseBackroudTask;
import com.example.androidnetworking.model.Person;
import com.example.androidnetworking.myVollay.vollaySingleton;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView tv1;
    private Button btn1,btnb1,btnb2,btnb3,btnb4;
    private EditText edt1,edt2,edt3;
    private  String url="http://10.0.2.2:8081/demo2.php";
    private IRetrofitService service;
    private static String BASE_URL= "http://10.0.2.2:8081/views/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main2);

        IRetrofitService service = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_URL);

        tv1= (TextView)findViewById (R.id.tv1);
        edt1=(EditText)findViewById (R.id.edt1);
        edt2=(EditText)findViewById (R.id.edt2);
//        edt3=(EditText)findViewById (R.id.edt3);
        btn1=(Button)findViewById (R.id.btn1);
//        btnb1=(Button)findViewById (R.id.btnbai1);
//        btnb2=(Button)findViewById (R.id.btnbai2);
//        btnb3=(Button)findViewById (R.id.btnbai3);
//        btnb4=(Button)findViewById (R.id.btnbai4);

//        btnb1.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent (MainActivity.this,MainActivity.class);
//                startActivity (i);
//                finish ();
//            }
//        });
//        btnb2.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent (MainActivity.this,bai2Activity.class);
//                startActivity (i);
//                finish ();
//            }
//        });
//        btnb3.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent (MainActivity.this,bai3Activity.class);
//                startActivity (i);
//                finish ();
//            }
//        });
//        btnb4.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent (MainActivity.this,bai4Activity.class);
//                startActivity (i);
//                finish ();
//            }
//        });

        btn1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String id = edt1.getText ().toString ();
                String name = edt2.getText ().toString ();
                Person p = new Person (id,name);
                postToSeverByVollay (p);
                tv1.setText ("Score: "+p.getId ()+ "   " + "Name: "+ p.getName ());
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
    private void postToSeverByVollay(Person person){
        vollaySingleton.getInstance (this.getApplicationContext ()).getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest (Request.Method.POST,url,null,
                new Response.Listener<JSONObject> () {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String id = response.getString ("id");
                            String name = response.getString ("name");
                            Person p = new Person (id,name);

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
                    body.put("id",person.getId ());
                    body.put ("name",person.getName ());
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