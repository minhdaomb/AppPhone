package com.example.androidnetworking.lab2.Backroudtask;

import com.example.androidnetworking.lab2.DAO.InterfaceBackroudTask;
import com.example.androidnetworking.lab2.Database.API;

public class BackroudTaskDAO implements InterfaceBackroudTask {
    @Override
    public String get(String url){
        API api = new API();
        String data = api.get (url);
        return data;
    }
}
