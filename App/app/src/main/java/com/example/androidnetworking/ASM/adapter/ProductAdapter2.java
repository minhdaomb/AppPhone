package com.example.androidnetworking.ASM.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidnetworking.R;
import com.example.androidnetworking.model.Person;
import com.example.androidnetworking.model.Product;

import java.util.List;

public class ProductAdapter2 extends RecyclerView.Adapter<ProductAdapter2.PersonViewHolder> {
    List<Product> data;
    Context context;

    public ProductAdapter2(List<Product> data,Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.item, parent, false);
        return new PersonViewHolder (view);
    }

    private void dialogsua(Product product){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater ();
        View view = inflater.inflate(R.layout.activity_insert,null);
        builder.setView(view);

        final EditText edtProductName= view.findViewById ( R.id.edtProductName );
        final EditText edtPrice=view.findViewById ( R.id.edtPrice );
        final Spinner spnCategoryID=view.findViewById ( R.id.spinner );
        final TextView tvChup=view.findViewById ( R.id.tvChup );
        final ImageView imageViewProduct=view.findViewById ( R.id.imgProduct );
        final Button btnSave=view.findViewById ( R.id.btnSave );
        final Button btnCancel=view.findViewById ( R.id.btnCancel );

        edtProductName.setText ( product.getProduct_name () );

    }


    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder,int position) {
        Product p= data.get (position);
        holder.txtUserName.setText (p.getProduct_name ());
        holder.txtSDT.setText (p.getPrice ()+"");
        Glide.with (context).load (p.getImage_url ()).into (holder.images);
        holder.itemRCV.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                dialogsua ( data.get ( position ) );
            }
        } );
    }

    @Override
    public int getItemCount() {
        return data.size ();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder{
        TextView txtUserName,txtSDT;
        CardView itemRCV;
        ImageView images, delete;
        public PersonViewHolder(@NonNull View itemView) {
            super (itemView);
            txtUserName= itemView.findViewById (R.id.tvNameProduct);
            txtSDT = itemView.findViewById (R.id.tvPrice);
            images= itemView.findViewById ( R.id.images );
//            delete = itemView.findViewById ( R.id.del );
            itemRCV = itemView.findViewById ( R.id.itemRCV );

        }
    }
}
