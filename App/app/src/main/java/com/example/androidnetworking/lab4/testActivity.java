package com.example.androidnetworking.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidnetworking.ASM.LoginActivity;
import com.example.androidnetworking.MyRetrofit.IRetrofitService;
import com.example.androidnetworking.MyRetrofit.Retrofitbuilder;
import com.example.androidnetworking.R;
import com.example.androidnetworking.model.AccessToken;
import com.example.androidnetworking.model.AccessTokenManager;
import com.example.androidnetworking.model.Person;
import com.example.androidnetworking.model.login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class testActivity extends AppCompatActivity {
    private EditText edtUser,edtPass;
    private Button btnLogin,btnGet;
    private TextView tvGet;
    private IRetrofitService service;
    private static String BASE_URL= "http://10.0.2.2:8081/views/";
    private AccessTokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_test);
        IRetrofitService service = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_URL);
        tokenManager = AccessTokenManager.getInstance (getSharedPreferences ("prefs", MODE_PRIVATE));

        edtUser = (EditText) findViewById (R.id.edtUsername);
        edtPass = (EditText)findViewById (R.id.edtPASS);
        btnLogin = (Button)findViewById (R.id.btnDangNhap);
//        btnGet = (Button)findViewById (R.id.btnGet);
//        tvGet = (TextView)findViewById (R.id.TvGet);



        btnLogin.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String username = edtUser.getText ().toString ();
                String password = edtPass.getText ().toString ();
                service.login2 (new login (username,password)).enqueue (LoginCallback);
            }
        });
//        btnGet.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                service.getProfile ().enqueue (getProfileCallback);
//            }
//        });
    }
    Callback<login> LoginCallback = new Callback<login> () {
        @Override
        public void onResponse(Call<login> call,Response<login> response) {
            if(response.isSuccessful ()){
                Intent i = new Intent (testActivity.this,LoginActivity.class);
                startActivity (i);


            }

        }

        @Override
        public void onFailure(Call<login> call,Throwable t) {

        }
    };
}