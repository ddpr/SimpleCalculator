package com.zybooks.simplecalculator;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CommonFunctions extends Fragment {

    private static final String[] PRESET_FUNCTIONS = {
            "2 + 2",
            "5 / 5",
            "10 * 10",
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common_functions, container, false);

        // Get the reference to the ListView
        ListView functionListView = view.findViewById(R.id.functionListView);

        // Create an ArrayAdapter to populate the ListView with pre-set functions
        ArrayAdapter<String> functionAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                PRESET_FUNCTIONS
        );

        // Set the adapter for the ListView
        functionListView.setAdapter(functionAdapter);

        return view;
    }
}
