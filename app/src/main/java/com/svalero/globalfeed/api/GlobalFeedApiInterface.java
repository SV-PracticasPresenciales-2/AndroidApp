package com.svalero.globalfeed.api;

import com.svalero.globalfeed.domain.PersonLogin;
import com.svalero.globalfeed.domain.Post;
import com.svalero.globalfeed.domain.Token;
import com.svalero.globalfeed.domain.User;
import com.svalero.globalfeed.domain.dto.PostDTO;
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

    @GET("posts")
    Call<List<Post>> getPostsByUsername(@Query("username") String username);

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") long id);

    @POST("post")
    Call<Post> addPost(@Body PostDTO post);

    @PUT("post/{id}")
    Call<Post> editPost(@Path("id") long id, @Body PostDTO post);

    @DELETE("post/{id}")
    Call<Void> deletePost(@Path("id") long id);


    //Users
    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{id}")
    Call<User> getUser(@Path("id") long id);

    @GET("users")
    Call<List<User>> getUsersByPost(@Query("postId") long id);

    @GET("user")
    Call<User> getUserByUsername(@Query("username") String username);

    @POST("user")
    Call<User> addUser(@Body UserDTO user);

    @PUT("user/{id}")
    Call<User> editUser(@Path("id") long id, @Body User user);

    @DELETE("user/{id}")
    Call<Void> deleteUser(@Path("id") long id);


    @POST("token")
    Call<Token> getToken(@Body PersonLogin personLogin);

    @POST("register")
    Call<User> register(@Body UserDTO user);
}
