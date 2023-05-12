package com.svalero.globalfeed.api;


import static com.svalero.globalfeed.util.Constants.BASE_URL;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GlobalFeedSecureApi {
    public static GlobalFeedApiInterface buildInstance(String token){
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request.Builder requestBuilder = original.newBuilder()
                                .header("Authorization", "Bearer " + token);
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GlobalFeedApiInterface.class);
    }
}
