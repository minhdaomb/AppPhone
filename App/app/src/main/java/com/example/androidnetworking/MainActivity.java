package com.example.androidnetworking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button button,button2;
    private TextView textView,textView2,textView3;
    private int number1, number2;
    private final int MIN= 1;
    private final int MAX=700;

    private Handler handler = new Handler(Looper.getMainLooper ()){
      public void handleMessage(Message msg){
          super.handleMessage (msg);
          switch (msg.arg1){
              case 1:
                  textView.setText (msg.obj.toString ()+ "");
          }
      }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        button = ( Button ) findViewById (R.id.button);
        button2= (Button) findViewById (R.id.button2);
        textView = (TextView) findViewById (R.id.textView);

        button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                new Thread (new Runnable () {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }

}