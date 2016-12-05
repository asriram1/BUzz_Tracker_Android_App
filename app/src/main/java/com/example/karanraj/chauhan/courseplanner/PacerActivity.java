package com.example.karanraj.chauhan.courseplanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioButton;

import static android.R.attr.value;
import static com.example.karanraj.chauhan.courseplanner.R.id.tv;

/**
 * Created by karanraj on 12/3/16.
 */

public class PacerActivity extends AppCompatActivity {
    
    final static String TAG = "PacerActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacer);

        int weightHundreds = 0, weightTens = 0, weightOnes = 0;
        final int[] weightDigits = {weightHundreds, weightTens, weightOnes};

        // NumberPicker for hundreds value of weight
        NumberPicker weightHundredsNumberPicker = (NumberPicker) findViewById(R.id.pacer_weight_hundreds);

        // Specify the maximum and minimum digit for NumberPicker
        weightHundredsNumberPicker.setMaxValue(9);
        weightHundredsNumberPicker.setMinValue(0);

        // Set whether the selector wheel wraps on reaching the min/max value.
        weightHundredsNumberPicker.setWrapSelectorWheel(true);

        // Set a value change listener for NumberPicker
        weightHundredsNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Display the newly selected number from picker
                weightDigits[0] = picker.getValue();
                Log.d(TAG, "onValueChange: hundreds " + weightDigits[0]);
            }

        });


        // NumberPicker for tens value of weight
        NumberPicker weightTensNumberPicker = (NumberPicker) findViewById(R.id.pacer_weight_tens);

        // Specify the maximum and minimum digit for NumberPicker
        weightTensNumberPicker.setMaxValue(9);
        weightTensNumberPicker.setMinValue(0);

        // Set whether the selector wheel wraps on reaching the min/max value.
        weightTensNumberPicker.setWrapSelectorWheel(true);

        // Set a value change listener for NumberPicker
        weightTensNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Display the newly selected number from picker
                weightDigits[1] = picker.getValue();
                Log.d(TAG, "onValueChange: tens "+weightDigits[1]);
            }

        });


        // NumberPicker for ones value of weight
        NumberPicker weightOnesNumberPicker = (NumberPicker) findViewById(R.id.pacer_weight_ones);

        // Specify the maximum and minimum digit for NumberPicker
        weightOnesNumberPicker.setMaxValue(9);
        weightOnesNumberPicker.setMinValue(0);

        // Set whether the selector wheel wraps on reaching the min/max value.
        weightOnesNumberPicker.setWrapSelectorWheel(true);

        // Set a value change listener for NumberPicker
        weightOnesNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Display the newly selected number from picker
                weightDigits[2] = picker.getValue();
                Log.d(TAG, "onValueChange: ones " + weightDigits[2]);
            }

        });

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
