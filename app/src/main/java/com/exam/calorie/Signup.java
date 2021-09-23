package com.exam.calorie;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    EditText ed_name,ed_weight,ed_age,ed_height,ed_sex,ed_mail;
    Button signup_btn;
    DatabaseHandler databaseHandler;
    DatabaseManagerModel databaseManagerModel;
    TextView get_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ed_name = findViewById(R.id.ed_name);
        ed_weight = findViewById(R.id.ed_weight);
        ed_age = findViewById(R.id.ed_age);
        ed_height = findViewById(R.id.ed_height);
        ed_mail = findViewById(R.id.ed_mail);
        signup_btn = findViewById(R.id.signup_btn);
        get_name = findViewById(R.id.get_name);

        databaseManagerModel = new DatabaseManagerModel();
        databaseHandler = new DatabaseHandler(this);



        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHandler.addDatabase(new DatabaseManagerModel(ed_name.getText().toString(),ed_height.getText().toString(),ed_weight.getText().toString(),ed_age.getText().toString(),ed_sex.getText().toString(),ed_mail.getText().toString()));

                databaseManagerModel = databaseHandler.getDatabase(ed_mail.getText().toString());
                String name = databaseManagerModel.getName();
                get_name.setText(name);
            }
        });


    }
}