package com.mukhtarinc.dredge.data.remote;

import com.mukhtarinc.movieapp.BuildConfig;
import com.mukhtarinc.dredge.common.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {



    public  <T> T createService(Class<T> serviceClass){

        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.getBaseUrl());

        OkHttpClient.Builder hBuilder1 = new OkHttpClient.Builder()
                .readTimeout(90, TimeUnit.SECONDS)
                .connectTimeout(90,TimeUnit.SECONDS)
                .writeTimeout(90,TimeUnit.SECONDS)
                .cache(null);


        if (BuildConfig.DEBUG){

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            hBuilder1.addInterceptor(interceptor);
        }

        builder.client(hBuilder1.build());
        Retrofit retrofit = builder.build();

        return retrofit.create(serviceClass);

    }
}
