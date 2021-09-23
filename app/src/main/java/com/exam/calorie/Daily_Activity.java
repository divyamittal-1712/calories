package com.exam.calorie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Daily_Activity extends AppCompatActivity {

    Button add_Food,add_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_);

        add_Food = findViewById(R.id.add_food);

        add_Food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Daily_Activity.this,Input_Activity.class);
                startActivity(intent);
            }
        });

    }
}