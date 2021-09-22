package com.exam.calorie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int Database_Version = 1;
    private static final String Database_Name = "DatabaseManager";
    private static final String Table_Name = "Calorie";
    private static final String Key_Name = "name";
    private static final String Key_Height = "height";
    private static final String Key_Weight = "weight";
    private static final String Key_Sex = "sex";
    private static final String Key_Age = "age";
    private static final String Key_Mail = "mail";




    public DatabaseHandler(@Nullable Context context) {
        super(context, Database_Name, null, Database_Version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String Create_Contacts_Table = "CREATE TABLE " + Table_Name + "(" + Key_Name + " TEXT, " + Key_Height + " TEXT, " + Key_Weight + " Text, " + Key_Age + " TEXT, " + Key_Sex + " TEXT, " + Key_Mail + " String PRIMARY KEY "+ ")";
        db.execSQL(Create_Contacts_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);

        onCreate(db);
    }

    public void addDatabase(DatabaseManagerModel databaseManagerModel) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Key_Name, databaseManagerModel.getName());
        values.put(Key_Height, databaseManagerModel.getHeight());
        values.put(Key_Weight, databaseManagerModel.getWeight());
        values.put(Key_Age, databaseManagerModel.getAge());
        values.put(Key_Sex, databaseManagerModel.getSex());
        values.put(Key_Mail, databaseManagerModel.getMail());

        db.insert(Table_Name, null, values);
        db.close();

    }

    public DatabaseManagerModel getDatabase(String mealid) {
        SQLiteDatabase db = this.getReadableDatabase();


//        Cursor cursor = db.rawQuery(selectQuery, null);

        Cursor cursor =  db.rawQuery( "SELECT * FROM "+ Table_Name + " WHERE " + Key_Mail + " = '"+ mealid +"'" , null );

        if (cursor != null)
            cursor.moveToFirst();

        DatabaseManagerModel databaseManagerModel = new DatabaseManagerModel();
        databaseManagerModel.setName(String.valueOf(cursor.getString(cursor.getColumnIndex(Key_Name))));
        databaseManagerModel.setHeight(String.valueOf(cursor.getString(cursor.getColumnIndex(Key_Height))));
        databaseManagerModel.setWeight(String.valueOf(cursor.getString(cursor.getColumnIndex(Key_Weight))));
        databaseManagerModel.setAge(String.valueOf(cursor.getString(cursor.getColumnIndex(Key_Age))));
        databaseManagerModel.setSex(String.valueOf(cursor.getString(cursor.getColumnIndex(Key_Sex))));
        databaseManagerModel.setMail(String.valueOf(cursor.getString(cursor.getColumnIndex(Key_Mail))));
        return databaseManagerModel;
    }


    public int updateDatabase(String mail,String weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(Key_Email_Id, databaseManagerModel.getEmail_id());
//        contentValues.put(Key_Type, databaseManagerModel.getType());
        contentValues.put(Key_Mail, mail);
        return db.update(Table_Name, contentValues, Key_Weight + "=?",
                new String[]{String.valueOf(weight)});
    }

    public void deleteDatabase(DatabaseManagerModel databaseManagerModel) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Table_Name, Key_Mail + "=?", new String[]{String.valueOf(databaseManagerModel.getMail())});
        db.close();

    }

    public boolean getDataExist(String id) {

        SQLiteDatabase sq = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + Table_Name + " WHERE "
                + Key_Mail + " = '" + id + "'";

        Cursor cursor = sq.query(Table_Name, new String[]{Key_Name,Key_Height, Key_Weight,Key_Age,Key_Sex,Key_Mail}, Key_Mail + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        boolean hasObject = (cursor.getCount()>0);

        return hasObject;
    }

}
