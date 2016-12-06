package com.example.karanraj.chauhan.courseplanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.value;
import static com.example.karanraj.chauhan.courseplanner.R.id.tv;
import static com.example.karanraj.chauhan.courseplanner.R.string.weight;

/**
 * Created by karanraj on 12/3/16.
 */

public class PacerActivity extends AppCompatActivity {

    private final static String TAG = "PacerActivity";

    private int weightHundreds = 0, weightTens = 0, weightOnes = 0;

    private int numberOfBeverages = 1;

    private List<String> beverageNamesSelected = new ArrayList<>(15);
    private List<Integer> quantitiesSelected = new ArrayList<>(15);
    private List<String> timesSelected = new ArrayList<>(15);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacer);
        
        // NumberPicker for hundreds value of weight
        NumberPicker weightHundredsNumberPicker = (NumberPicker) findViewById(R.id.pacer_weight_hundreds);

        // NumberPicker for tens value of weight
        NumberPicker weightTensNumberPicker = (NumberPicker) findViewById(R.id.pacer_weight_tens);

        // NumberPicker for ones value of weight
        NumberPicker weightOnesNumberPicker = (NumberPicker) findViewById(R.id.pacer_weight_ones);

        // Specify the maximum and minimum digit for all NumberPickers
        weightHundredsNumberPicker.setMaxValue(3);
        weightHundredsNumberPicker.setMinValue(0);
        weightTensNumberPicker.setMaxValue(9);
        weightTensNumberPicker.setMinValue(0);
        weightOnesNumberPicker.setMaxValue(9);
        weightOnesNumberPicker.setMinValue(0);

        // Set whether the selector wheel wraps on reaching the min/max value.
        weightHundredsNumberPicker.setWrapSelectorWheel(true);
        weightTensNumberPicker.setWrapSelectorWheel(true);
        weightOnesNumberPicker.setWrapSelectorWheel(true);

        // Set value change listeners for NumberPickers
        weightHundredsNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Display the newly selected number from picker
                weightHundreds = picker.getValue();
            }

        });
        weightTensNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Display the newly selected number from picker
                weightTens = picker.getValue();
            }

        });
        weightOnesNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Display the newly selected number from picker
                weightOnes = picker.getValue();
            }

        });

        setUpSpinnersInActivity();

//        Log.d(TAG, "collected info: "+weightHundreds+weightTens+weightOnes+" "+beverageNamesSelected.get(0)+" "+quantitiesSelected.get(0)+" "+timesSelected.get(0));

    }

    private void setUpSpinnersInActivity() {

        // Spinners for beverage options & info, quantity, time of consumption
        Spinner beverageOptionsSpinner = (Spinner) findViewById(R.id.beverage_options_spinner);
        Spinner quantitySpinner = (Spinner) findViewById(R.id.quantity_spinner);
        Spinner timeSpinner = (Spinner) findViewById(R.id.time_spinner);

        // Arrays containing options inside spinners that user can select
        final String[] BEVERAGE_OPTIONS_ARRAY = {"Regular Beer (5%, 12oz)", "Light Beer (4%, 12oz)",
                "Table Wine (12%, 5oz)", "Wine Cooler (5%, 12oz)", "Vodka (40%, 1.25oz)",
                "Gin (40%, 1.25oz)", "Rum (40%, 1.25oz)", "Tequila (40%, 1.25oz)",
                "Bourbon (40%, 1.25oz)", "Scotch (40%, 1.25oz)"};

        final Integer[] QUANTITY_OPTIONS_ARRAY = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        final String[] TIME_OPTIONS_ARRAY = {"00:00", "00:30", "01:00", "01:30", "02:00", "02:30",
                "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00",
                "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
                "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00",
                "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30",
                "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"};

        // Creating and setting adapters for spinners
        ArrayAdapter<String> beverageOptionsSpinnerAdapter = new ArrayAdapter<String>(PacerActivity.this,
                R.layout.support_simple_spinner_dropdown_item, BEVERAGE_OPTIONS_ARRAY);
        ArrayAdapter<Integer> quantitySpinnerAdapter = new ArrayAdapter<Integer>(PacerActivity.this,
                R.layout.support_simple_spinner_dropdown_item, QUANTITY_OPTIONS_ARRAY);
        ArrayAdapter<String> timeSpinnerAdapter = new ArrayAdapter<String>(PacerActivity.this,
                R.layout.support_simple_spinner_dropdown_item, TIME_OPTIONS_ARRAY);

        beverageOptionsSpinner.setAdapter(beverageOptionsSpinnerAdapter);
        quantitySpinner.setAdapter(quantitySpinnerAdapter);
        timeSpinner.setAdapter(timeSpinnerAdapter);

        // Set up onItemSelectedListeners to update beverageNamesSelected, quantitiesSelected,
        // timeSpinner every time user inters with the respective spinners
//        beverageOptionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                beverageNamesSelected.set(numberOfBeverages-1, parent.getSelectedItem().toString());
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                quantitiesSelected.set(numberOfBeverages-1, ((Integer) parent.getSelectedItem()));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                timesSelected.set(numberOfBeverages-1, parent.getSelectedItem().toString());
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    public void onRadioButtonClicked(View view) {

        double genderConstant;

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_button_male:
                if (checked)
                    genderConstant = 0.73;  // standard value for males
                break;
            case R.id.radio_button_female:
                if (checked)
                    genderConstant = 0.66;  // standard value for females
                break;
        }
    }


}