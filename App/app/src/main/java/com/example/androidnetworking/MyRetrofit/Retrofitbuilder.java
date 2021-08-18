package com.example.androidnetworking.MyRetrofit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofitbuilder {
    private String BASE_URL= "http://10.0.2.2:8081/views/";
//    private static final Retrofit retrofit = buildRetrofit();

    private Retrofit buildRetrofit() {
        Gson gson = new GsonBuilder ()
                .setLenient ()
                .create ();
        return new Retrofit.Builder ()
                .baseUrl (BASE_URL)
                .addConverterFactory (GsonConverterFactory.create (gson))
                .build ();
    }
    public <T> T createService(Class<T> service, String url){
        BASE_URL = url;
        return buildRetrofit ().create (service);
    }

}
