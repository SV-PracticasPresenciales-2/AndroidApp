package com.svalero.globalfeed.model.user;

import com.svalero.globalfeed.api.GlobalFeedApi;
import com.svalero.globalfeed.api.GlobalFeedApiInterface;
import com.svalero.globalfeed.contract.user.RegisterUserContract;
import com.svalero.globalfeed.domain.User;
import com.svalero.globalfeed.domain.dto.UserDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUserModel implements RegisterUserContract.Model {
    @Override
    public void registerUser(UserDTO user, OnRegisterUserListener listener) {
        GlobalFeedApiInterface api = GlobalFeedApi.buildInstance();
        Call<User> userCall = api.register(user);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user1 = response.body();
                listener.onRegisterUserSuccess(user1);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                String message = "Error al crear usero";
                listener.onRegisterUserError(message);
            }
        });

    }
}
