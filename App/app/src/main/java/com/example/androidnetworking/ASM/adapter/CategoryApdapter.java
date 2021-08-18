package com.example.androidnetworking.ASM.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidnetworking.R;
import com.example.androidnetworking.model.category;

import java.util.List;

public class CategoryApdapter extends BaseAdapter {
    private List<category> data;
    private Context ctx;

    public CategoryApdapter(List<category> data,Context ctx) {
        this.data = data;
        this.ctx = ctx;
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
            view = View.inflate (_viewGroup.getContext (),R.layout.item_category,null);

            TextView name = (TextView)view.findViewById (R.id.tvCate);

            ViewHolder holder = new ViewHolder (name);
            view.setTag (holder);
        }
        ViewHolder holder = (ViewHolder)view.getTag ();
        category p = (category) getItem (_i);
        holder.category_name.setText (p.getCategory_name ());
        return view;
    }
    private static class ViewHolder{
        final TextView category_name;

        public ViewHolder(TextView _name){
            this.category_name = _name;

        }
    }
}
