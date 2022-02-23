package com.project.regame.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;


import com.project.regame.Features.CreateData.Leaderboard;
import com.project.regame.Util.Config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DatabaseQueryClass {

    private Context context;

    public DatabaseQueryClass(Context context){
        this.context = context;
    }

    public long insertData(Leaderboard leaderboard){

        long id = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_GAME_NAME, leaderboard.getName());
        contentValues.put(Config.COLUMN_GAME_SCORE, leaderboard.getScoreNumber());


        try {
            id = sqLiteDatabase.insertOrThrow(Config.TABLE_GAME, null, contentValues);
        } catch (SQLiteException e){

            Toast.makeText(context, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return id;
    }

    public List<Leaderboard> getAllData(){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
            String[] score = new String[]{ "score" };
            cursor = sqLiteDatabase.query(Config.TABLE_GAME, null, null, null, null, null, null, null);


            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<Leaderboard> leaderboardList = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_GAME_ID));
                        String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_GAME_NAME));
                        int scoreNumber = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_GAME_SCORE));

                        leaderboardList.add(new Leaderboard(id, name, scoreNumber));
                    }   while (cursor.moveToNext());

                    return leaderboardList;
                }
        } catch (Exception e){
            Log.d("Exception: " , e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }






    public boolean deleteAllStudents(){
        boolean deleteStatus = false;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            //for "1" delete() method returns number of deleted rows
            //if you don't want row count just use delete(TABLE_NAME, null, null)
            sqLiteDatabase.delete(Config.TABLE_GAME, null, null);

            long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_GAME);

            if(count==0)
                deleteStatus = true;

        } catch (SQLiteException e){
            Log.d("Exception: " , e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deleteStatus;
    }

}