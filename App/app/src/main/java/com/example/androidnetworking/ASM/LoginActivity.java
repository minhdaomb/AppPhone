package com.example.androidnetworking.ASM;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidnetworking.MyRetrofit.IRetrofitService;
import com.example.androidnetworking.MyRetrofit.Retrofitbuilder;
import com.example.androidnetworking.R;
import com.example.androidnetworking.lab4.testActivity;
import com.example.androidnetworking.model.AccessToken;
import com.example.androidnetworking.model.AccessTokenManager;
import com.example.androidnetworking.model.Person;
import com.example.androidnetworking.model.login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUser,edtPass;
    private Button btnLogin;
    private TextView tvRegister,tvForgotPassWord;
    private IRetrofitService service;
    private AccessTokenManager tokenManager;
    private static String BASE_URL= "http://10.0.2.2:8081/views/";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        // STATUS BAR COLOR
        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.primary_color));

        IRetrofitService service = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_URL);
        tokenManager = AccessTokenManager.getInstance (getSharedPreferences ("prefs", MODE_PRIVATE));

        edtUser = (EditText) findViewById (R.id.inputEmail);
        edtPass = (EditText)findViewById (R.id.inputPassword);
        btnLogin = (Button)findViewById (R.id.btnLogin);
        tvRegister = (TextView)findViewById (R.id.tvRegister);
        tvForgotPassWord = (TextView)findViewById (R.id.tvForgotPassWord) ;
        tvForgotPassWord.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (LoginActivity.this,QuenMKActivity.class);
                startActivity (i);
            }
        });
        tvRegister.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (LoginActivity.this,SigUpActivity.class);
                startActivity (i);
            }
        });

        btnLogin.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String username = edtUser.getText ().toString ();
                String password = edtPass.getText ().toString ();
                service.login2 (new login (username,password)).enqueue (LoginCallback);
            }
        });
    }

    Callback<login> LoginCallback = new Callback<login> () {
        @Override
        public void onResponse(Call<login> call,Response<login> response) {
            if(response.isSuccessful ()){
                Intent i = new Intent (LoginActivity.this,ProductActivity2.class);
                startActivity (i);
            }
        }

        @Override
        public void onFailure(Call<login> call,Throwable t) {

        }
    };


}