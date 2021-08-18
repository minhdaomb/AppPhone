package com.example.androidnetworking.ASM;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidnetworking.ASM.Update.InsertProductActivity;
import com.example.androidnetworking.ASM.adapter.ProductApdapter;
import com.example.androidnetworking.MyRetrofit.IRetrofitService;
import com.example.androidnetworking.MyRetrofit.Retrofitbuilder;
import com.example.androidnetworking.R;
import com.example.androidnetworking.model.Product;
import com.example.androidnetworking.model.ResponseModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {
    private ListView ListView;
    private List<Product> data = new ArrayList<> ();
    private ProductApdapter apdapter;
    private IRetrofitService service;
    private FloatingActionButton btnThem;
    private static String BASE_URL= "http://10.0.2.2:8081/views/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_product);
//        androidx.appcompat.widget.Toolbar toolbar=findViewById (R.id.my_toolbar);
//        setSupportActionBar (toolbar);

        IRetrofitService service = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_URL);
        ListView = (ListView) findViewById (R.id.listViewProduct);
        service.getAllProduct ().enqueue (getALLCallback);
        btnThem=(FloatingActionButton)findViewById ( R.id.InsertProduct );
        btnThem.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivityForResult ( new Intent (ProductActivity.this,InsertProductActivity.class),2 );
            }
        } );
        ListView.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view,int i,long l) {
                Product p = (Product) adapterView.getItemAtPosition ( i );
                Intent iz =new Intent (getBaseContext (),InsertProductActivity.class);
                iz.putExtra ( "id",p.getId () );
                startActivity ( iz );
            }
        } );
        ListView.setOnItemLongClickListener ( new AdapterView.OnItemLongClickListener () {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView,View view,int i,long l) {
                new AlertDialog.Builder(ProductActivity.this)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                Product p = (Product) adapterView.getItemAtPosition ( i );
                                IRetrofitService service2 = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_URL);
                                service2.delete (p).enqueue (deleteCB);
                                Intent i = new Intent (ProductActivity.this, ProductActivity.class);
                                startActivity ( i );
                                finish ();
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


                return true;
            }
        } );
    }

    @Override
    protected void onResume() {
        super.onResume ();
        reloadScreen ();
    }
    private void reloadScreen(){
        IRetrofitService service = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_URL);
        service.getAllProduct ().enqueue (getALLCallback);
    }

    Callback<ResponseModel>deleteCB = new Callback<ResponseModel> () {
        @Override
        public void onResponse(Call<ResponseModel> call,Response<ResponseModel> response) {
            if(response.isSuccessful ()){
                ResponseModel model = response.body ();
                if (model.getStatus ()){
                    reloadScreen ();
                }else {
                    Toast.makeText ( ProductActivity.this, "Insert Failed", Toast.LENGTH_LONG ).show ();
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


    final Callback<List<Product>> getALLCallback = new Callback<List<Product>> () {
        @Override
        public void onResponse(Call<List<Product>> call,Response<List<Product>> response) {
            if (response.isSuccessful ()) {
                if (data.size ()==0){
                    data = response.body ();
                    apdapter = new ProductApdapter (data,ProductActivity.this);
                    ListView.setAdapter (apdapter);

                    apdapter.notifyDataSetChanged ();

                }else {
                    data.clear ();
                    data.addAll ( response.body () );
                    apdapter.notifyDataSetChanged ();
                }

            } else {
                Log.i ("Error: ",response.message ());
            }
        }

        @Override
        public void onFailure(Call<List<Product>> call,Throwable t) {
            Log.i ("Error: ",call.toString ());
        }
    };

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater=getMenuInflater ();
//        inflater.inflate (R.menu.them,menu);
//        return super.onCreateOptionsMenu (menu);
//    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.opt_1:
//                Intent i = new Intent (ProductActivity.this,InsertProductActivity.class);
//                startActivity (i);
//                break;
//            case  R.id.otp_2:
//                Intent a = new Intent (ProductActivity.this,InsertProductActivity.class);
//                startActivity (a);
//                break;
////            case R.id.opt_3:
////                Intent m = new Intent (HomeActivity.this,CartActivity.class);
////                startActivity (m);
////                break;
//        }
//        return super.onOptionsItemSelected (item);
//    }
}