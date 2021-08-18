package com.example.androidnetworking.Thi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidnetworking.R;
import com.example.androidnetworking.model.book;

import java.util.List;

public class BookApdapter extends BaseAdapter {
    private List<book> data;
    private Context ctx;

    public BookApdapter(List<book> data,Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    public void updateData(List<book> data){
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
            view = View.inflate (_viewGroup.getContext (),R.layout.itembook,null);

            TextView title = (TextView)view.findViewById (R.id.tvTitle);
            TextView price = (TextView)view.findViewById (R.id.tvPrice);
            TextView author = (TextView)view.findViewById (R.id.tvAthor);
            TextView published = (TextView)view.findViewById (R.id.tvPublished);
            ViewHolder holder = new ViewHolder (title, price, author, published );
            view.setTag (holder);
        }
        ViewHolder holder = (ViewHolder)view.getTag ();
        book p = (book) getItem (_i);
        holder.Title.setText (p.getTitle ());
        holder.Price.setText (p.getPrice ()+"$");
        holder.Author.setText (p.getAuthor ());
        holder.Published.setText (p.getPublished ());
        return view;
    }
    private static class ViewHolder{
        final TextView Title, Price, Author, Published;

        public ViewHolder(TextView _Title, TextView _Price, TextView _Author, TextView _Published){
            this.Title = _Title;
            this.Price = _Price;
            this.Author = _Author;
            this.Published = _Published;
        }
    }
}
