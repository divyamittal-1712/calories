package com.exam.calorie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.exam.calorie.R;
import com.exam.calorie.utils.DatabaseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Input_Activity extends AppCompatActivity {

    LinearLayout ll_activity,ll_food;
    TextView txt_add_Food,txt_add_Activity;
    Spinner food_spinner,portion_spinner,mealtype_spinner;
    String data;
    ArrayList<String> food_name = new ArrayList<>();
    ArrayList<String> food_protion = new ArrayList<>();
    String[] mealTime = {"Breakfast","Lunch","Snacks","Dinner"};
    Button submit;
    String pro_name,pro_quntity,pro_meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_);

        ll_activity = findViewById(R.id.ll_activity);
        ll_food = findViewById(R.id.ll_food);
        txt_add_Food = findViewById(R.id.txt_add_Food);
        txt_add_Activity = findViewById(R.id.txt_add_Activity);
        food_spinner = findViewById(R.id.food_spinner);
        portion_spinner = findViewById(R.id.portion_spinner);
        mealtype_spinner = findViewById(R.id.mealtype_spinner);
        submit = findViewById(R.id.submit);


        Intent intent =getIntent();
        String a =  intent.getStringExtra("abc");

       if(a.equals("food")){
           txt_add_Food.setVisibility(View.VISIBLE);
           ll_food.setVisibility(View.VISIBLE);
       }else
        if(a.equals("activity")){
            txt_add_Activity.setVisibility(View.VISIBLE);
            ll_activity.setVisibility(View.VISIBLE);
        }

        for (int i=1; i<=10; i++){
            food_protion.add(String.valueOf(i));
        }
        portion_spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,food_protion));
        mealtype_spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mealTime));

        food_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pro_name = food_spinner.getItemAtPosition(food_spinner.getSelectedItemPosition()).toString();
                Log.e("food_name", pro_name);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        portion_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pro_quntity = portion_spinner.getItemAtPosition(portion_spinner.getSelectedItemPosition()).toString();
                Log.e("food_name", pro_quntity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mealtype_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pro_meal = mealtype_spinner.getItemAtPosition(mealtype_spinner.getSelectedItemPosition()).toString();
                Log.e("food_meal", pro_meal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        showFoodData();

        submit.setOnClickListener(v -> {
            DatabaseHandler databaseHandler = new DatabaseHandler(this);
            databaseHandler.addFood(pro_name,pro_quntity,pro_meal);
            startActivity(new Intent(this,Daily_Activity.class));
        });
    }

    public void onSubmitClick(View v){


    }



    private void showFoodData(){
        String string = "";
        StringBuilder stringBuilder = new StringBuilder();
        InputStream is = this.getResources().openRawResource(R.raw.dataconvert);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        while (true) {
            try {
                if ((string = reader.readLine()) == null) break;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            stringBuilder.append(string).append("\n");

            data = stringBuilder.toString();

        }
        try {
            is.close();
            food_json();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void food_json() {
        try {
            JSONObject js = new JSONObject(data);
            JSONArray jsonArray = js.getJSONArray("Sheet1");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String id = jsonObject1.getString("ID");
                String name = jsonObject1.getString("name");
                String food_group = jsonObject1.getString("Food Group");
                String calories = jsonObject1.getString("Calories");
                String fat = jsonObject1.getString("Fat (g)");
                String protein = jsonObject1.getString("Protein (g)");
                String carbohydrate = jsonObject1.getString("Carbohydrate (g)");
                String serving = jsonObject1.getString("Serving Description 1 (g)");

                Log.e("Protein",name);
                food_name.add(name+" ("+calories+")");
//                food_protion.add(protein);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        food_spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,food_name));
//        portion_spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,food_protion));

    }

}