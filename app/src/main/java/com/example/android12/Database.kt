package com.example.android12

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
class Database(context: Context?) :
    SQLiteOpenHelper(context, "School.db", null, 1) {
    var context = context
    override fun onCreate(p0: SQLiteDatabase?) {
        var sql = "CREATE TABLE student(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT, address TEXT)"
        p0?.execSQL(sql)
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
    fun insertData(name:String, surname:String, address:String) {
        var db = writableDatabase
        var values = ContentValues()
        values.put("name",name)
        values.put("surname",surname)
        values.put("address",address)
        var iss = db.insert("student",null,values)
        if (iss.toInt() == -1) {
            Toast.makeText(context, "Data is Not Insert", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Data is Successfully Inserted", Toast.LENGTH_SHORT).show()
        }
    }

    fun showData(): ArrayList<UserModel> {
        var modelList = ArrayList<UserModel>()
        var db = readableDatabase
        var sql = "SELECT * FROM student"
        var cursor = db.rawQuery(sql,null)
        cursor.moveToFirst()

        for (x in 0..cursor.count-1) {
            var id = cursor.getInt(0)
            var name = cursor.getString(1)
            var surname = cursor.getString(2)
            var address = cursor.getString(3)
            var model = UserModel(id, name, surname, address)
            modelList.add(model)
            cursor.moveToNext()
        }
        return modelList
    }

    fun updateData(id:Int, name:String, surname:String, address:String) {

        var db = writableDatabase
        var values = ContentValues()
        values.put("name",name)
        values.put("surname",surname)
        values.put("address",address)
        db.update("student",values,"id=$id",null)

    }

    fun deleteData(id: Int) {
        var db = writableDatabase
        db.delete("student","id=$id",null)
    }

}