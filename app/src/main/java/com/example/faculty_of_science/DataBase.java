package com.example.faculty_of_science;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    String error = "";
    public static final String Dnmae = "student";

    public DataBase(@Nullable Context context) {
        super(context, "student", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table student" +
                "(" +
                "std_id  integer PRIMARY KEY autoincrement ," +
                "name  text  NOT NULL ," +
                "email text   NOT NULL, " +
                "password text NOT NULL " +
                ");";
        db.execSQL(sql);
        //
        String st = "create table To_Do" +
    "("+
                "name text ,"+
                "student_id integer NOT NULL, " +
                "FOREIGN KEY (student_id) REFERENCES student(std_id)"+
                ");";

        db.execSQL(st);

    }

    /*
                    "(" +
                    "task_id integer PRIMARY KEY autoincrement," +
                    "task_progress integer, " +
                    "task_name text NOT NULL," +
                    "student_id integer NOT NULL," +  // إضافة عمود للمفتاح الخارجي
                    "FOREIGN KEY (student_id) REFERENCES student(std_id)" +  // تعريف المفتاح الخارجي
                    ");";*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists student");
    }

    Boolean insert_data(String name, String email, String password) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = mydb.insert("student", null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    Boolean check_email(String email) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor coursor = mydb.rawQuery("Select * from student where email = ?", new String[]{email});
        if (coursor.getCount() > 0) {
            return true;
        } else return false;
    }

    Boolean check_email_password(String email, String password) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor coursor = mydb.rawQuery("Select * from student where email = ? and password = ?", new String[]{email, password});
        if (coursor.getCount() > 0) {
            return true;
        } else return false;
    }
    @SuppressLint("Range")
    public int getUserId(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        int userId = -1; // Default value if no matching user is found

        String selection = "email = ? AND password = ?";
        String[] selectionArgs = {username, password};
        String[] columns = {"std_id"};

        Cursor cursor = db.query("student", columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(cursor.getColumnIndex("std_id"));
        }
        cursor.close();

        return userId;
    }

    public boolean insertTask(To_Do task,int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String error = "";

        // Begin a transaction
        db.beginTransaction();
        try {
            String name = task.getName();

            // Execute the INSERT INTO command
            //دي من غير مادخل ال  idهي شغاله
           // String st = "INSERT INTO To_Do (name) VALUES ('" + name + "') ;";
            String st = "INSERT INTO To_Do (name, student_id) VALUES ('" + name + "', " + id + ") ;";

            db.execSQL(st);

            // Mark the transaction as successful
            db.setTransactionSuccessful();
            return true;
        } catch (SQLException e) {
            error += "Error inserting task: ";

        } finally {
            // End the transaction (commit or rollback)
            db.endTransaction();
        }
        return false;
    }


    Cursor show(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String error = "";
        db.beginTransaction();
        try {
            Cursor res;
            // Modify the SQL query to use the passed id parameter
            String st = "SELECT name FROM To_DO WHERE student_id = " + id + ";";
            res = db.rawQuery(st, null);
            db.setTransactionSuccessful();
            return res;
        } catch (SQLException e) {
            error += "error in show task";
        } finally {
            db.endTransaction();
        }
        return null;
    }

    public boolean delete(String tname) {
        SQLiteDatabase db = this.getWritableDatabase();
        error = "";
        db.beginTransaction();
        try {
            String st = "DELETE FROM To_Do WHERE name='" + tname + "';";
            db.execSQL(st);
            db.setTransactionSuccessful();
            return true;
        } catch (SQLException e) {
            error += "Error in deleting task";
        } finally {
            db.endTransaction();
        }
        return false;
    }

    public String getError() {
        return error;
    }
}


