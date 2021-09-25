package com.exam.calorie.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.exam.calorie.model.DatabaseManagerModel;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int Database_Version = 1;
    private static final String Database_Name = "calories";
    private static final String Table_Name = "user_info";
    private static final String Key_ID = "id";
    private static final String Key_Name = "name";
    private static final String Key_Height = "height";
    private static final String Key_Weight = "weight";
    private static final String Key_Sex = "sex";
    private static final String Key_Age = "age";
    private static final String Key_Mail = "mail";


    private static final String Food_Table_Name = "calories_in";
    private static final String Food_id = "food_id";
    private static final String Food_name = "food_name";
    private static final String Food_quantitty = "food_quantity";
    private static final String Food_meal = "food_meal";

    private static final String Activity_Table_Name = "calories_out";
    private static final String Activity_id = "activity_id";
    private static final String Activity_Type = "activity_type";
    private static final String Duration = "duration";


    public DatabaseHandler(@Nullable Context context) {
        super(context, Database_Name, null, Database_Version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + Table_Name + "("
                + Key_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Key_Name + " TEXT, "
                + Key_Height + " TEXT,"
                + Key_Weight + " TEXT,"
                + Key_Sex + " TEXT,"
                + Key_Age + " TEXT,"
                + Key_Mail + " TEXT UNIQUE)");

        db.execSQL("CREATE TABLE " + Food_Table_Name + "("
                + Food_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Food_name + " TEXT, "
                + Food_quantitty + " TEXT,"
                + Key_Weight + " TEXT,"
                + Food_meal + " TEXT )");

        db.execSQL("CREATE TABLE " + Activity_Table_Name + "("
                + Activity_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Activity_Type + " TEXT, "
                + Duration + " TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        db.execSQL("DROP TABLE IF EXISTS " + Food_Table_Name);
        db.execSQL("DROP TABLE IF EXISTS " + Activity_Table_Name);

        onCreate(db);
    }

    public long addDatabase(String name, String height, String weight, String age, String sex, String mail) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Key_Name, name);
        values.put(Key_Height, height);
        values.put(Key_Weight, weight);
        values.put(Key_Age, age);
        values.put(Key_Sex, sex);
        values.put(Key_Mail, mail);

        long id = db.insert(Table_Name, null, values);
        db.close();

        return id;
    }

    public DatabaseManagerModel getNote(long email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Table_Name,
                new String[]{Key_ID, Key_Name,Key_Height,Key_Weight,Key_Age,Key_Sex,Key_Mail},
                Key_ID + "=?",
                new String[]{String.valueOf(email)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        DatabaseManagerModel note = new DatabaseManagerModel();
        note.setId(cursor.getString(0));
        note.setName(cursor.getString(1));
        note.setMail(cursor.getString(6));
        note.setWeight(cursor.getString(3));
        note.setHeight(cursor.getString(2));
        note.setSex(cursor.getString(5));
        note.setAge(cursor.getString(4));

        cursor.close();

        return note;
    }

    public DatabaseManagerModel matchEmail(String email) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        Cursor cursor = db.rawQuery("SELECT mail FROM user_info WHERE mail =" + email, null);
//
//        return email;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT mail FROM user_info WHERE mail =" + email, null);
        res.moveToFirst();
        while(res.isAfterLast() == false){
            DatabaseManagerModel response = new DatabaseManagerModel();
//            response.mail = res.getString(res.getColumnIndex(Table_Name));
            // rest of columns
            return response;
        }
        return null;
    }

    public ArrayList<DatabaseManagerModel> getDatabase() {
        ArrayList<DatabaseManagerModel> managerModels = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Table_Name;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                DatabaseManagerModel model = new DatabaseManagerModel();
                model.setId(cursor.getString(cursor.getColumnIndex(Key_ID)));
                model.setName(cursor.getString(cursor.getColumnIndex(Key_Name)));
                model.setHeight(cursor.getString(cursor.getColumnIndex(Key_Height)));
                model.setAge(cursor.getString(cursor.getColumnIndex(Key_Age)));
                model.setSex(cursor.getString(cursor.getColumnIndex(Key_Sex)));
                model.setWeight(cursor.getString(cursor.getColumnIndex(Key_Weight)));
                model.setMail(cursor.getString(cursor.getColumnIndex(Key_Mail)));

                managerModels.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
//        db.close();

        return managerModels;
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

    public long addFood(String food_name, String food_quantity, String food_meal) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Food_name, food_name);
        values.put(Food_quantitty, food_quantity);
        values.put(Food_meal, food_meal);

        long id = db.insert(Food_Table_Name, null, values);
        db.close();

        return id;
    }


    public long addActivity(String activity_type, String duration) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Activity_Type, activity_type);
        values.put(Duration, duration);


        long id = db.insert(Activity_Table_Name, null, values);
        db.close();

        return id;
    }

    public ArrayList<String> getAllBookFood(String type) {
        ArrayList<String> array_list = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Food_Table_Name + " where " + Food_meal + "='" + type + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                array_list.add(cursor.getString(cursor.getColumnIndex(Food_name)));
                array_list.add(cursor.getString(cursor.getColumnIndex(Food_quantitty)));
                array_list.add(cursor.getString(cursor.getColumnIndex(Food_meal)));
//                String model = new AppointmentTableModel();
//                model.setAppoint_id(cursor.getString(cursor.getColumnIndex(APPOINTMENT_ID)));
//                model.setAppoint_patient_name(cursor.getString(cursor.getColumnIndex(Food_name)));
//                model.setAppoint_patient_phone(cursor.getString(cursor.getColumnIndex(Food_quantitty)));
//                model.setAppoint_patient_email(cursor.getString(cursor.getColumnIndex(Food_meal)));
//
//                appointmentTableModels.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
//        db.close();

        return array_list;
    }
}
