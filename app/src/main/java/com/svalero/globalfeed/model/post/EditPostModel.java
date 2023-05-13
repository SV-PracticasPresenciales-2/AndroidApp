package com.svalero.globalfeed.model.post;

import com.svalero.globalfeed.api.GlobalFeedApiInterface;
import com.svalero.globalfeed.api.GlobalFeedSecureApi;
import com.svalero.globalfeed.contract.post.EditPostContract;
import com.svalero.globalfeed.domain.Post;
import com.svalero.globalfeed.domain.dto.PostDTO;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPostModel implements EditPostContract.Model {
    @Override
    public void editPost(String token, long postId, PostDTO post, OnEditPostListener listener) {
        GlobalFeedApiInterface api = GlobalFeedSecureApi.buildInstance(token);
        Call<Post> postCall = api.editPost(postId, post);
        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                listener.onEditPostSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                t.printStackTrace();
                String message = "No se ha podido editar el posto";
                listener.onEditPostError(message);
            }
        });
    }
}
