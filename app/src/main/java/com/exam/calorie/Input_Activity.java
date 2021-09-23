package com.exam.calorie;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class Input_Activity extends AppCompatActivity {

    LinearLayout ll_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_);

        ll_activity = findViewById(R.id.ll_activity);

    }

    public void onSubmitClick(View v){


    }

    public void onDeleteClick(View v){


    }


}