package com.example.android.pets.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.android.pets.data.PetContract.*

class PetDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    init {
        Log.d(LOG_TAG, "Constructor")
    }

    companion object {
        val LOG_TAG = "PetDBHelper"
        val DATABASE_NAME = "pets.db"
        val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sqlCreateTable = "CREATE TABLE " + PetEntry.TABLE_NAME + "(" +
                PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PetEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                PetEntry.COLUMN_BREED + " TEXT, " +
                PetEntry.COLUMN_GENDER + " INTEGER NOT NULL, " +
                PetEntry.COLUMN_WEIGHT + "INTEGER NOT NULL DEFAULT 0);"
        db!!.execSQL(sqlCreateTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}