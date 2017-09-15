package com.zaptrapp.nishanth.databasetesting.Database;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.Date;

public class DbHelperProviderClient{


    // ------------- DETAILS_HELPERS ------------
    public static Uri addDetails (String Name, 
                                double Amount, 
                                String ShortDesc, 
                                Context c) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelperProvider.DETAILS_NAME_COLUMN, Name);
        contentValues.put(DbHelperProvider.DETAILS_AMOUNT_COLUMN, Amount);
        contentValues.put(DbHelperProvider.DETAILS_SHORTDESC_COLUMN, ShortDesc);
        ContentResolver cr = c.getContentResolver();
        return cr.insert(DbHelperProvider.DETAILS_URI, contentValues);
    }

    public static int removeDetails(long rowIndex, Context c){
        ContentResolver cr = c.getContentResolver();
        Uri rowAddress = ContentUris.withAppendedId(DbHelperProvider.DETAILS_URI, rowIndex);
        return cr.delete(rowAddress, null, null);
    }

    public static int removeAllDetails(Context c){
        ContentResolver cr = c.getContentResolver();
        return cr.delete(DbHelperProvider.DETAILS_URI, null, null);
    }

    public static Cursor getAllDetails(Context c){
    	ContentResolver cr = c.getContentResolver();
        String[] resultColumns = new String[] {
                         DbHelperProvider.ROW_ID,
                         DbHelperProvider.DETAILS_NAME_COLUMN,
                         DbHelperProvider.DETAILS_AMOUNT_COLUMN,
                         DbHelperProvider.DETAILS_SHORTDESC_COLUMN
                         };

        Cursor resultCursor = cr.query(DbHelperProvider.DETAILS_URI, resultColumns, null, null, null);
        return resultCursor;
    }

    public static Cursor getDetails(long rowId, Context c){
    	ContentResolver cr = c.getContentResolver();
        String[] resultColumns = new String[] {
                         DbHelperProvider.ROW_ID,
                         DbHelperProvider.DETAILS_NAME_COLUMN,
                         DbHelperProvider.DETAILS_AMOUNT_COLUMN,
                         DbHelperProvider.DETAILS_SHORTDESC_COLUMN
                         };

        Uri rowAddress = ContentUris.withAppendedId(DbHelperProvider.DETAILS_URI, rowId);
        String where = null;    
        String whereArgs[] = null;
        String order = null;
    
        Cursor resultCursor = cr.query(rowAddress, resultColumns, where, whereArgs, order);
        return resultCursor;
    }

    public static int updateDetails (int rowId, 
                                   String Name,
                                   double Amount,
                                   String ShortDesc,
                                   Context c) {

        Log.d("updateDetails",rowId+" "+Name+" "+Amount+" "+ShortDesc);

        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelperProvider.DETAILS_NAME_COLUMN, Name);
        contentValues.put(DbHelperProvider.DETAILS_AMOUNT_COLUMN, Amount);
        contentValues.put(DbHelperProvider.DETAILS_SHORTDESC_COLUMN, ShortDesc);
        Uri rowAddress = ContentUris.withAppendedId(DbHelperProvider.DETAILS_URI, rowId);

        ContentResolver cr = c.getContentResolver();
        int updatedRowCount = cr.update(rowAddress, contentValues, null, null);
        return updatedRowCount;
    }
    
}
