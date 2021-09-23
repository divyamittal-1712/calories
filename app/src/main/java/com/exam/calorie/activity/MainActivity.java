package com.exam.calorie.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exam.calorie.DatabaseHandler;
import com.exam.calorie.DatabaseManagerModel;
import com.exam.calorie.R;
import com.exam.calorie.adapter.ShowAllUsersAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler_view;
    ShowAllUsersAdapter usersAdapter;
    ArrayList<DatabaseManagerModel> modelArrayList = new ArrayList<>();
    DatabaseHandler databaseHandler;
    private SharedPreferences pref;
    TextView name,email,sex,age,height,weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getApplicationContext().getSharedPreferences("userDetails", 0);
        String user_name = pref.getString("name",null);
        String user_age = pref.getString("age",null);
        String user_height = pref.getString("height",null);
        String user_weight = pref.getString("weight",null);
        String user_email = pref.getString("mail",null);
        String user_sex = pref.getString("sex",null);
        databaseHandler = new DatabaseHandler(this);
        if (user_name == null){
            startActivity(new Intent(this, SignInActivity.class));
        }
        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        usersAdapter = new ShowAllUsersAdapter(modelArrayList,this);
        recycler_view.setAdapter(usersAdapter);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        sex = findViewById(R.id.sex);
        age = findViewById(R.id.age);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        name.setText(user_name);
        email.setText(user_email);
        sex.setText(user_sex);
        age.setText("Age: "+user_age);
        height.setText("Height: "+user_height);
        weight.setText("Weight: "+user_weight);
        LinearLayout linear_layout = findViewById(R.id.linear_layout);
        linear_layout.setBackgroundColor(Color.BLUE);
        name.setTextColor(Color.WHITE);
        email.setTextColor(Color.WHITE);
        sex.setTextColor(Color.WHITE);
        age.setTextColor(Color.WHITE);
        height.setTextColor(Color.WHITE);
        weight.setTextColor(Color.WHITE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        modelArrayList.addAll(databaseHandler.getDatabase());
    }

    @Override
    protected void onPause() {
        super.onPause();
        modelArrayList.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Are you sure you want to Log Out?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SharedPreferences.Editor editor = pref.edit();
                                editor.clear();
                                editor.apply();
                                editor.commit();

                                Intent i = new Intent(MainActivity.this, SignInActivity.class);
                                startActivity(i);
                                finish();

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}