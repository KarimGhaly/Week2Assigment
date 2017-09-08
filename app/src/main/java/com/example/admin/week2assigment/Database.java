package com.example.admin.week2assigment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 9/7/2017.
 */

public class Database extends SQLiteOpenHelper {
    public static final String Database_Name = "DB";
    public static final int Database_Version = 6;
    public static final String Table_Name = "TasksToDo";
    public static final String Column_ID ="ID";
    public static final String Column_Name = "Name";
    public static final String Column_Date = "DueDate";
    public static final String Column_Priority = "Priority";
    public static final String Column_Done = "Done";

    public Database(Context context) {
        super(context, Database_Name, null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTBL = "CREATE TABLE "+Table_Name + "("+Column_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Column_Name+" Text,"+
                Column_Date+" Text,"+
                Column_Priority+" Int,"+
                Column_Done+" Boolean"+
                ")";
        db.execSQL(CreateTBL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }

    public long InsertToDB(String Name, String DueDate, int Priority, Boolean Done)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column_Name,Name);
        contentValues.put(Column_Date,DueDate);
        contentValues.put(Column_Priority,Priority);
        contentValues.put(Column_Done,Done);
        long isSaved = sqLiteDatabase.insert(Table_Name,null,contentValues);
        return isSaved;
    }
    public List<Tasks> ReadFromDatabase()
    {
        List<Tasks> tasksList = new ArrayList<Tasks>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "Select * FROM "+Table_Name;
        Cursor cursor =  sqLiteDatabase.rawQuery(query,null);
        int x = cursor.getCount();
        if(cursor.moveToFirst())
        {
            do {
                boolean value = cursor.getInt(4) > 0;
                Tasks t = new Tasks(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),value);
                tasksList.add(t);
            }while(cursor.moveToNext());
        }
        return tasksList;
    }

    public Tasks getTaskById(int ID)
    {
        Tasks t = null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "Select * FROM "+Table_Name+" WHERE "+Column_ID+"="+ID;
        Cursor cursor =  sqLiteDatabase.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do {
                boolean value = cursor.getInt(4) > 0;
                t = new Tasks(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),value);
            }while(cursor.moveToNext());
        }
        return t;
    }

    public int UpdateTask(int ID,String Name,String DueDate,int Priority,Boolean Done)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column_Name,Name);
        contentValues.put(Column_Date,DueDate);
        contentValues.put(Column_Priority,Priority);
        contentValues.put(Column_Done,Done);
        int Update = sqLiteDatabase.update(Table_Name,contentValues,Column_ID+"="+ID,null);
        return Update;
    }
    public int DeleteTask(int ID)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int Delete = sqLiteDatabase.delete(Table_Name,Column_ID +"="+ID,null);
        return  Delete;
    }
}
