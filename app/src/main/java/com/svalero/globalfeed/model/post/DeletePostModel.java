package com.svalero.globalfeed.model.post;

import com.svalero.globalfeed.api.GlobalFeedApiInterface;
import com.svalero.globalfeed.api.GlobalFeedSecureApi;
import com.svalero.globalfeed.contract.post.DeletePostContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeletePostModel implements DeletePostContract.Model {
    @Override
    public void deletePost(String token, long id, OnDeletePostListener listener) {
        GlobalFeedApiInterface api = GlobalFeedSecureApi.buildInstance(token);
        Call<Void> call = api.deletePost(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 418){
                    listener.onDeletePostError("Error al borrar el posto");
                } else {
                    listener.onDeletePostSuccess("Posto eliminado correctamente!");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                listener.onDeletePostError("Error al borrar el posto");
            }
        });
    }
}
