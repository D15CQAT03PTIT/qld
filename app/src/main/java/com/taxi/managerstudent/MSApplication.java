package com.taxi.managerstudent;

import androidx.multidex.MultiDexApplication;
import com.google.gson.Gson;
import com.taxi.managerstudent.network.EndPoint;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MSApplication extends MultiDexApplication {
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private Gson mGSon;
    private static MSApplication mSelf;

    @Override
    public void onCreate() {
        super.onCreate();
        mSelf = this;
        mGSon = new Gson();

       // FacebookSdk.sdkInitialize(this);
        okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectTimeout(100000, TimeUnit.SECONDS)
                .readTimeout(100000, TimeUnit.SECONDS)
                .writeTimeout(100000, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(EndPoint.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public static MSApplication self() {
        return mSelf;
    }

    public Gson getGSon() {
        return mGSon;
    }
}
