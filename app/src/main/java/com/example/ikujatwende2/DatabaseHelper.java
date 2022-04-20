package com.example.ikujatwende2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context){super(context,"myActivities2.db",null,1);}
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create Table myActivities2(Activity TEXT NOT NULL,Location TEXT ,Date TEXT NOT NULL," +
                "Time TEXT,Reporter TEXT NOT NULL, myReport TEXT,PRIMARY KEY ( Activity, Date, Reporter ))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists myActivities2");



    }

    // Inserting Data
    public Boolean insertuserdata(String Activity, String Location, String Date,String Time, String Reporter){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Activity",Activity);
        contentValues.put("Location",Location);
        contentValues.put("Date",Date);
        contentValues.put("Time",Time);
        contentValues.put("Reporter",Reporter);
        contentValues.put("myReport", "No report added yet!");

        long result = DB.insert("myActivities2",null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    // UPDATING DATA
    public Boolean updateuserdata(String Activity,  String Date, String Reporter, String myReport){
// get a mode of the database that can be written on
        SQLiteDatabase db= this.getWritableDatabase();
        // package the data for insertion
        ContentValues contentValues= new ContentValues();
        // just like bundles, theres a key and the value
        contentValues.put("myReport",myReport);


        // Updating happens
        Cursor cursor = db.rawQuery("Select * from myActivities2 where Activity=? and Date=? and Reporter=?" , new String[]{Activity, Date,Reporter});
        if(cursor.getCount()>0){
            long result = db.update("myActivities2", contentValues, "Activity=? and Date=? and Reporter=?", new String[]{Activity, Date,Reporter});
            if(result==-1){
                return false;
            } else{
                return true;
            }
        }else{
            return false;
        }

    }
    // DELETING
    public Boolean deleteuserdata(String Activity, String Date, String Reporter){
        SQLiteDatabase db= this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from myActivities2 where Activity=? and Date=? and Reporter=?", new String[]{Activity, Date,Reporter});
        if(cursor.getCount()>0){
            long result = db.delete("myActivities2 ", "Activity=? and Date=? and Reporter=?", new String[]{Activity, Date,Reporter});
            if(result==-1){
                return false;
            } else{
                return true;
            }
        }else{
            return false;

        }
    }
    // VIEW DATA
    public Cursor getdata(){
        SQLiteDatabase db= this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from myActivities2", null);
        return cursor;
    }
    public Cursor getReport(String Activity, String Date, String Reporter){
        SQLiteDatabase db= this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select  myReport from myActivities2 where Activity=? and Date=? and Reporter=?", new String[]{Activity, Date,Reporter});
        return cursor;
    }


}
