package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        cityList = findViewById(R.id.city_list);
        Button addCityButton = findViewById(R.id.add_city_button);
        Button deleteCityButton = findViewById(R.id.delete_city_button);
        EditText cityInput = findViewById(R.id.city_input);

        // Initial list of cities
        String[] cities = {"Edmonton", "Vancouver"};

        // Initialize the data list and adapter
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        cityList.setAdapter(cityAdapter);

        // Add city functionality
        addCityButton.setOnClickListener(v -> {
            String cityName = cityInput.getText().toString().trim();
            if (!cityName.isEmpty() && !dataList.contains(cityName)) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                cityInput.setText("");
                Toast.makeText(MainActivity.this, "City added!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Invalid or duplicate city!", Toast.LENGTH_SHORT).show();
            }
        });

        // Delete city functionality
        deleteCityButton.setOnClickListener(v -> {
            String cityName = cityInput.getText().toString().trim();
            if (dataList.remove(cityName)) {
                cityAdapter.notifyDataSetChanged();
                cityInput.setText("");
                Toast.makeText(MainActivity.this, "City deleted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "City not found!", Toast.LENGTH_SHORT).show();
            }
        });

        // Select city from the list
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCity = dataList.get(position);
            cityInput.setText(selectedCity);
        });
    }
}