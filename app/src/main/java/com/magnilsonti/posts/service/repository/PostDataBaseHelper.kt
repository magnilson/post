package com.magnilsonti.posts.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.magnilsonti.posts.service.constants.DataBaseConstants.POST

class PostDataBaseHelper(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_POST)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    companion object {
        private const val VERSION = 1
        private const val NAME = "post.db"
        private const val CREATE_TABLE_POST =
            "create table ${POST.TABLE_NAME} (${POST.COLUMNS.ID} integer primary key autoincrement, ${POST.COLUMNS.TITLE} text, ${POST.COLUMNS.BODY} text);"
    }
}