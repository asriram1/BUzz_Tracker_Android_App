package com.example.karanraj.chauhan.courseplanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import static java.lang.reflect.Modifier.STATIC;

/**
 * Created by karanraj on 12/3/16.
 */

public class PacerActivity extends AppCompatActivity {

    private static final String TAG = "PacerActivity";

    private BeverageInfoLayout beverage1InfoLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacer);

        final String[] BEVERAGE_OPTIONS_ARRAY = {"Beer", "Vodka", "Wine"};

        beverage1InfoLayout = (BeverageInfoLayout) findViewById(R.id.beverage_1_info_layout);

        ArrayAdapter<String> beverageOptionsAdapter = new ArrayAdapter<>(PacerActivity.this,R.layout.support_simple_spinner_dropdown_item,BEVERAGE_OPTIONS_ARRAY);

        Spinner beverage1OptionsSpinner = beverage1InfoLayout.getBeverageOptionsSpinner();

        String beverage1 = "";
        final String[] beverages = {beverage1};

        beverage1OptionsSpinner.setAdapter(beverageOptionsAdapter);

        beverage1OptionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                beverages[0] = parent.getItemAtPosition(position).toString();
                Log.d(TAG, "onItemSelected: successfully edited beverage 1"+beverages[0]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_button_male:
                if (checked)
                    // male selected
                    break;
            case R.id.radio_button_female:
                if (checked)
                    // female selected
                    break;
        }
    }

}
