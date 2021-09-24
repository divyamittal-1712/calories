package com.exam.calorie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.exam.calorie.R;
import com.exam.calorie.adapter.ExpandableAdapter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Daily_Activity extends AppCompatActivity {

    Button add_Food,add_activity;
    ExpandableAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_);

        add_Food = findViewById(R.id.add_food);
        add_activity = findViewById(R.id.add_activity);
        expListView = findViewById(R.id.daily_list);

        add_Food.setOnClickListener(v -> {
            Intent intent = new Intent(Daily_Activity.this, Input_Activity.class);
            intent.putExtra("abc","food");
            startActivity(intent);
        });

        add_activity.setOnClickListener(v -> {
            Intent intent = new Intent(Daily_Activity.this, Input_Activity.class);
            intent.putExtra("activity","act");
            startActivity(intent);
        });

        prepareListData();

        listAdapter = new ExpandableAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Breakfast");
        listDataHeader.add("Lunch");
        listDataHeader.add("Snacks");
        listDataHeader.add("Dinner");

        // Adding child data
        List<String> breakfast = new ArrayList<String>();
        breakfast.add("The Shawshank Redemption");
        breakfast.add("The Godfather");
        breakfast.add("The Godfather: Part II");
        breakfast.add("Pulp Fiction");
        breakfast.add("The Good, the Bad and the Ugly");
        breakfast.add("The Dark Knight");
        breakfast.add("12 Angry Men");

        List<String> lunch = new ArrayList<String>();
        lunch.add("The Conjuring");
        lunch.add("Despicable Me 2");
        lunch.add("Turbo");
        lunch.add("Grown Ups 2");
        lunch.add("Red 2");
        lunch.add("The Wolverine");

        List<String> snacks = new ArrayList<String>();
        snacks.add("2 Guns");
        snacks.add("The Smurfs 2");
        snacks.add("The Spectacular Now");
        snacks.add("The Canyons");
        snacks.add("Europa Report");

        List<String> dinner = new ArrayList<String>();
        dinner.add("The Smurfs 2");
        dinner.add("The Spectacular Now");
        dinner.add("The Canyons");
        dinner.add("Europa Report");
        dinner.add("2 Guns");

        listDataChild.put(listDataHeader.get(0), breakfast); // Header, Child data
        listDataChild.put(listDataHeader.get(1), lunch);
        listDataChild.put(listDataHeader.get(2), snacks);
        listDataChild.put(listDataHeader.get(3), dinner);
    }
}