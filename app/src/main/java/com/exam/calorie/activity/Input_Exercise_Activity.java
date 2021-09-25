package com.exam.calorie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.exam.calorie.R;
import com.exam.calorie.utils.DatabaseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Input_Exercise_Activity extends AppCompatActivity {

    Spinner activity_spinner,duration_spinner;
    String data;
    ArrayList<String> activity_type = new ArrayList<>();
    ArrayList<String> duration = new ArrayList<>();
    Button activity_button;
    String pro_name,pro_quntity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_exercise);

        activity_spinner = findViewById(R.id.activity_spinner);
        duration_spinner = findViewById(R.id.duration_spinner);
        activity_button = findViewById(R.id.activity_button);

        for (int i=1; i<=10; i++){
            duration.add(String.valueOf(i));
        }

        duration_spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,duration));

        activity_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pro_name = activity_spinner.getItemAtPosition(activity_spinner.getSelectedItemPosition()).toString();
                Log.e("activity_name", pro_name);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        duration_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pro_quntity = duration_spinner.getItemAtPosition(duration_spinner.getSelectedItemPosition()).toString();
                Log.e("activity_name", pro_quntity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        showActivityData();

        activity_button.setOnClickListener(v -> {
            DatabaseHandler databaseHandler = new DatabaseHandler(this);
            databaseHandler.addActivity(pro_name,pro_quntity);
            startActivity(new Intent(this,Daily_Activity.class));
        });

    }


    public void onActivityClick(View v){

    }

    private void showActivityData(){
        String string = "";
        StringBuilder stringBuilder = new StringBuilder();
        InputStream is = this.getResources().openRawResource(R.raw.activity);
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
            activiy_json();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void activiy_json() {
        try {
            JSONObject js = new JSONObject(data);
            JSONArray jsonArray = js.getJSONArray("Sheet1");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String activity = jsonObject1.getString("ACTIVITY");
                String name = jsonObject1.getString("SPECIFIC MOTION");
                String meTs = jsonObject1.getString("METs");


                Log.e("Protein",activity);
//                food_name.add(name+" ("+calories+")");
//                food_protion.add(protein);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        activity_spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,activity_type));
//        portion_spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,food_protion));

    }

}
