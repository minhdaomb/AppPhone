package com.example.androidnetworking.lab7.adapter;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.androidnetworking.R;
import com.example.androidnetworking.lab7.model.Message;

import java.util.List;

public class messageAdapter extends BaseAdapter {
    List<Message> data;
    Context context;

    public messageAdapter(List<Message> data,Context context) {
        this.data = data;
        this.context = context;
    }
    public void updateData(List<Message> data){
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
        return data.get ( i ) ;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View getView(int _i,View _view,ViewGroup _viewGroup) {
        View view=_view;
        if (view==null){
            view = View.inflate (_viewGroup.getContext (),R.layout.layout_item_message,null);
            TextView msg = (TextView)view.findViewById (R.id.tvMessage);
            ViewHolder holder = new ViewHolder (msg);
            view.setTag (holder);
        }
        ViewHolder holder = (ViewHolder)view.getTag ();
        Message m = (Message) getItem (_i);
        holder.msg.setText (m.getData ());
        if(m.getFromMe ()){
            holder.msg.setTextAlignment ( View.TEXT_ALIGNMENT_TEXT_END );
        }else {
            holder.msg.setTextAlignment ( View.TEXT_ALIGNMENT_TEXT_START );
        }

        return view;
    }
    private static class ViewHolder{
        final TextView msg;
        public ViewHolder(TextView msg){
            this.msg = msg;

        }
    }
}
