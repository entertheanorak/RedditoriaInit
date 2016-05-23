package com.udacity.akshay.redditoria.Data.Service;

import com.udacity.akshay.redditoria.Data.PostListingResponse;

import retrofit.http.GET;

/**
 * Created by A on 5/7/2016.
 */
public interface Service {
    @GET("/r/hot.json")
    PostListingResponse listHotStories();
}
