package com.example.androidnetworking.lab3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidnetworking.R;
import com.example.androidnetworking.model.Person;
import com.example.androidnetworking.model.Product;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    List<Product> PersonList;
    Context context;
    public PersonAdapter(Context context,List<Product> personn){
        this.context=context;
        PersonList =  personn;
    }
    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.item, parent, false);
        return new PersonViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder,int position) {
        Product person= PersonList.get (position);
        holder.txtUserName.setText (person.getProduct_name ());
        holder.txtSDT.setText (person.getPrice ()+"");
    }

    @Override
    public int getItemCount() {
        return PersonList.size ();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder{
        TextView txtUserName,txtSDT;
        public PersonViewHolder(@NonNull View itemView) {
            super (itemView);
            txtUserName= itemView.findViewById (R.id.tvNameProduct);
            txtSDT = itemView.findViewById (R.id.tvPrice);

        }
    }
}
