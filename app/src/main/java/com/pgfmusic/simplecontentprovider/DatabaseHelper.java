package com.pgfmusic.simplecontentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pedrogonzalezferrandez on 22/12/15.
 */


public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + Contract.STUDENTS_TABLE_NAME +
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " name TEXT NOT NULL, " +
            " grade TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, Contract.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.STUDENTS_TABLE_NAME);
        onCreate(db);
    }
}
