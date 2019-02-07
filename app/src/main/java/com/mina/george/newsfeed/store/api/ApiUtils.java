package com.mina.george.newsfeed.store.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {

    public static final String BASE_URL = "https://newsapi.org/v2/";
    public static final String API_KEY = "de6eeab9e028498593caecbd53435a05";
    private static Retrofit retrofit;

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private final OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

    @Inject
    ApiUtils() {

    }

    public NewsApiService getNewsApiService() {
        return getUserRetrofit().create(NewsApiService.class);
    }

    private Retrofit getUserRetrofit() {
        if (ApiUtils.retrofit == null) {
            ApiUtils.retrofit = new Retrofit.Builder()
                    .baseUrl(ApiUtils.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.newThread()))
                    .client(okHttpClient)
                    .build();
        }
        return ApiUtils.retrofit;
    }

    public OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
    }

}