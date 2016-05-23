package com.udacity.akshay.redditoria.Sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import com.udacity.akshay.redditoria.ContentProvider.PostContract;
import com.udacity.akshay.redditoria.Data.Adapter.RedditAntenna;
import com.udacity.akshay.redditoria.Data.PostListingResponse;

/**
 * Created by A on 5/7/2016.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {

    public static final String TAG = SyncAdapter.class.getSimpleName();

    /**
     * Define a variable to contain a content resolver instance.
     */
    private ContentResolver mContentResolver;

    /**
     * Initialize the sync adapter.
     */
    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mContentResolver = context.getContentResolver();
    }

    /**
     * Initialize the sync adapter. This form of the constructor maintains compatibility with
     * Android 3.0 and later platform versions.
     */

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        mContentResolver = context.getContentResolver();
    }

    /**
     * Specify the code you want to run in the sync adapter. The entire sync adapter runs in a
     * background thread, so you don't have to set up your own background processing.
     */
    @Override
    public void onPerformSync(Account account, Bundle extras, String authority,
                              ContentProviderClient provider, SyncResult syncResult) {
        Log.d(TAG, "***** onPerformSync()");

        PostListingResponse hotStoriesResponse = RedditAntenna.getListingsService().listHotStories();
        if (hotStoriesResponse != null) {
            try {
                Log.d(TAG, "***** Attempt to insert/update stories: " + hotStoriesResponse.getData().getPosts().size());
                provider.bulkInsert(PostContract.Post.CONTENT_URI, hotStoriesResponse.getStoryContentValues());
            } catch (RemoteException e) {
                Log.d(TAG, Log.getStackTraceString(e));
            }
        } else {
            Log.d(TAG, "***** No stories returned from web service");
        }
    }
}
