package com.example.androidnetworking.Thi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.androidnetworking.MyRetrofit.IRetrofitService;
import com.example.androidnetworking.MyRetrofit.Retrofitbuilder;
import com.example.androidnetworking.R;
import com.example.androidnetworking.model.ResponseModel;
import com.example.androidnetworking.model.book;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookActivity extends AppCompatActivity {
    private ListView ListView;
    private List<book> data = new ArrayList<> ();
    private BookApdapter apdapter;
    private IRetrofitService service;
    private FloatingActionButton btnThem;
    private static String BASE_URL= "http://10.0.2.2:8081/views/";

    DrawerLayout drawer;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.book);
        drawer=findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById ( R.id.my_toolbar );
        setSupportActionBar ( toolbar );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                ( BookActivity.this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        IRetrofitService service = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_URL);
        ListView = (ListView) findViewById (R.id.lvBook);
        service.getAllBook ().enqueue (getALLCallback);

        ListView.setOnItemLongClickListener ( new AdapterView.OnItemLongClickListener () {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView,View view,int i,long l) {
                new AlertDialog.Builder( BookActivity.this)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                book book = (book) adapterView.getItemAtPosition ( i );
                                IRetrofitService service2 = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_URL);
                                service2.deleteBook (book).enqueue (deleteCB);
                                Intent i = new Intent ( BookActivity.this, BookActivity.class);
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


        navigationView.setNavigationItemSelectedListener (new NavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId ()){
                    case R.id.nav1:
                        Intent t = new Intent ( BookActivity.this,BookActivity.class);
                        startActivity (t);
                        break;
//                    case R.id.nav:
//                        Intent p = new Intent ( BookActivity.this,LaptopActivity.class);
//                        startActivity (p);
//                        break;
//                    case R.id.nav_trangchu:
//                        Intent z = new Intent ( BookActivity.this,MobileActivity.class);
//                        startActivity (z);
//                        break;
//                    case R.id.nav_nike:
//                        Intent i = new Intent ( BookActivity.this,DesktopActivity.class);
//                        startActivity (i);
//                        break;

                }
                navigationView.setCheckedItem (item.getItemId ());
                drawer.closeDrawer ( GravityCompat.START);
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume ();
        reloadScreen ();
    }
    private void reloadScreen(){
        IRetrofitService service = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_URL);
        service.getAllBook ().enqueue (getALLCallback);
    }

    Callback<ResponseModel>deleteCB = new Callback<ResponseModel> () {
        @Override
        public void onResponse(Call<ResponseModel> call,Response<ResponseModel> response) {
            if(response.isSuccessful ()){
                ResponseModel model = response.body ();
                if (model.getStatus ()){
                    reloadScreen ();
                }else {
                    Toast.makeText ( BookActivity.this, "Insert Failed", Toast.LENGTH_LONG ).show ();
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


    final Callback<List<book>> getALLCallback = new Callback<List<book>> () {
        @Override
        public void onResponse(Call<List<book>> call,Response<List<book>> response) {
            if (response.isSuccessful ()) {
                if (data.size ()==0){
                    data = response.body ();
                    apdapter = new BookApdapter (data,BookActivity.this);
                    ListView.setAdapter (apdapter);


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
        public void onFailure(Call<List<book>> call,Throwable t) {
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
//            case  R.id.otp_2:
//                Intent a = new Intent ( BookActivity.this,InsertProductActivity.class);
//                startActivity (a);
//                break;
//            case  R.id.otp_3:
//                Intent m = new Intent ( BookActivity.this,BookActivity.class);
//                startActivity (m);
//                break;
//        }
//        return super.onOptionsItemSelected (item);
//    }

}