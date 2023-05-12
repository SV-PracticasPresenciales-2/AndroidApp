package com.svalero.globalfeed.api;


import static com.svalero.globalfeed.util.Constants.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GlobalFeedApi {
    public static GlobalFeedApiInterface buildInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GlobalFeedApiInterface.class);
    }
}
