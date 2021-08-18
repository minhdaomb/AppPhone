package com.example.androidnetworking.ASM.Update;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.androidnetworking.ASM.ProductActivity;
import com.example.androidnetworking.ASM.ProductActivity2;
import com.example.androidnetworking.ASM.adapter.categoriesAdapter;
import com.example.androidnetworking.MyRetrofit.IRetrofitService;
import com.example.androidnetworking.MyRetrofit.Retrofitbuilder;
import com.example.androidnetworking.R;
import com.example.androidnetworking.model.Product;
import com.example.androidnetworking.model.Productcategory;
import com.example.androidnetworking.model.Response2pikModel;
import com.example.androidnetworking.model.ResponseModel;
import com.example.androidnetworking.model.cart;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertProductActivity extends AppCompatActivity {

    private EditText edtProductName,edtPrice;
    private Spinner spnCategoryID;
    private TextView tvChup;
    private ImageView imageViewProduct;
    private Button btnSave, btnCancel, themgiohang;

    private static String BASE_URL= "http://10.0.2.2:8081/views/";
    private static String BASE_2PIK_URL= "https://2.pik.vn/";

    private List<Productcategory> data;
    private categoriesAdapter adapter;
    private String image_url = null;

    private Integer category_id = -1;
    private Integer productID=-1 ;
    private Product model = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_insert );
        themgiohang=findViewById ( R.id.Cart );
        edtProductName=findViewById ( R.id.edtProductName );
        edtPrice=findViewById ( R.id.edtPrice );
        spnCategoryID=findViewById ( R.id.spinner );
        tvChup=findViewById ( R.id.tvChup );
        imageViewProduct=findViewById ( R.id.imgProduct );
        btnSave=findViewById ( R.id.btnSave );
        btnCancel=findViewById ( R.id.btnCancel );

        productID = getIntent ().getIntExtra ( "id", -1 );

        IRetrofitService service = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_URL);
        service.getAllCategories ().enqueue ( getAllCategoriesCB );

        themgiohang.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                IRetrofitService service2 = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_URL);
                cart cart = new cart ();
                cart.setImage_url ( image_url );
                cart.setProduct_name ( edtProductName.getText ().toString () );
                cart.setPrice ( Double.parseDouble ( edtPrice.getText ().toString () ) );
                service2.insertCart ( cart ).enqueue ( insertCartCB );
            }
        } );


        tvChup.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent ( MediaStore.ACTION_IMAGE_CAPTURE );
                startActivityForResult ( i, 1 );
            }
        } );
        btnCancel.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                finish ();
            }
        } );
        btnSave.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                IRetrofitService service1 = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_URL);
                Product product = new Product ();
                product.setImage_url ( image_url );
                product.setProduct_name ( edtProductName.getText ().toString () );
                product.setPrice ( Double.parseDouble ( edtPrice.getText ().toString () )  );
                product.setCategory_id (category_id);
                product.setId ( productID );
                if (productID == -1){
                    service1.insert (product).enqueue ( insert_update_CB );
                }else {
                    service1.update (product).enqueue ( insert_update_CB );
                }

                Intent i = new Intent (InsertProductActivity.this,ProductActivity2.class );
                startActivity ( i );
                finish ();
            }
        } );
        spnCategoryID.setOnItemSelectedListener ( new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> adapterView,View view,int i,long l) {
                Productcategory productcategory =(Productcategory) adapterView.getItemAtPosition ( i );
                category_id = productcategory.getId ();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        } );
    }

    Callback<Product> getByIdCB = new Callback<Product> () {
        @Override
        public void onResponse(Call<Product> call,Response<Product> response) {
            if(response.isSuccessful ()){
                model = response.body ();
                edtProductName.setText ( model.getProduct_name () );
                edtPrice.setText ( model.getPrice ()+"" );
                int index = getIndex ( data, model.getCategory_id () );
                spnCategoryID.setSelection (index);
                image_url= model.getImage_url ();
                Glide.with ( InsertProductActivity.this  )
                        .load ( model.getImage_url () ).into ( imageViewProduct );
            }else {
                Log.e("getByIdCB",response.message ());
            }
        }

        @Override
        public void onFailure(Call<Product> call,Throwable t) {
            Log.e("getByIdCB",t.getMessage ());
        }
    };


    Callback<ResponseModel>insert_update_CB = new Callback<ResponseModel> () {
        @Override
        public void onResponse(Call<ResponseModel> call,Response<ResponseModel> response) {
            if(response.isSuccessful ()){
                ResponseModel model = response.body ();
                if (model.getStatus ()){
                    finish ();
                }else {
                    Toast.makeText ( InsertProductActivity.this, "Insert Failed", Toast.LENGTH_LONG ).show ();
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

    //Cart
    Callback<ResponseModel>insertCartCB = new Callback<ResponseModel> () {
        @Override
        public void onResponse(Call<ResponseModel> call,Response<ResponseModel> response) {
            if(response.isSuccessful ()){
                ResponseModel model = response.body ();
                if (model.getStatus ()){
                    finish ();
                }else {
                    Toast.makeText ( InsertProductActivity.this, "Insert Failed", Toast.LENGTH_LONG ).show ();
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



    Callback<List<Productcategory>>getAllCategoriesCB = new Callback<List<Productcategory>> () {
        @Override
        public void onResponse(Call<List<Productcategory>> call,Response<List<Productcategory>> response) {
            if(response.isSuccessful ()){
                data = response.body ();
                adapter = new categoriesAdapter ( data, InsertProductActivity.this );
                spnCategoryID.setAdapter ( adapter );
                spnCategoryID.setSelection ( 0 );
                if (productID != -1){
                    IRetrofitService service = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_URL);
                    service.getProductById ( new Product (productID, -1, -1.0,null,null) )
                            .enqueue ( getByIdCB );
                }
            }else {
                Log.e("onRenponse",response.message ());
            }
        }

        @Override
        public void onFailure(Call<List<Productcategory>> call,Throwable t) {
            Log.e("onFaled",t.getMessage ());
        }
    };

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult ( requestCode,resultCode,data );
        if (requestCode == 1 && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras ();
            Bitmap bitmap = (Bitmap) bundle.get ("data");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream ();
            bitmap.compress ( Bitmap.CompressFormat.PNG,100,byteArrayOutputStream );
            byte[] byteArray = byteArrayOutputStream.toByteArray ();
            String encoded = Base64.encodeToString ( byteArray, Base64.DEFAULT );

            encoded = "data:image/png;base64," +encoded;
            MultipartBody.Part part = MultipartBody.Part.createFormData ( "image",encoded );
            IRetrofitService service = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_2PIK_URL);
            service.upLoad2pik (part).enqueue (uploadCB);
        }
    }

    Callback<Response2pikModel> uploadCB = new Callback<Response2pikModel> () {
        @Override
        public void onResponse(Call<Response2pikModel> call,Response<Response2pikModel> response) {
            if (response.isSuccessful ()){
                Response2pikModel model = response.body ();
                image_url = model.getSaved();
                Log.i ( "url: ",image_url );
                Glide.with ( InsertProductActivity.this  )
                        .load ( image_url ).into ( imageViewProduct );
            }else {
                Log.i("upLoadCB Error: ",response.message ());
            }
        }

        @Override
        public void onFailure(Call<Response2pikModel> call,Throwable t) {
            Log.i("onFailure Error: ",t.getMessage ());
        }
    };
    private Integer getIndex (List<Productcategory>_data, int category_id){
        for (int i = 0; i< _data.size ();i++){
            if (_data.get ( i ).getId () == category_id){
                return i;
            }
        }
        return 0;
    }
}