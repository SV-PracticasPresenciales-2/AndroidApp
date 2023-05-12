package com.svalero.globalfeed.api;

import com.svalero.storeapp.domain.Inventory;
import com.svalero.storeapp.domain.InventoryMapDTO;
import com.svalero.storeapp.domain.Person;
import com.svalero.storeapp.domain.PersonLogin;
import com.svalero.storeapp.domain.Product;
import com.svalero.storeapp.domain.Review;
import com.svalero.storeapp.domain.ReviewDTO;
import com.svalero.storeapp.domain.Token;

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
    //Products
    @GET("products")
    Call<List<Product>> getProducts();

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") long id);

    @POST("products")
    Call<Product> addProduct(@Body Product product);

    @PUT("products/{id}")
    Call<Product> editProduct(@Path("id")long id, @Body Product product);

    @DELETE("products/{id}")
    Call<Void> deleteProduct(@Path("id") long id);


    //Reviews
    @GET("reviews")
    Call<List<Review>> getReviews();

    @GET("reviews/{id}")
    Call<Review> getReview(@Path("id") long id);

    @GET("reviews/")
    Call<List<Review>> getReviewsByProduct(@Query("productId") long id);

    @POST("reviews")
    Call<Review> addReview(@Body ReviewDTO review);

    @PUT("reviews/{id}")
    Call<Review> editReview(@Path("id")long id, @Body Review review);

    @DELETE("reviews/{id}")
    Call<Void> deleteReview(@Path("id") long id);

    @POST("token")
    Call<Token> getToken(@Body PersonLogin personLogin);

    //Person
    @GET("persons")
    Call<List<Person>> getPersonByUsername(@Query("username") String username);

    //Inventory
    @GET("inventories")
    Call<List<Inventory>> getAllInventories();

    @POST("inventories/{id}/waypoint")
    Call<Inventory> addWaypoint(@Path("id")long id, @Body InventoryMapDTO inventoryMapDTO);
}
