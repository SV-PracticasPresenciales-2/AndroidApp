package com.svalero.globalfeed.model.post;

import android.util.Log;

import com.svalero.globalfeed.api.GlobalFeedApi;
import com.svalero.globalfeed.api.GlobalFeedApiInterface;
import com.svalero.globalfeed.contract.post.PostListContract;
import com.svalero.globalfeed.domain.Post;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostListModel implements PostListContract.Model {
    @Override
    public void loadAllPosts(OnLoadPostListener listener) {
        GlobalFeedApiInterface apiInterface = GlobalFeedApi.buildInstance();
        Call<List<Post>> callPosts = apiInterface.getPosts();
        Log.d("posts", "Llamada desde model");
        callPosts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.d("posts", "Llamada desde model ok");
                List<Post> postList = response.body();
                listener.onLoadPostsSuccess(postList);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("posts", "Llamada desde model error");
                Log.d("posts", t.getMessage());
                t.printStackTrace();
                String message = "Error al conseguir todos los postos";
                listener.onLoadPostsError(t.getMessage());
            }
        });
    }
    @Override
    public void loadAllPostsByUser(String username, OnLoadPostListener listener) {
        GlobalFeedApiInterface apiInterface = GlobalFeedApi.buildInstance();
        Call<List<Post>> callPosts = apiInterface.getPostsByUsername(username);
        Log.d("posts", "Llamada desde model");
        callPosts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.d("posts", "Llamada desde model ok");
                List<Post> postList = response.body();
                listener.onLoadPostsSuccess(postList);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("posts", "Llamada desde model error");
                Log.d("posts", t.getMessage());
                t.printStackTrace();
                String message = "Error al conseguir todos los postos";
                listener.onLoadPostsError(t.getMessage());
            }
        });
    }
}
