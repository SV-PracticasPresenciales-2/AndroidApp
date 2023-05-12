package com.svalero.globalfeed.api;

import com.svalero.globalfeed.domain.Post;
import com.svalero.globalfeed.domain.User;
import com.svalero.globalfeed.domain.dto.UserDTO;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GlobalFeedApiInterface {
    //Posts
    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") long id);

    @POST("posts")
    Call<Post> addPost(@Body Post post);

    @PUT("posts/{id}")
    Call<Post> editPost(@Path("id")long id, @Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") long id);


    //Users
    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{id}")
    Call<User> getUser(@Path("id") long id);

    @GET("users/")
    Call<List<User>> getUsersByPost(@Query("postId") long id);

    @POST("users")
    Call<User> addUser(@Body UserDTO user);

    @PUT("users/{id}")
    Call<User> editUser(@Path("id")long id, @Body User user);

    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") long id);

    //todo
    /*
    @POST("token")
    Call<Token> getToken(@Body PersonLogin personLogin);
*/
}
