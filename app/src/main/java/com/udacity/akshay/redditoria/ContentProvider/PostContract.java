package com.udacity.akshay.redditoria.ContentProvider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by A on 5/1/2016.
 */
public class PostContract {
    private PostContract() {}

    /**
     * Content provider authority.
     */
    public static final String CONTENT_AUTHORITY = "com.udacity.akshay.redditoria";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final String PATH_STORIES = "stories";

    public static class Post implements BaseColumns {
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.redditoria.posts";

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.redditoria.post";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_STORIES).build();

        public static final String TABLE_NAME = "story";
        public static final String DEFAULT_SORT_ORDER = Post.COLUMN_PUBLISHED + " ASC";

        public static final String COLUMN_ID = "story_id";              //id
        public static final String COLUMN_TITLE = "title";              //title text
        public static final String COLUMN_AUTHOR = "author";            //who made post
        public static final String COLUMN_URL = "url";                  //url of reddit post use to launch intent
        public static final String COLUMN_PERMALINK = "permalink";      //permalink
        public static final String COLUMN_THUMBNAIL = "thumbnail";      //thumbnail image url
        public static final String COLUMN_SCORE = "score";              //total of upvotes - downvotes
        public static final String COLUMN_PUBLISHED = "published";      //date post made by author
        public static final String COLUMN_CREATED = "created";          //
        public static final String COLUMN_NSFW = "over_18";             //nsfw
    }
}
