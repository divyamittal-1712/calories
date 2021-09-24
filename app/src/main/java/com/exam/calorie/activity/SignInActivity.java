package com.exam.calorie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.calorie.utils.DatabaseHandler;
import com.exam.calorie.R;

public class SignInActivity extends AppCompatActivity {

    TextView txt_sign_in;
    Button btn_login;
    EditText ed_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();
        txt_sign_in = findViewById(R.id.txt_sign_in);
        txt_sign_in.setOnClickListener(v -> {
            startActivity(new Intent(this,Signup.class));
        });
        btn_login = findViewById(R.id.btn_login);
        ed_email = findViewById(R.id.ed_email);

        btn_login.setOnClickListener(v -> {
            String email = ed_email.getText().toString();
            if (email.isEmpty()){
                ed_email.setError("Enter Email");
                ed_email.requestFocus();
            }
            else {
                try {
                    DatabaseHandler databaseHandler = new DatabaseHandler(this);
                    databaseHandler.matchEmail(email);
                    startActivity(new Intent(this,MainActivity.class));
                }catch (Exception e){
                    Toast.makeText(this,"Not matched !!!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}