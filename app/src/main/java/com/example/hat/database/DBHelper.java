package com.example.hat.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "BDHat";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";

    public static final String TABLE_WORD = "word";
    public static final String TABLE_COMPLEXITY = "complexity";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "--- onCreate database ---");

        db.execSQL("create table " + TABLE_WORD + " ("
                + COLUMN_ID + " integer primary key autoincrement,"
                + COLUMN_TITLE + " text" +  ");");

        db.execSQL("create table " + TABLE_COMPLEXITY + " ("
                + COLUMN_ID + " integer primary key autoincrement,"
                + COLUMN_TITLE + " text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPLEXITY);
        onCreate(db);
    }

    public boolean insertWord (String word) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_WORD, word);
        db.insert(TABLE_WORD, null, contentValues);
        db.close();
        return true;
    }

    public boolean insertWord (String word, int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_WORD, word);
        db.update(TABLE_WORD,contentValues, "id = ?",
                    new String[] {String.valueOf(position)});
        db.close();
        return true;
    }

    public boolean insertWords (ArrayList<String> words) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (String word : words) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(TABLE_WORD, word);
            db.insert(TABLE_WORD, null, contentValues);
        }
        db.close();
        return true;
    }

    public ArrayList<String> getWords() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_WORD,
                null, null, null, null, null, null);
        ArrayList<String> words = new ArrayList<>();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                words.add(cursor.getString(1));
            }
        }
        cursor.close();
        db.close();
        return words;
    }

    public ArrayList<String> getWords(int count) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_WORD,
                null, null, null, null, null, null);
        ArrayList<String> words = new ArrayList<>();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount() && i <= count; i++) {
                cursor.moveToNext();
                words.add(cursor.getString(1));
            }
        }
        cursor.close();
        db.close();
        return words;
    }



}
