package com.example.androidnetworking.lab8.service;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidnetworking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class FCMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_f_c_m );
        Button btntoken = findViewById ( R.id.btnToken );
        btntoken.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance ().getToken ().addOnCompleteListener ( new OnCompleteListener<String> () {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful ()){
                            Log.d (">>>>","get Token error:"+task.getException ());
                            return;
                        }
                        String token = task.getResult ();
                        Log.i (">>>>","get Token: "+token);
                    }
                } );
            }
        } );
    }
}