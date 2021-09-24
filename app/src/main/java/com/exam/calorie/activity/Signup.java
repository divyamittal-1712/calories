package com.exam.calorie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.calorie.model.DatabaseManagerModel;
import com.exam.calorie.utils.DatabaseHandler;

import com.exam.calorie.R;

public class Signup extends AppCompatActivity {

    EditText ed_name, ed_weight, ed_age, ed_height, ed_mail;
    RadioGroup ed_sex;
    Button signup_btn;
    DatabaseHandler databaseHandler;
    DatabaseManagerModel databaseManagerModel;
    TextView txt_sign_in;
    String select_gender;
    RadioGroup radioGroup;
    private RadioButton gender_radio_button;
    String emailPattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        ed_name = findViewById(R.id.ed_name);
        ed_weight = findViewById(R.id.ed_weight);
        ed_age = findViewById(R.id.ed_age);
        ed_height = findViewById(R.id.ed_height);
        ed_mail = findViewById(R.id.ed_mail);
        signup_btn = findViewById(R.id.signup_btn);
        txt_sign_in = findViewById(R.id.txt_sign_in);
        radioGroup = findViewById(R.id.radioGroup);

        databaseHandler = new DatabaseHandler(this);
        pref = getApplicationContext().getSharedPreferences("userDetails", 0);
        editor = pref.edit();

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            gender_radio_button = group.findViewById(checkedId);
            select_gender = gender_radio_button.getText().toString();
        });

        txt_sign_in.setOnClickListener(v -> {
            startActivity(new Intent(this,SignInActivity.class));
        });
        signup_btn.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            gender_radio_button = findViewById(selectedId);
            select_gender = gender_radio_button.getText().toString();

            if (ed_name.getText().toString().isEmpty()) {
                ed_name.setError("Enter name");
                ed_name.requestFocus();
            } else if (ed_weight.getText().toString().isEmpty()) {
                ed_weight.setError("Enter weight");
                ed_weight.requestFocus();
            } else if (ed_age.getText().toString().isEmpty()) {
                ed_age.setError("Enter age");
                ed_age.requestFocus();
            } else if (Integer.parseInt(ed_age.getText().toString()) < 1 || Integer.parseInt(ed_age.getText().toString()) > 120) {
                ed_age.setError("please Enter Valid Patient Age");
                ed_age.requestFocus();
            } else if (ed_height.getText().toString().isEmpty()) {
                ed_height.setError("Enter height");
                ed_height.requestFocus();
            } else if (!(ed_mail.getText().toString().matches(emailPattern))) {
                ed_mail.setError("Enter Valid Id");
                ed_mail.requestFocus();
            } else {
                long data = databaseHandler.addDatabase(ed_name.getText().toString(), ed_height.getText().toString(),
                        ed_weight.getText().toString(), ed_age.getText().toString(), select_gender,
                        ed_mail.getText().toString());
                try {

                    DatabaseManagerModel n = databaseHandler.getNote(data);
                    Log.e("data", String.valueOf(n));
                    if (n != null) {
                        editor.putString("name", ed_name.getText().toString());
                        editor.putString("id", n.getId());
                        editor.putString("age", n.getAge());
                        editor.putString("height", n.getHeight());
                        editor.putString("weight", n.getWeight());
                        editor.putString("sex", n.getSex());
                        editor.putString("mail", n.getMail());

                        editor.commit();

                        ed_name.getText().clear();
                        ed_weight.getText().clear();
                        ed_age.getText().clear();
                        ed_height.getText().clear();
                        ed_mail.getText().clear();
                        Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "Already Email available", Toast.LENGTH_SHORT).show();
                }


            }

        });

    }

}