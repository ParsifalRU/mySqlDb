package main.db

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.database.sqlite.SQLiteOpenHelper as SQLiteOpenHelper1

object setting{
    const val TABLE_NAME = ""
    const val DATABASENAME = "AUTO_DB"
    const val DATABASE_VERSIOM = "1"
}

class DataBase(context) {

    override fun onCreate(db: SQLiteDatabase) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}