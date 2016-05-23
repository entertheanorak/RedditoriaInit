package com.udacity.akshay.redditoria.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by A on 3/26/2016.
 */

/*public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "BookDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_BOOK_TABLE = "CREATE TABLE books ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, "+
                "author TEXT )";

        // create books table
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS books");

        // create fresh books table
        this.onCreate(db);
    }

}*/
public class PostProvider extends ContentProvider{
    public static final String TAG = PostProvider.class.getSimpleName();

    public static final int POSTS_ROUTE = 1;
    public static final int POST_IDS_ROUTE = 2;
    private Database mPostDatabaseHelper;


    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(PostContract.CONTENT_AUTHORITY, "posts", POSTS_ROUTE);
        sUriMatcher.addURI(PostContract.CONTENT_AUTHORITY, "posts/*", POST_IDS_ROUTE);
    }

    /**
     * Initialize content provider on startup. This method is called for all registered content
     * providers on the application main thread at application launch time.
     */
    @Override
    public boolean onCreate() {
        mPostDatabaseHelper = new Database(getContext());
        return true;
    }

    /**
     * Handles requests for the MIME type of the data at the given URI.
     */
    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case POSTS_ROUTE:
                return PostContract.Post.CONTENT_TYPE;
            case POST_IDS_ROUTE:
                return PostContract.Post.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }
    }

    /**
     * Handles query requests from clients.
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        Cursor cursor;

        switch (sUriMatcher.match(uri)) {
            case POST_IDS_ROUTE:
                // Query database for single story.
                String id = uri.getLastPathSegment();
                // TODO: evaluate better/safer method of building query where clause.
                if (TextUtils.isEmpty(selection)) {
                    selection = PostContract.Post._ID + "=" + id;
                }
                else {
                    selection += " AND " + PostContract.Post._ID + "=" + id;
                }
            case POSTS_ROUTE:
                // Query database for all stories.

                // If no sort order specified/passed, use the default.
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = PostContract.Post.DEFAULT_SORT_ORDER;
                }

                // Perform query and notify URI observers.
                queryBuilder.setTables(PostContract.Post.TABLE_NAME);
                cursor = queryBuilder.query(getDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }
    }

    /**
     * STUB - Functionality not yet implemented.
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    /**
     * STUB - Functionality not yet implemented.
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * STUB - Functionality not yet implemented.
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * Handles requests to insert a set of rows.
     */
    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        int insertCount;
        switch (sUriMatcher.match(uri)) {
            case POSTS_ROUTE:
                insertCount = bulkReplaceRecords(PostContract.Post.TABLE_NAME, values);
                Log.d(TAG, "***** number of records inserted: " + insertCount);// TODO: remove
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI " + uri);
        }

        // If rows inserted, notify registered observers that row(s) was inserted/updated.
        if (insertCount > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return insertCount;
    }

    /**
     * Utility method to create and/or open database.
     */
    private SQLiteDatabase getDatabase() {
        return mPostDatabaseHelper.getReadableDatabase();
    }

    /**
     * Utility method to insert/update(replace) records in a single bulk transaction.
     */
    private int bulkReplaceRecords(String tableName, ContentValues[] values) {
        int recordCount = 0;
        try {
            getDatabase().beginTransaction();
            for (ContentValues value : values) {
                long id = getDatabase().replace(tableName, tableName, value);
                if (id > -1) {
                    recordCount++;
                }
            }
            getDatabase().setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "Error replacing records via bulkReplaceRecords(): ", e);
            return 0;
        } finally {
            getDatabase().endTransaction();
        }
        return recordCount;
    }
}
