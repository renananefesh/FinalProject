//package com.example.project;
//
//import android.content.ContentValues;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.content.Context;
//
//public class Databasehelper extends SQLiteOpenHelper {
//    public static final String DATABASE_NAME ="Event.db";
//    public static final String TABLE_NAME = "Event_table";
//    public static final String COL_1 = "event_name";
//    public static final String COL_2 = "manager_name";
//    public static final String COL_3 = "date";
//    public static final String COL_4 = "adress";
//    public static final String COL_5 = "participants_db_name ";
//
//
//    public Databasehelper(Context context){
//        super(context, DATABASE_NAME, null,1); //creating the database
//
//    }
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table " + TABLE_NAME + "(event_name TEXT PRIMARY KEY, manager_name TEXT," +
//                " date INTIGER , adress TEXT, participants_db_name TEXT)");
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(" drop table if exists " + TABLE_NAME);
//        onCreate(db);
//
//    }
//
//    public boolean insertData( String event_name ,String manager_name,Integer date,String adress){
//        SQLiteDatabase db =this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1, event_name);
//        contentValues.put(COL_2, manager_name);
//        contentValues.put(COL_3, date);
//        contentValues.put(COL_4, adress);
//
//        long result = db.insert(TABLE_NAME, null, contentValues);
//        if (result==-1)
//            return false;
//        return true;
//    }
//}
