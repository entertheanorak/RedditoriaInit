package com.udacity.akshay.redditoria.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by A on 5/7/2016.
 */
public class PostListingData {
    @SerializedName("children")
    private List<Posts> posts;

    public List<Posts> getPosts() {
        return posts;
    }

    public void setStories(List<Posts> p) {
        this.posts = p;
    }
}
