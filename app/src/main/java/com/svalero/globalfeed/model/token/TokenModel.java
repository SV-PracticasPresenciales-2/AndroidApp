package com.svalero.globalfeed.model.token;

import android.media.session.MediaSession;
import android.util.Log;

import com.svalero.globalfeed.api.GlobalFeedApi;
import com.svalero.globalfeed.api.GlobalFeedApiInterface;
import com.svalero.globalfeed.contract.token.TokenContract;
import com.svalero.globalfeed.domain.PersonLogin;
import com.svalero.globalfeed.domain.Token;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokenModel implements TokenContract.Model {
    @Override
    public void loadToken(PersonLogin personLogin, OnLoadTokenListener listener) {
        GlobalFeedApiInterface apiInterface = GlobalFeedApi.buildInstance();
        Call<Token> callToken = apiInterface.getToken(personLogin);
        Log.d("token", "Llamada desde model");
        callToken.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Log.d("token", "Llamada desde model ok");
                Token token = response.body();
                listener.onLoadTokenSuccess(token);
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.d("product", "Llamada desde model error");
                Log.d("product", t.getMessage());
                t.printStackTrace();
                String message = "Error iniciando sesion";
                listener.onLoadTokenError(t.getMessage());
            }
        });
    }
}
