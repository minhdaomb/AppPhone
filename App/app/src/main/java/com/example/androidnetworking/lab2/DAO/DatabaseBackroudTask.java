package com.example.androidnetworking.lab2.DAO;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.androidnetworking.R;
import com.example.androidnetworking.lab2.Backroudtask.BackroudTaskDAO;
import com.example.androidnetworking.lab2.Database.API;

public class DatabaseBackroudTask extends AsyncTask<String, Integer, String> {
    private Activity contextParent;
    private String data;
    public DatabaseBackroudTask(Activity contextParent){
        this.contextParent=contextParent;
    }

    @Override
    protected String doInBackground(String... strings) {
        BackroudTaskDAO dao = new BackroudTaskDAO ();
        return dao.get (strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute (s);
        //hien thi du lieu len textview
        TextView tv1 = (TextView)contextParent.findViewById (R.id.tv1);
        tv1.setText (s);
    }
}
