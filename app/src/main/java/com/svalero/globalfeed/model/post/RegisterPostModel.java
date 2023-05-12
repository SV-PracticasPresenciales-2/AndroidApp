package com.svalero.globalfeed.model.post;

import com.svalero.globalfeed.api.GlobalFeedApi;
import com.svalero.globalfeed.api.GlobalFeedApiInterface;
import com.svalero.globalfeed.api.GlobalFeedSecureApi;
import com.svalero.globalfeed.contract.post.RegisterPostContract;
import com.svalero.globalfeed.domain.Post;
import com.svalero.globalfeed.domain.dto.PostDTO;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPostModel implements RegisterPostContract.Model {
    @Override
    public void registerPost(String token, PostDTO post, RegisterPostContract.Model.OnRegisterPostListener listener) {
        GlobalFeedApiInterface api = GlobalFeedSecureApi.buildInstance(token);
        Call<Post> postCall = api.addPost(post);
        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post post1 = response.body();
                listener.onRegisterPostSuccess(post1);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                t.printStackTrace();
                String message = "Error al crear posto";
                listener.onRegisterPostError(message);
            }
        });

    }
}
