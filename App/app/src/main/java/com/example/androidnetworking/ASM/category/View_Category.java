package com.example.androidnetworking.ASM.category;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.androidnetworking.ASM.adapter.CategoryApdapter;
import com.example.androidnetworking.MyRetrofit.IRetrofitService;
import com.example.androidnetworking.MyRetrofit.Retrofitbuilder;
import com.example.androidnetworking.R;
import com.example.androidnetworking.model.ResponseModel;
import com.example.androidnetworking.model.category;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class View_Category extends AppCompatActivity {
    ListView listView;
    private List<category> data = new ArrayList<> ();
    private CategoryApdapter apdapter;
    private static String BASE_URL= "http://10.0.2.2:8081/views/";
    FloatingActionButton them;
    DrawerLayout drawer;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_view__category );
        listView=findViewById ( R.id.lvCategory );
        IRetrofitService service = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_URL);
        service.getCategory ().enqueue (getALLCallback);
        them=findViewById ( R.id.btnThemCategory );
        them.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent g = new Intent (View_Category.this,CategoryActivity.class);
                startActivity ( g );
            }
        } );
        listView.setOnItemLongClickListener ( new AdapterView.OnItemLongClickListener () {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView,View view,int i,long l) {
                new AlertDialog.Builder( View_Category.this)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                category category = (category) adapterView.getItemAtPosition ( i );
                                IRetrofitService service = new Retrofitbuilder ()
                                .createService ( IRetrofitService.class ,BASE_URL);
                                service.deleteCategory (category).enqueue (deleteCB);
                            }
                        })
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
        service.getCategory ().enqueue (getALLCallback);
    }


    Callback<List<category>> getALLCallback = new Callback<List<category>> () {
        @Override
        public void onResponse(Call<List<category>> call,Response<List<category>> response) {
            if (response.isSuccessful ()) {
                if (data.size ()==0){
                    data = response.body ();
                    apdapter = new CategoryApdapter (data,View_Category.this);
                    listView.setAdapter (apdapter);
                    apdapter.notifyDataSetChanged ();
                }

            } else {
                Log.i ("Error: ",response.message ());
            }
        }

        @Override
        public void onFailure(Call<List<category>> call,Throwable t) {
            Log.i ("Error: ",call.toString ());
        }
    };
    Callback<ResponseModel>deleteCB = new Callback<ResponseModel> () {
        @Override
        public void onResponse(Call<ResponseModel> call,Response<ResponseModel> response) {
            if(response.isSuccessful ()){
                ResponseModel model = response.body ();
                if (model.getStatus ()){
                    reloadScreen ();
                }else {
                    Toast.makeText ( View_Category.this, "Insert Failed", Toast.LENGTH_LONG ).show ();
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