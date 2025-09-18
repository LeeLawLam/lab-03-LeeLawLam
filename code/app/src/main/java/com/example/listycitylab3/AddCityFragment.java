package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {

    private EditText cityText;
    private EditText provinceText;

    public AddCityFragment() {
        // empty constructor
    }

    public static AddCityFragment newInstance(City city) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        AddCityFragment fragment = new AddCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_city, null);

        cityText = view.findViewById(R.id.edit_text_city_text);
        provinceText = view.findViewById(R.id.edit_text_province_text);

        Bundle args = getArguments();
        City editCity = null;
        if (args != null) {
            editCity = (City) args.getSerializable("city");
        }

        if (editCity != null) {
            cityText.setText(editCity.getCityName());
            provinceText.setText(editCity.getProvinceName());
        }

        City finalEditCity = editCity;

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view)
                .setTitle(editCity == null ? "Add City" : "Edit City")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Save", (dialog, which) -> {
                    String cityName = cityText.getText().toString();
                    String provinceName = provinceText.getText().toString();

                    MainActivity parent = (MainActivity) getActivity();

                    if (finalEditCity != null) {
                        finalEditCity.setCityName(cityName);
                        finalEditCity.setProvinceName(provinceName);
                        if (parent != null) parent.refresh();
                    } else {
                        City newCity = new City(cityName, provinceName);
                        if (parent != null) parent.addCity(newCity);
                    }
                });

        return builder.create();
    }
}