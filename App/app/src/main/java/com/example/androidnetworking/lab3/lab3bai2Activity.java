//package com.example.androidnetworking.lab3;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.example.androidnetworking.MyRetrofit.IRetrofitService;
//import com.example.androidnetworking.MyRetrofit.Retrofitbuilder;
//import com.example.androidnetworking.R;
//import com.example.androidnetworking.model.diemsv;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class lab3bai2Activity extends AppCompatActivity {
//    private IRetrofitService service;
//    private TextView tv5;
//    private EditText edtname,edtso1,edtso2,edtso3,edtso4;
//    private Button btn;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate (savedInstanceState);
//        setContentView (R.layout.activity_lab3bai2);
//        service = Retrofitbuilder.createService (IRetrofitService.class);
//        tv5 = (TextView)findViewById (R.id.tv5);
//        edtname = (EditText) findViewById (R.id.edtname);
//        edtso1 = (EditText) findViewById (R.id.edtso1);
//        edtso2 = (EditText) findViewById (R.id.edtso2);
//        edtso3 = (EditText) findViewById (R.id.edtso3);
//        edtso4 = (EditText) findViewById (R.id.edtso4);
//        btn = (Button)findViewById (R.id.button4);
//        btn.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                String ten = edtname.getText ().toString ();
//                String so1 = edtso1.getText ().toString ();
//                String so2 = edtso2.getText ().toString ();
//                String so3 = edtso3.getText ().toString ();
//                String so4 = edtso4.getText ().toString ();
//                service.postdiem (new diemsv (ten, Double.parseDouble (so1),Double.parseDouble (so2),
//                        Double.parseDouble (so3),
//                        Double.parseDouble (so4),0.0));
//            }
//        });
//    }
//    Callback<diemsv> postDiemSVCallback = new Callback<diemsv> () {
//        @Override
//        public void onResponse(Call<diemsv> call,Response<diemsv> response) {
//            if (response.isSuccessful ()){
//                diemsv dsv = response.body ();
//                tv5.setText (dsv.getTen ()+" "+"Điểm trung bình: "+dsv.getTrungbinh ());
//            }
//        }
//
//        @Override
//        public void onFailure(Call<diemsv> call,Throwable t) {
//
//        }
//    };
//}