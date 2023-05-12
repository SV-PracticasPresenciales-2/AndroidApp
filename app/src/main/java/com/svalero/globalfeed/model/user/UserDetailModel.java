package com.svalero.globalfeed.model.user;

import android.util.Log;

import com.svalero.globalfeed.api.GlobalFeedApi;
import com.svalero.globalfeed.api.GlobalFeedApiInterface;
import com.svalero.globalfeed.contract.user.UserDetailContract;
import com.svalero.globalfeed.domain.User;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailModel implements UserDetailContract.Model {
    @Override
    public void getUserDetail(String username, OnUserDetailListener listener) {
        GlobalFeedApiInterface api = GlobalFeedApi.buildInstance();
        Call<User> userCall = api.getUserByUsername(username);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() == null) {
                    listener.onUserDetailError("Error al conseguir el usuario");
                } else {
                    Log.d("llamada usuario", String.valueOf(response.body()));
                    listener.onUserDetailSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                listener.onUserDetailError("Error al conseguir el usuario");
            }
        });
    }
}
