package com.example.androidnetworking.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.androidnetworking.ASM.ProductActivity;
import com.example.androidnetworking.ASM.Update.InsertProductActivity;
import com.example.androidnetworking.ASM.adapter.ProductAdapter2;
import com.example.androidnetworking.ASM.adapter.ProductApdapter;
import com.example.androidnetworking.MyRetrofit.IRetrofitService;
import com.example.androidnetworking.MyRetrofit.Retrofitbuilder;
import com.example.androidnetworking.R;
import com.example.androidnetworking.lab3.adapter.PersonAdapter;
import com.example.androidnetworking.model.Person;
import com.example.androidnetworking.model.Product;
import com.example.androidnetworking.model.ten;
import com.example.androidnetworking.myVollay.vollaySingleton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class bai1_lab3_Activity extends AppCompatActivity {
    private TextView tv1;
    private Button btn1;
    private List<Product> data;
//    private String url = "http://10.0.2.2:8081/demo2.php";
    private IRetrofitService service;
    private RecyclerView recyclerView;
    private ProductAdapter2 adapter;
    private static String BASE_URL= "http://10.0.2.2:8081/views/";
    private FloatingActionButton btnThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.lab3b1);
        recyclerView = findViewById (R.id.recyclerView);
        IRetrofitService service = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_URL);
        recyclerView.setHasFixedSize (true);
        recyclerView.setLayoutManager (new LinearLayoutManager (this));
        service.getAllProduct ().enqueue (getOneCallback);
        btnThem=(FloatingActionButton)findViewById ( R.id.InsertProduct );

        btnThem.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivityForResult ( new Intent ( bai1_lab3_Activity.this,InsertProductActivity.class),2 );
            }
        } );

    }

    Callback<List<Product>> getOneCallback = new Callback<List<Product>> () {
        @Override
        public void onResponse(Call<List<Product>> call,Response<List<Product>> response) {
            if (response.isSuccessful ()) {
                data = response.body ();
                adapter = new ProductAdapter2 (data, bai1_lab3_Activity.this);
                recyclerView.setAdapter (adapter);
            }

        }

        @Override
        public void onFailure(Call<List<Product>> call,Throwable t) {

        }
    };

}
