package com.mbds.newsletter

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.*

class FavDB(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DB_VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {}

    // create empty table
    fun insertEmpty() {
        val db = this.writableDatabase
        val cv = ContentValues()
        // enter your value
        for (x in 1..10) {
            cv.put(KEY_ID, x)
            cv.put(FAVORITE_STATUS, "0")
            db.insert(TABLE_NAME, null, cv)
        }
    }

    // insert data into database
    fun insertIntoTheDatabase(
        id: String?,
        title: String,
        description: String,
        author: String,
        urlToImage: String,
        favorite: String

    ) {
        val db: SQLiteDatabase
        db = this.writableDatabase
        val cv = ContentValues()
        cv.put(ARTICLE_TITLE, title)
        cv.put(ARTICLE_DESCRIPTION, description)
        cv.put(ARTICLE_AUTHOR, author)
        cv.put(ARTICLE_IMAGE, urlToImage)
        cv.put(KEY_ID, id)
        cv.put(FAVORITE_STATUS, favorite)
        db.insert(TABLE_NAME, null, cv)
        Log.d(
            "FavDB Status",
            "$title, favstatus - $favorite - . $cv"
        )
    }

    // read all data
    fun read_all_data(id: String): Cursor {
        val db = this.readableDatabase
        val sql =
            "select * from $TABLE_NAME where $KEY_ID=$id"
        return db.rawQuery(sql, null, null)
    }

    // remove line from database
    fun remove_fav(id: String) {
        val db = this.writableDatabase
        val sql =
            "UPDATE $TABLE_NAME SET  $FAVORITE_STATUS ='0' WHERE $KEY_ID=$id"
        db.execSQL(sql)
        Log.d("remove", id)
    }

    // select all favorite list
    fun select_all_favorite_list(): Cursor {
        val db = this.readableDatabase
        val sql =
            "SELECT * FROM $TABLE_NAME WHERE $FAVORITE_STATUS ='1'"
        return db.rawQuery(sql, null, null)
    }

    companion object {
        private const val DB_VERSION = 1
        private const val DATABASE_NAME = "ArticleDB"
        private const val TABLE_NAME = "favoriteTable"
        var KEY_ID = "id"
        var ARTICLE_TITLE = "articleTitle"
        var ARTICLE_DESCRIPTION = "articleDescription"
        var ARTICLE_AUTHOR = "articleDescription"
        var ARTICLE_IMAGE = "articleImage"
        var FAVORITE_STATUS = "fStatus"

        private val CREATE_TABLE =
            ("CREATE TABLE " + TABLE_NAME + "("
                    + KEY_ID + " TEXT," + ARTICLE_TITLE + " TEXT," + ARTICLE_DESCRIPTION + " TEXT," + ARTICLE_AUTHOR + " TEXT,"
                    + ARTICLE_IMAGE + " TEXT," + FAVORITE_STATUS + " TEXT)")
    }
}