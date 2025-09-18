package com.example.listycitylab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<City> cityList;
    private CityArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.city_list);
        FloatingActionButton addButton = findViewById(R.id.button_add_city);

        cityList = new ArrayList<>();
        cityList.add(new City("Edmonton", "AB"));
        cityList.add(new City("Vancouver", "BC"));
        adapter = new CityArrayAdapter(this, cityList);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(v -> {
            AddCityFragment dialog = new AddCityFragment();
            FragmentManager fm = getSupportFragmentManager();
            dialog.show(fm, "AddCity");
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            City clicked = (City) parent.getItemAtPosition(position);
            AddCityFragment dialog = AddCityFragment.newInstance(clicked);
            dialog.show(getSupportFragmentManager(), "EditCity");
        });
    }

    public void addCity(City city) {
        cityList.add(city);
        adapter.notifyDataSetChanged();
    }

    public void refresh() {
        adapter.notifyDataSetChanged();
    }
}