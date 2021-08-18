package com.example.androidnetworking.ASM.category;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidnetworking.MyRetrofit.IRetrofitService;
import com.example.androidnetworking.MyRetrofit.Retrofitbuilder;
import com.example.androidnetworking.R;
import com.example.androidnetworking.model.ResponseModel;
import com.example.androidnetworking.model.category;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {
    private static String BASE_URL= "http://10.0.2.2:8081/views/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_category );
        EditText edtCategory = findViewById ( R.id.edtCategory );
        Button btnSaveCate = findViewById ( R.id.btnSaveCate );

        btnSaveCate.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                IRetrofitService service2 = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_URL);
                category category = new category ();
                category.setCategory_name ( edtCategory.getText ().toString () );
                service2.insertCategory ( category ).enqueue ( insertCategoryCB );
            }
        } );
    }

    Callback<ResponseModel> insertCategoryCB = new Callback<ResponseModel> () {
        @Override
        public void onResponse(Call<ResponseModel> call,Response<ResponseModel> response) {
            if(response.isSuccessful ()){
                ResponseModel model = response.body ();
                if (model.getStatus ()){
                    Intent q = new Intent (CategoryActivity.this,View_Category.class);
                    startActivity ( q );
                }else {
                    Toast.makeText ( CategoryActivity.this, "Insert Failed", Toast.LENGTH_LONG ).show ();
                }
            }else {
                Log.e("onRenponse",response.message ());
            }
        }

        @Override
        public void onFailure(Call<ResponseModel> call,Throwable t) {
            Log.e("onFaled",t.getMessage ());
        }
    };
}