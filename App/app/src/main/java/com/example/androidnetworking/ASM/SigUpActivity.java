package com.example.androidnetworking.ASM;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidnetworking.MyRetrofit.IRetrofitService;
import com.example.androidnetworking.MyRetrofit.Retrofitbuilder;
import com.example.androidnetworking.R;
import com.example.androidnetworking.lab4.testActivity;
import com.example.androidnetworking.model.login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigUpActivity extends AppCompatActivity {
    private IRetrofitService service;
    private EditText edtUser,edtPass;
    private Button btnSigUp;
    private static String BASE_URL= "http://10.0.2.2:8081/views/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_sig_up);
        IRetrofitService service = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_URL);
        edtUser = (EditText)findViewById (R.id.usernamedk);
        edtPass = (EditText)findViewById (R.id.password1);
        btnSigUp = (Button)findViewById (R.id.register);

        btnSigUp.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String username = edtUser.getText ().toString ();
                String password = edtPass.getText ().toString ();
                service.SigUP (new login (username,password)).enqueue (SigUpCallback);
            }
        });
    }

    Callback<login> SigUpCallback = new Callback<login> () {
        @Override
        public void onResponse(Call<login> call,Response<login> response) {
            if(response.isSuccessful ()){
                Intent i = new Intent (SigUpActivity.this,LoginActivity.class);
                startActivity (i);
            }
        }

        @Override
        public void onFailure(Call<login> call,Throwable t) {

        }
    };
}