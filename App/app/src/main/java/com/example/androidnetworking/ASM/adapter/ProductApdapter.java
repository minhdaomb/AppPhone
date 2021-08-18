package com.example.androidnetworking.ASM.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidnetworking.R;
import com.example.androidnetworking.model.Product;

import java.util.List;

public class ProductApdapter extends BaseAdapter {
    private List<Product> data;
    private Context ctx;

    public ProductApdapter(List<Product> data,Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    public void updateData(List<Product> data){
        data.clear ();
        data.addAll ( data );
        notifyDataSetChanged ();
    }
    @Override
    public int getCount() {
        return data.size ();
    }

    @Override
    public Object getItem(int i) {
        return data.get (i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int _i,View _view,ViewGroup _viewGroup) {
        View view=_view;
        if (view==null){
            view = View.inflate (_viewGroup.getContext (),R.layout.item,null);

            TextView name = (TextView)view.findViewById (R.id.tvNameProduct);
            TextView price = (TextView)view.findViewById (R.id.tvPrice);
            ImageView image = (ImageView)view.findViewById (R.id.images);

            ViewHolder holder = new ViewHolder (name , price , image);
            view.setTag (holder);
        }
        ViewHolder holder = (ViewHolder)view.getTag ();
        Product p = (Product) getItem (_i);
        holder.ProductName.setText (p.getProduct_name ());
        holder.Price.setText (p.getPrice ()+"$");

        Glide.with (ctx).load (p.getImage_url ()).into (holder.images);


        return view;
    }
    private static class ViewHolder{
        final TextView ProductName, Price;
        final ImageView images;
        public ViewHolder(TextView _name, TextView _price,ImageView images){
            this.ProductName = _name;
            this.Price = _price;
            this.images = images;
        }
    }
}
