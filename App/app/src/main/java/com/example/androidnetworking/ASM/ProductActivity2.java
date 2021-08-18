package com.example.androidnetworking.ASM;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.androidnetworking.ASM.Cart.CartActivity2;
import com.example.androidnetworking.ASM.Loai.AccessoriesActivity;
import com.example.androidnetworking.ASM.Loai.DesktopActivity;
import com.example.androidnetworking.ASM.Loai.LaptopActivity;
import com.example.androidnetworking.ASM.Loai.MobileActivity;
import com.example.androidnetworking.ASM.Loai.TabletActivity;
import com.example.androidnetworking.ASM.Update.InsertProductActivity;
import com.example.androidnetworking.ASM.adapter.ProductApdapter;
import com.example.androidnetworking.ASM.category.View_Category;
import com.example.androidnetworking.MyRetrofit.IRetrofitService;
import com.example.androidnetworking.MyRetrofit.Retrofitbuilder;
import com.example.androidnetworking.R;
import com.example.androidnetworking.model.Product;
import com.example.androidnetworking.model.ResponseModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity2 extends AppCompatActivity {
    private ListView ListView;
    private List<Product> data = new ArrayList<> ();
    private ProductApdapter apdapter;
    private IRetrofitService service;
    private FloatingActionButton btnThem;
    private static String BASE_URL= "http://10.0.2.2:8081/views/";

    DrawerLayout drawer;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_product2);
        drawer=findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById ( R.id.my_toolbar );
        setSupportActionBar ( toolbar );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (ProductActivity2.this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        IRetrofitService service = new Retrofitbuilder ().createService ( IRetrofitService.class ,BASE_URL);
        ListView = (ListView) findViewById (R.id.listViewProduct);
        service.getAllProduct ().enqueue (getALLCallback);
//        btnThem=(FloatingActionButton)findViewById ( R.id.InsertProduct );
//        btnThem.setOnClickListener ( new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                startActivityForResult ( new Intent ( ProductActivity2.this,InsertProductActivity.class),2 );
//            }
//        } );
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
                new AlertDialog.Builder( ProductActivity2.this)
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
                                Intent i = new Intent ( ProductActivity2.this, ProductActivity2.class);
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
                        Intent t = new Intent (ProductActivity2.this,ProductActivity2.class);
                        startActivity (t);
                        break;
                    case R.id.nav:
                        Intent p = new Intent (ProductActivity2.this,LaptopActivity.class);
                        startActivity (p);
                        break;
                    case R.id.nav_trangchu:
                        Intent z = new Intent (ProductActivity2.this,MobileActivity.class);
                        startActivity (z);
                        break;
                    case R.id.nav_nike:
                        Intent i = new Intent (ProductActivity2.this,DesktopActivity.class);
                        startActivity (i);
                        break;
                    case R.id.nav_converse:
                        Intent a = new Intent (ProductActivity2.this,AccessoriesActivity.class);
                        startActivity (a);
                        break;
                    case R.id.nav_adidas:
                        Intent s = new Intent (ProductActivity2.this,TabletActivity.class);
                        startActivity (s);
                        break;

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
                    Toast.makeText ( ProductActivity2.this, "Insert Failed", Toast.LENGTH_LONG ).show ();
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
                    apdapter = new ProductApdapter (data,ProductActivity2.this);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater ();
        inflater.inflate (R.menu.them,menu);
        return super.onCreateOptionsMenu (menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case  R.id.otp_2:
                Intent a = new Intent (ProductActivity2.this,InsertProductActivity.class);
                startActivity (a);
                break;
            case  R.id.otp_3:
                Intent m = new Intent (ProductActivity2.this,CartActivity2.class);
                startActivity (m);
                break;
            case  R.id.otp_4:
                Intent t = new Intent (ProductActivity2.this,View_Category.class);
                startActivity (t);
                break;
        }
        return super.onOptionsItemSelected (item);
    }

}