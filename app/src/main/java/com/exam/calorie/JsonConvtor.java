package com.exam.calorie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class JsonConvtor extends AppCompatActivity {

    String data,id,name,food_group,Calories,Fat,Protein,Carbohydrate,Serving;
    StringBuilder text;
    TextView textnew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_convtor);

        textnew = findViewById(R.id.text);

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

            textnew.setText(stringBuilder);

        }
        try {
            is.close();
            fi();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Toast.makeText(getBaseContext(), stringBuilder.toString(), Toast.LENGTH_LONG).show();

    }


    public void fi() {
        try {

            JSONObject js = new JSONObject(data);
            JSONArray jsonArray = js.getJSONArray("Sheet1");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                id = jsonObject1.getString("ID");
                name = jsonObject1.getString("name");
                food_group = jsonObject1.getString("Food Group");
                Calories = jsonObject1.getString("Calories");
                Fat = jsonObject1.getString("Fat (g)");
                Protein = jsonObject1.getString("Protein (g)");
                Carbohydrate = jsonObject1.getString("Carbohydrate (g)");
                Serving = jsonObject1.getString("Serving Description 1 (g)");

                Log.d("Protein",name);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}