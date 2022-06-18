package com.example.hat.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hat.models.HatDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "BDHat";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";

    public static final String TABLE_WORD = "word";
    public static final String TABLE_DICTIONARY = "complexity";

    public static final String FOREIGN_KEY_DICTIONARY = "dictionary_key";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "--- onCreate database ---");

        db.execSQL("CREATE TABLE " + TABLE_DICTIONARY + " ("
                + COLUMN_ID + " integer PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " text" + ");");

        db.execSQL("CREATE TABLE " + TABLE_WORD + " ("
                + COLUMN_ID + " integer PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " text, "
                + FOREIGN_KEY_DICTIONARY + " integer NOT NULL,"
                + "FOREIGN KEY (" + FOREIGN_KEY_DICTIONARY + ") REFERENCES " + TABLE_DICTIONARY + "(" + COLUMN_ID + ")" +  ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DICTIONARY);
        onCreate(db);
    }


    public boolean insertWord (String word, int dictionaryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, word);
        contentValues.put(FOREIGN_KEY_DICTIONARY, dictionaryId);
        db.insert(TABLE_WORD, null, contentValues);
        db.close();
        return true;
    }


    public boolean insertWords (List<String> words, int dictionaryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (String word : words) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_TITLE, word);
            contentValues.put(FOREIGN_KEY_DICTIONARY, dictionaryId);
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


    public ArrayList<HatDictionary> getDictionary() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DICTIONARY,
                null, null, null, null, null, null);
        ArrayList<HatDictionary> words = new ArrayList<>();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                words.add(new HatDictionary(cursor.getString(1), getWords(cursor.getInt(0))));
            }
        }
        cursor.close();
        db.close();
        return words;
    }




    public boolean insertDictionary (HatDictionary dictionary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, dictionary.getName());
        int id = (int) db.insert(TABLE_DICTIONARY, null, contentValues);

        if(id != 0)
            insertWords(dictionary.getWords(), id);

        db.close();
        return true;
    }

    public ArrayList<String> getWords(int count, int idDictionary) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = FOREIGN_KEY_DICTIONARY + " =?";
        String[] selectionArgs = {String.valueOf(idDictionary)};

        Cursor cursor = db.query(TABLE_WORD,
                null, selection, selectionArgs, null, null, null);
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

    public ArrayList<String> getWords(int idDictionary) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = FOREIGN_KEY_DICTIONARY + " =?";
        String[] selectionArgs = {String.valueOf(idDictionary)};

        Cursor cursor = db.query(TABLE_WORD,
                null, selection, selectionArgs, null, null, null);
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

}
