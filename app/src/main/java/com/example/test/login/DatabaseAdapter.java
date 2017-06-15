package com.example.test.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

/**
 * Created by test on 12-06-2017.
 */

public class DatabaseAdapter {
    DatabaseHelper databaseHelper;

    public DatabaseAdapter(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public long insertdata(String name, String password) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.PASSWORD, password);
        long id = sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
        return id;

    }

    public String getAllData(){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String[] coloumns = {DatabaseHelper.UID,DatabaseHelper.NAME,DatabaseHelper.PASSWORD};
        StringBuffer buffer = new StringBuffer();
        Cursor cursor =  db.query(DatabaseHelper.TABLE_NAME,coloumns,null,null,null,null,null);
        while(cursor.moveToNext()){
            int index1 = cursor.getColumnIndex(DatabaseHelper.UID);
            int index2 = cursor.getColumnIndex(DatabaseHelper.NAME);
            int index3 = cursor.getColumnIndex(DatabaseHelper.PASSWORD);

            int cid = cursor.getInt(index1);
            String username = cursor.getString(index2);
            String password = cursor.getString(index3);
            buffer.append(cid+" "+username+" "+password+"\n");
        }
        return buffer.toString();
    }

    static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "Authentication";
        private static final String TABLE_NAME = "Login";
        private static final int DATABASE_VERSION = 7;
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String PASSWORD = "Password";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) UNIQUE ," + PASSWORD + " VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;


        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Message.message(context, "Constructor Called");

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
                Message.message(context, "onCreate Called");
            } catch (SQLException e) {
                Message.message(context, "" + e);
            }


        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Message.message(context, "onUpgrade called");
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (SQLException e) {
                Message.message(context, "" + e);
            }

        }
    }
}
