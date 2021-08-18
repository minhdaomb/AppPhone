package com.example.androidnetworking.ASM;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidnetworking.ASM.Update.InsertProductActivity;
import com.example.androidnetworking.MyRetrofit.IRetrofitService;
import com.example.androidnetworking.MyRetrofit.Retrofitbuilder;
import com.example.androidnetworking.R;
import com.example.androidnetworking.model.login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
QuenMKActivity extends AppCompatActivity {
    private EditText edtQuen;
    private Button btnQuen;
    private IRetrofitService service;
    private static String BASE_URL= "http://10.0.2.2:8081/views/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_quen_m_k);
        IRetrofitService service = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_URL);
        edtQuen = (EditText)findViewById (R.id.edtQuenMK);
        btnQuen = (Button)findViewById (R.id.btnQuenMK);

        btnQuen.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String username = edtQuen.getText ().toString ();
                service.Forgot (new login (username)).enqueue (ForgotPassCallback);
                Toast.makeText ( QuenMKActivity.this, "Kiểm tra tin nhắn Gmail", Toast.LENGTH_LONG ).show ();
            }
        });

    }


    Callback<login> ForgotPassCallback = new Callback<login> () {
        @Override
        public void onResponse(Call<login> call,Response<login> response) {
            if(response.isSuccessful ()){
                Intent intent = new Intent (QuenMKActivity.this,LoginActivity.class);
                startActivity (intent);
            }
        }

        @Override
        public void onFailure(Call<login> call,Throwable t) {

        }
    };
}