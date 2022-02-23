package com.project.regame.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.project.regame.Util.Config;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper databaseHelper;

    // All Static variables
    private static final int DATABASE_VERSION = 6;

    // Database Name
    private static final String DATABASE_NAME = Config.DATABASE_NAME;

    // Constructor
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public static synchronized DatabaseHelper getInstance(Context context){
        if(databaseHelper==null){
            databaseHelper = new DatabaseHelper(context);
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create tables SQL execution
        String CREATE_GAME_TABLE = "CREATE TABLE " + Config.TABLE_GAME + "("
                + Config.COLUMN_GAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_GAME_NAME+ " TEXT NOT NULL, "
                + Config.COLUMN_GAME_SCORE + " INTEGER NOT NULL "
                + ")";

        Log.d("Table create SQL: " , CREATE_GAME_TABLE);

        db.execSQL(CREATE_GAME_TABLE);

        Log.d("DB created!", "TRUE");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_GAME);

        // Create tables again
        onCreate(db);
    }

}
