package com.example.notesapp.helper

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.webkit.WebSettings.RenderPriority
import androidx.core.content.contentValuesOf
import com.example.notesapp.model.NotesModel

class DbHelper(context: Context?) :
    SQLiteOpenHelper(context, "NotesDb.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE mainNotes(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT,description TEXT,time TEXT,date TEXT,priority INTEGER)"
        db!!.execSQL(query)
        val recycleQuery = "CREATE TABLE recycleNotes(restoreId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "restoreTitle TEXT, restoreDescription TEXT,restoreTime TEXT, restoreDate TEXT, restorePriority INTEGER)"
        db.execSQL(recycleQuery)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
    fun insert(model: NotesModel){

        val db = writableDatabase
        val value = contentValuesOf()
        value.put("title",model.notesTitle)
        value.put("description",model.notesDescription)
        value.put("time",model.notesTime)
        value.put("date",model.notesDate)
        value.put("priority",model.notesPriority)
        db.insert("mainNotes",null,value)
    }
    fun update(model: NotesModel) {
        val db = writableDatabase
        val values = contentValuesOf()
        values.put("title", model.notesTitle)
        values.put("description", model.notesDescription)
        values.put("time", model.notesTime)
        values.put("date", model.notesDate)
        values.put("priority", model.notesPriority)
        db.update("mainNotes", values, "id=?", arrayOf<String>("${model.id}"))
    }
    fun delete(id: Int) {
        val db = writableDatabase
        db.delete("mainNotes", "id=?", arrayOf<String>("$id"))
    }
    @SuppressLint("Range", "Recycle")
    fun read():MutableList<NotesModel>{
        val list = mutableListOf<NotesModel>()
        val db = readableDatabase
        val query = "SELECT * FROM mainNotes"
        val cursor = db.rawQuery(query, null)
        if(cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val title = cursor.getString(cursor.getColumnIndex("title"))
                val description = cursor.getString(cursor.getColumnIndex("description"))
                val time = cursor.getString(cursor.getColumnIndex("time"))
                val date = cursor.getString(cursor.getColumnIndex("date"))
                val priority = cursor.getInt(cursor.getColumnIndex("priority"))
                list.add(NotesModel(id,title,description,time,date,priority))
            }while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range", "Recycle")
    fun readDataPriority(priority: Int) : MutableList<NotesModel>{
        val list = mutableListOf<NotesModel>()
        val db = readableDatabase
        val query = "SELECT * FROM mainNotes WHERE priority=$priority"
        val cursor = db.rawQuery(query, null)
        if(cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val title = cursor.getString(cursor.getColumnIndex("title"))
                val description = cursor.getString(cursor.getColumnIndex("description"))
                val time = cursor.getString(cursor.getColumnIndex("time"))
                val date = cursor.getString(cursor.getColumnIndex("date"))
                val priorities = cursor.getInt(cursor.getColumnIndex("priority"))
                list.add(NotesModel(id,title,description,time,date,priorities))
            }while (cursor.moveToNext())
        }
        return list
    }
    fun restoreInsert(model: NotesModel){
        val db = writableDatabase
        val cn = contentValuesOf()
        cn.put("restoreTitle",model.notesTitle)
        cn.put("restoreDescription",model.notesDescription)
        cn.put("restoreTime",model.notesTime)
        cn.put("restoreDate",model.notesDate)
        cn.put("restorePriority",model.notesPriority)
        db.insert("recycleNotes",null,cn)
    }
    fun restoreDelete(restoreId: Int) {
        val db = writableDatabase
        db.delete("recycleNotes", "restoreId=?", arrayOf<String>("$restoreId"))
    }
    @SuppressLint("Range", "Recycle")
    fun restoreRead():MutableList<NotesModel>{
        val recycleList = mutableListOf<NotesModel>()
        val db = readableDatabase
        val query = "SELECT * FROM recycleNotes"
        val cursor = db.rawQuery(query,null)
        if(cursor.moveToFirst()){
            do {
                val restoreId = cursor.getInt(cursor.getColumnIndex("restoreId"))
                val restoreTitle = cursor.getString(cursor.getColumnIndex("restoreTitle"))
                val restoreDescription = cursor.getString(cursor.getColumnIndex("restoreDescription"))
                val restoreTime = cursor.getString(cursor.getColumnIndex("restoreTime"))
                val restoreDate = cursor.getString(cursor.getColumnIndex("restoreDate"))
                val restorePriority = cursor.getInt(cursor.getColumnIndex("restorePriority"))
                recycleList.add(NotesModel(restoreId,restoreTitle,restoreDescription,restoreTime,restoreDate,restorePriority))
            }while (cursor.moveToNext())
        }
        return recycleList
    }
}