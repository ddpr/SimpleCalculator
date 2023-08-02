package com.zybooks.simplecalculator;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.StringTokenizer;

public class CommonFunctions extends Fragment {

    public TextView funcResult;

    private static final String[] PRESET_FUNCTIONS = {
            "2 + 2",
            "5 / 5",
            "10 * 10",
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common_functions, container, false);

        funcResult = view.findViewById(R.id.functionresult);
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

        functionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected pre-set function
                String selectedFunction = PRESET_FUNCTIONS[position];

                // Start the AsyncTask to solve the selected pre-set function
                new SolveFunctionTask().execute(selectedFunction);
            }
        });

        return view;
    }
    private class SolveFunctionTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            // Perform the computation for the selected function in the background
            String selectedFunction = params[0];

            double r = Calculate(selectedFunction);

            String result = "Result of " + selectedFunction + "=" + r;
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            showResultInTextView(result);
        }
    }

    public void showResultInTextView(String result) {
        funcResult.setText(result);
    }

    public double Calculate(String s){
        StringTokenizer tokenizer = new StringTokenizer(s);

        double result = 0;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.matches("\\d+")) { // Check if the token is a number using regular expression
                int number = Integer.parseInt(token);
                if(token.equals("+")){
                    result += number;
                } else if (token.equals("-")) {
                    result -= number;
                }else if (token.equals("*")) {
                    result *= number;
                }else if (token.equals("/")) {
                    result /= number;
                }
            }
        }
        return result;
    }
}






