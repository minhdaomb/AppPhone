package com.example.androidnetworking.lab2.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class API {
    public String get(String _url) {
        String data = "";
        try {
            URL url = new URL (_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection ();
            InputStream inputStream = connection.getInputStream ();
            BufferedReader bufferedReader =
                    new BufferedReader (new InputStreamReader (inputStream));
            String s = "";
            while ((s != null)) {
                s = bufferedReader.readLine ();
                if (s != null && data != null) data = data.concat (s);
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return data;
    }
}
