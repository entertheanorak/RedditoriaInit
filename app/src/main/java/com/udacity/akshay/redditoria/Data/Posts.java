package com.udacity.akshay.redditoria.Data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A on 3/18/2016.
 */
public class Posts {
    @SerializedName("data")
    private PostDetails postDetails;

    public PostDetails getDetails() {
        return postDetails;
    }

    public void setStoryDetails(PostDetails pD) {
        this.postDetails = pD;
    }
}
