/**********************************************************************************************************************************************************************
****** AUTO GENERATED FILE BY ANDROID SQLITE HELPER SCRIPT BY FEDERICO PAOLINELLI. ANY CHANGE WILL BE WIPED OUT IF THE SCRIPT IS PROCESSED AGAIN. *******
**********************************************************************************************************************************************************************/
package com.zaptrapp.nishanth.databasetesting.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import java.util.Date;

public class DbHelperDbHelper {
    private static final String TAG = "DbHelper";

    private static final String DATABASE_NAME = "DbHelper.db";
    private static final int DATABASE_VERSION = 1;


    // Variable to hold the database instance
    protected SQLiteDatabase mDb;
    // Context of the application using the database.
    private final Context mContext;
    // Database open/upgrade helper
    private DbHelper mDbHelper;
    
    public DbHelperDbHelper(Context context) {
        mContext = context;
        mDbHelper = new DbHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public DbHelperDbHelper open() throws SQLException { 
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
                                                     
    public void close() {
        mDb.close();
    }

    public static final String ROW_ID = "_id";

    
    // -------------- DETAILS DEFINITIONS ------------
    public static final String DETAILS_TABLE = "Details";
    
    public static final String DETAILS_NAME_COLUMN = "Name";
    public static final int DETAILS_NAME_COLUMN_POSITION = 1;
    
    public static final String DETAILS_AMOUNT_COLUMN = "Amount";
    public static final int DETAILS_AMOUNT_COLUMN_POSITION = 2;
    
    public static final String DETAILS_SHORTDESC_COLUMN = "ShortDesc";
    public static final int DETAILS_SHORTDESC_COLUMN_POSITION = 3;
    
    


    // -------- TABLES CREATION ----------

    
    // Details CREATION 
    private static final String DATABASE_DETAILS_CREATE = "create table " + DETAILS_TABLE + " (" +
                                "_id integer primary key autoincrement, " +
                                DETAILS_NAME_COLUMN + " text, " +
                                DETAILS_AMOUNT_COLUMN + " real, " +
                                DETAILS_SHORTDESC_COLUMN + " text" +
                                ")";
    

    
    // ----------------Details HELPERS -------------------- 
    public long addDetails (String Name, double Amount, String ShortDesc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DETAILS_NAME_COLUMN, Name);
        contentValues.put(DETAILS_AMOUNT_COLUMN, Amount);
        contentValues.put(DETAILS_SHORTDESC_COLUMN, ShortDesc);
        return mDb.insert(DETAILS_TABLE, null, contentValues);
    }

    public long updateDetails (long rowIndex,String Name, double Amount, String ShortDesc) {
        String where = ROW_ID + " = " + rowIndex;
        ContentValues contentValues = new ContentValues();
        contentValues.put(DETAILS_NAME_COLUMN, Name);
        contentValues.put(DETAILS_AMOUNT_COLUMN, Amount);
        contentValues.put(DETAILS_SHORTDESC_COLUMN, ShortDesc);
        return mDb.update(DETAILS_TABLE, contentValues, where, null);
    }

    public boolean removeDetails(long rowIndex){
        return mDb.delete(DETAILS_TABLE, ROW_ID + " = " + rowIndex, null) > 0;
    }

    public boolean removeAllDetails(){
        return mDb.delete(DETAILS_TABLE, null, null) > 0;
    }

    public Cursor getAllDetails(){
    	return mDb.query(DETAILS_TABLE, new String[] {
                         ROW_ID,
                         DETAILS_NAME_COLUMN,
                         DETAILS_AMOUNT_COLUMN,
                         DETAILS_SHORTDESC_COLUMN
                         }, null, null, null, null, null);
    }

    public Cursor getDetails(long rowIndex) {
        Cursor res = mDb.query(DETAILS_TABLE, new String[] {
                                ROW_ID,
                                DETAILS_NAME_COLUMN,
                                DETAILS_AMOUNT_COLUMN,
                                DETAILS_SHORTDESC_COLUMN
                                }, ROW_ID + " = " + rowIndex, null, null, null, null);

        if(res != null){
            res.moveToFirst();
        }
        return res;
    }
    

    private static class DbHelper extends SQLiteOpenHelper {
        public DbHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // Called when no database exists in disk and the helper class needs
        // to create a new one. 
        @Override
        public void onCreate(SQLiteDatabase db) {      
            db.execSQL(DATABASE_DETAILS_CREATE);
            
        }

        // Called when there is a database version mismatch meaning that the version
        // of the database on disk needs to be upgraded to the current version.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Log the version upgrade.
            Log.w(TAG, "Upgrading from version " + 
                        oldVersion + " to " +
                        newVersion + ", which will destroy all old data");
            
            // Upgrade the existing database to conform to the new version. Multiple 
            // previous versions can be handled by comparing _oldVersion and _newVersion
            // values.

            // The simplest case is to drop the old table and create a new one.
            db.execSQL("DROP TABLE IF EXISTS " + DETAILS_TABLE + ";");
            
            // Create a new one.
            onCreate(db);
        }
    }
}

