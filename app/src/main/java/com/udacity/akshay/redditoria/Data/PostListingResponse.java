package com.udacity.akshay.redditoria.Data;

import android.content.ContentValues;

import com.udacity.akshay.redditoria.ContentProvider.PostContract;
import com.udacity.akshay.redditoria.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A on 5/7/2016.
 */
public class PostListingResponse {
    private PostListingData data;

    public PostListingData getData() {
        return data;
    }

    public void setData(PostListingData data) {
        this.data = data;
    }

    /**
     * Utility method to transform collection of Stories to array of ContentValues.
     */
    public ContentValues[] getStoryContentValues() {
        List<ContentValues> contentValues = new ArrayList<ContentValues>();
        for(Posts p : getData().getPosts()) {
            ContentValues postValues = new ContentValues();
            if(p.getDetails().getNSFW()){
                postValues.put(PostContract.Post.COLUMN_ID, p.getDetails().getId());
                postValues.put(PostContract.Post.COLUMN_TITLE, "Over 18");
                postValues.put(PostContract.Post.COLUMN_AUTHOR, p.getDetails().getAuthor());
                postValues.put(PostContract.Post.COLUMN_URL, R.string.nsfw);
                postValues.put(PostContract.Post.COLUMN_PERMALINK,R.string.nsfw);
                postValues.put(PostContract.Post.COLUMN_THUMBNAIL, R.string.nsfw);
                postValues.put(PostContract.Post.COLUMN_NSFW, p.getDetails().getNSFW());
                postValues.put(PostContract.Post.COLUMN_SCORE, p.getDetails().getScore());
                postValues.put(PostContract.Post.COLUMN_PUBLISHED, p.getDetails().getPublished());
            }
            else {
                postValues.put(PostContract.Post.COLUMN_ID, p.getDetails().getId());
                postValues.put(PostContract.Post.COLUMN_TITLE, p.getDetails().getTitle());
                postValues.put(PostContract.Post.COLUMN_AUTHOR, p.getDetails().getAuthor());
                postValues.put(PostContract.Post.COLUMN_URL, p.getDetails().getUrl());
                postValues.put(PostContract.Post.COLUMN_PERMALINK, p.getDetails().getPermalink());
                postValues.put(PostContract.Post.COLUMN_THUMBNAIL, p.getDetails().getThumbnail());
                postValues.put(PostContract.Post.COLUMN_NSFW, p.getDetails().getNSFW());
                postValues.put(PostContract.Post.COLUMN_SCORE, p.getDetails().getScore());
                postValues.put(PostContract.Post.COLUMN_PUBLISHED, p.getDetails().getPublished());
            }
            contentValues.add(postValues);
        }
        return contentValues.toArray(new ContentValues[contentValues.size()]);
    }
}
