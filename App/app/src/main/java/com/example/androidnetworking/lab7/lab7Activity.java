package com.example.androidnetworking.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidnetworking.R;
import com.example.androidnetworking.lab7.adapter.messageAdapter;
import com.example.androidnetworking.lab7.model.Message;
import com.example.androidnetworking.model.Student;

import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class lab7Activity extends AppCompatActivity {
    EditText edtSend;
    Button btnSend;
    ListView lvMess;

    messageAdapter adapter;
    List<Message> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_lab7 );
        edtSend= findViewById ( R.id.edtSend );
        btnSend = findViewById ( R.id.btnSend );
        lvMess = findViewById ( R.id.lvMess );
        data=new ArrayList<> ();
        adapter = new messageAdapter (data, getApplicationContext ());
        lvMess.setAdapter ( adapter );


        OkHttpClient client = new OkHttpClient ();
        Request request = new Request.Builder ().url ( "ws://10.0.2.2:2046/socket.php" ).build ();
        WebSocket socket = client.newWebSocket ( request, socketListener );
        client.dispatcher ().executorService ().shutdown ();

        btnSend.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String s = edtSend.getText ().toString ();
                Message message = new Message (s, true);
                JSONObject object = new JSONObject ();
                try {
                    object.put ("data",message.getData ());
                    object.put ("fromMe",message.getFromMe ());

                    socket.send ( object.toString () );

                    List<Message> _data = new ArrayList<Message> (data);
                    _data.add ( message );
                    data.clear ();
                    data.addAll ( _data );
                    adapter.notifyDataSetChanged ();
                    lvMess.setAdapter ( adapter );
                    edtSend.setText ( "" );
                }catch (Exception e) {
                    e.printStackTrace ();
                }
            }
        } );
    }


    WebSocketListener socketListener = new WebSocketListener () {
        @Override
        public void onOpen(WebSocket webSocket,Response response) {
            super.onOpen ( webSocket,response );
        }

        @Override
        public void onMessage(WebSocket webSocket,String text) {
            super.onMessage ( webSocket,text );
            Log.d(">>>> Socket onMessage",text);
            text = text.replace ( "\\\"","'" );
            text = text.substring ( 1, text.length()-1 );
            try {
                 JSONObject object = new JSONObject (text);
                 String d = object.getString ( "data" );

                 Message message = new Message (d, false);


                 runOnUiThread ( new Runnable () {
                     @Override
                     public void run() {
                         List<Message> _data = new ArrayList<Message> (data);
                         _data.add ( message );
                         data.clear ();
                         data.addAll ( _data );
                         adapter.notifyDataSetChanged ();
                         lvMess.setAdapter ( adapter );
                     }
                 } );
            } catch (JSONException e) {
                e.printStackTrace ();
            }
        }

        @Override
        public void onMessage(WebSocket webSocket,ByteString bytes) {
            super.onMessage ( webSocket,bytes );
        }

        @Override
        public void onClosing(WebSocket webSocket,int code,String reason) {
            super.onClosing ( webSocket,code,reason );
        }

        @Override
        public void onClosed(WebSocket webSocket,int code,String reason) {
            super.onClosed ( webSocket,code,reason );
        }

        @Override
        public void onFailure(WebSocket webSocket,Throwable t,@Nullable Response response) {
            super.onFailure ( webSocket,t,response );
        }
    };
}