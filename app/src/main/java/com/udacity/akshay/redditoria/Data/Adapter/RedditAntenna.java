package com.udacity.akshay.redditoria.Data.Adapter;

import com.udacity.akshay.redditoria.Data.Service.Service;

import retrofit.RestAdapter;
/**
 * Created by A on 5/7/2016.
 */
public class RedditAntenna {
    private static final String ENDPOINT_URL = "https://www.reddit.com";

    /**
     * Create single Retrofit RestAdapter instance for efficient reuse.
     */
    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setEndpoint(ENDPOINT_URL)
            .build();

    /**
     * Create single ListingsService instance for efficient reuse.
     */
    private static final Service POST_SERVICE = REST_ADAPTER.create(Service.class);

    /**
     * Private constructor to prevent external instantiation.
     */
    private RedditAntenna() {}

    /**
     * Returns instance of ListingsService for use in obtaining records from network.
     */
    public static Service getListingsService() {
        return POST_SERVICE;
    }
}
