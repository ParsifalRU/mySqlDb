package com.example.test2_bd.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.widget.Toast
import com.example.test2_bd.recview.auto_list
import com.example.test2_bd.recview.db_list.ColumnsDataBaseList


class DataBase(context: Context):SQLiteOpenHelper(context, DbConfig.DATABASE_NAME, null, DbConfig.DATABASE_VERSION) {

private val ctx: Context = context

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DbConfig.SQL_CREATE_ENTRIES)
        injectAuto(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DbConfig.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun addAuto(mark:String, model:String, price:Int, speed:String, country:String){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(DbConfig.COLUMN_NAME_0, mark)
        cv.put(DbConfig.COLUMN_NAME_1, model)
        cv.put(DbConfig.COLUMN_NAME_2, price)
        cv.put(DbConfig.COLUMN_NAME_3, speed)
        cv.put(DbConfig.COLUMN_NAME_4, country)
        db.insert(DbConfig.TABLE_NAME, null, cv)
        val cursor = db.query(DbConfig.TABLE_NAME, null, null, null, null, null, null)
        auto_list.clear()
        cursor(cursor)
        cursor.close()
        db.close()

    }

    private fun injectAuto(db: SQLiteDatabase){

        val cv = ContentValues()
        var i = 0

        while (i<StartData().modelAudi.size){
            cv.put(DbConfig.COLUMN_NAME_0, StartData().audi)
            cv.put(DbConfig.COLUMN_NAME_1, StartData().modelAudi[i])
            cv.put(DbConfig.COLUMN_NAME_2, StartData().priceAudi[i])
            cv.put(DbConfig.COLUMN_NAME_3, StartData().speedAudi[i])
            cv.put(DbConfig.COLUMN_NAME_4, StartData().countryAudi[i])
            db.insert(DbConfig.TABLE_NAME, null, cv)
            i++
        }

        i = 0
        while (i<StartData().modelToy.size){
            cv.put(DbConfig.COLUMN_NAME_0, StartData().toy)
            cv.put(DbConfig.COLUMN_NAME_1, StartData().modelToy[i])
            cv.put(DbConfig.COLUMN_NAME_2, StartData().priceToy[i])
            cv.put(DbConfig.COLUMN_NAME_3, StartData().speedToy[i])
            cv.put(DbConfig.COLUMN_NAME_4, StartData().countryToy[i])
            db.insert(DbConfig.TABLE_NAME, null, cv)
            i++
        }

        i=0
        while (i<StartData().modelPorsche.size){
            cv.put(DbConfig.COLUMN_NAME_0, StartData().por)
            cv.put(DbConfig.COLUMN_NAME_1, StartData().modelPorsche[i])
            cv.put(DbConfig.COLUMN_NAME_2, StartData().pricePorsche[i])
            cv.put(DbConfig.COLUMN_NAME_3, StartData().speedPorsche[i])
            cv.put(DbConfig.COLUMN_NAME_4, StartData().countryPorsche[i])
            db.insert(DbConfig.TABLE_NAME, null, cv)
            i++
        }
    }

    fun delete(numb_int:Int) {
        val db = this.writableDatabase
        db.delete(DbConfig.TABLE_NAME, "${BaseColumns._ID} = $numb_int", null)
    }

    fun rvList() {
        val db = this.writableDatabase
        val cursor = db.query(DbConfig.TABLE_NAME, null, null, null, null, null, null)

        cursor(cursor)
        cursor.close()
        db.close()
    }

    fun filterManufacturer(selection1: String, selection2:String) {

        val orAnd:String = if (selection1 != ""){
            if (selection2 != ""){
                "AND"
            }else "OR"
        }else "OR"

        val db = readableDatabase
        val cursor = db.query(DbConfig.TABLE_NAME,
            null, "${DbConfig.COLUMN_NAME_0} = ? $orAnd ${DbConfig.COLUMN_NAME_1} = ?" ,
            arrayOf(selection1,selection2), null, null, null)

        cursor(cursor)
        cursor.close()
        db.close()

    }

    fun sort(){
        val db = readableDatabase
        val cursor = db.query(DbConfig.TABLE_NAME, null, null, null, null, null, DbConfig.COLUMN_NAME_2)
        cursor(cursor)
        db.close()

    }

    private fun cursor(cursor:Cursor){

        if (cursor.moveToFirst()) {
            val idColIndex = cursor.getColumnIndex(BaseColumns._ID)
            val col0ColIndex = cursor.getColumnIndex(DbConfig.COLUMN_NAME_0)
            val col1ColIndex = cursor.getColumnIndex(DbConfig.COLUMN_NAME_1)
            val col2ColIndex = cursor.getColumnIndex(DbConfig.COLUMN_NAME_2)
            val col3ColIndex = cursor.getColumnIndex(DbConfig.COLUMN_NAME_3)
            val col4ColIndex = cursor.getColumnIndex(DbConfig.COLUMN_NAME_4)

            do {
                val colInd = cursor.getInt(idColIndex)
                val col0 = cursor.getString(col0ColIndex)
                val col1 = cursor.getString(col1ColIndex)
                val col2 = cursor.getInt(col2ColIndex)
                val col3 = cursor.getString(col3ColIndex)
                val col4 = cursor.getString(col4ColIndex)

                val auto = ColumnsDataBaseList(colInd,col0, col1, col2, col3, col4)
                auto_list.add(auto)

            } while (cursor.moveToNext())
        } else
            Toast.makeText(ctx, "Проверьте правильность введенных данных", Toast.LENGTH_SHORT).show()
    }

}

private object DbConfig{

    const val DATABASE_NAME = "AUTO_DB"
    const val DATABASE_VERSION = 1

    const val TABLE_NAME = "AUTO1234"

    const val COLUMN_NAME_0 = "C0"
    const val COLUMN_NAME_1 = "C1"
    const val COLUMN_NAME_2 = "C2"
    const val COLUMN_NAME_3 = "C3"
    const val COLUMN_NAME_4 = "C4"

    const val SQL_CREATE_ENTRIES =  "CREATE TABLE $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "$COLUMN_NAME_0 TEXT," +
            "$COLUMN_NAME_1 TEXT," +
            "$COLUMN_NAME_2 INTEGER," +
            "$COLUMN_NAME_3 TEXT," +
            "$COLUMN_NAME_4 Text)"

    const val SQL_DELETE_ENTRIES = "DROP IF NOT EXISTS $TABLE_NAME"

}