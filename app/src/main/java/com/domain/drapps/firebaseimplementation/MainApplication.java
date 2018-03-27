package com.domain.drapps.firebaseimplementation;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by diogo.rosa on 21/03/2018.
 */

public class MainApplication extends Application {

    public ServiceInterface service;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        if (service == null) {
            service = getNewService();
        }
        context = getApplicationContext();
    }

    public static ServiceInterface getNewService(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .client(httpClient.build())
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(
                        new Persister(new AnnotationStrategy())))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        ServiceInterface service = retrofit.create(ServiceInterface.class);
        return service;
    }

    public ServiceInterface getService() {
        return service;
    }

    public static Context getContext(){
        return context;
    }
}
