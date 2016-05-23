package com.udacity.akshay.redditoria.ContentProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by A on 4/30/2016.
 */
public class Database extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "post.db";
    /**
     * SQL DDL statement to create "story" table.
     */
    private static final String SQL_CREATE_STORIES =
            "CREATE TABLE " + PostContract.Post.TABLE_NAME + " (" +
                    PostContract.Post._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    PostContract.Post.COLUMN_ID + " TEXT UNIQUE," +
                    PostContract.Post.COLUMN_TITLE + " TEXT," +
                    PostContract.Post.COLUMN_AUTHOR + " TEXT," +
                    PostContract.Post.COLUMN_URL + " TEXT," +
                    PostContract.Post.COLUMN_PERMALINK + " TEXT," +
                    PostContract.Post.COLUMN_THUMBNAIL + " TEXT," +
                    PostContract.Post.COLUMN_NSFW + " BOOLEAN," +
                    PostContract.Post.COLUMN_SCORE + " INTEGER," +
                    PostContract.Post.COLUMN_PUBLISHED + " INTEGER," +
                    PostContract.Post.COLUMN_CREATED + "INTEGER DEFAULT current_timestamp)";

    /**
     * SQL DDL statement to drop "story" table.
     */
    private static final String SQL_DELETE_STORIES =
            "DROP TABLE IF EXISTS " + PostContract.Post.TABLE_NAME;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_STORIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over.
        db.execSQL(SQL_DELETE_STORIES);
        onCreate(db);
    }
}