package com.example.karanraj.chauhan.courseplanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Scroller;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.graphics.Color;

/**
 * Created by anand
 */

public class SoberUpActivity extends AppCompatActivity {
    private final static String TAG = "SoberActivity";
    private int weightHundreds = 0, weightTens = 0, weightOnes = 0;
    private int beerBottles = 0, wineGlass = 0, vodkaShots = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sober_up);
        //New code
        NumberPicker weightHundredsNumberPicker = (NumberPicker) findViewById(R.id.sober_weight_hundreds);

        // NumberPicker for tens value of weight
        NumberPicker weightTensNumberPicker = (NumberPicker) findViewById(R.id.sober_weight_tens);

        // NumberPicker for ones value of weight
        NumberPicker weightOnesNumberPicker = (NumberPicker) findViewById(R.id.sober_weight_ones);

        // Specify the maximum and minimum digit for all NumberPickers
        weightHundredsNumberPicker.setMaxValue(3);
        weightHundredsNumberPicker.setMinValue(0);
        weightTensNumberPicker.setMaxValue(9);
        weightTensNumberPicker.setMinValue(0);
        weightOnesNumberPicker.setMaxValue(9);
        weightOnesNumberPicker.setMinValue(0);
        boolean set_wrap = false;
        // Set whether the selector wheel wraps on reaching the min/max value.
        weightHundredsNumberPicker.setWrapSelectorWheel(set_wrap);
        weightTensNumberPicker.setWrapSelectorWheel(set_wrap);
        weightOnesNumberPicker.setWrapSelectorWheel(set_wrap);



        //Old Code
        //Get the widgets reference from XML layout

        // NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker1);

        ////Set TextView text color
        // tv.setTextColor(Color.parseColor("#ffd32b3b"));

        //Populate NumberPicker values from minimum and maximum value range
        //Set the minimum value of NumberPicker
        // np.setMinValue(0);
        // //Specify the maximum value/number of NumberPicker
        //np.setMaxValue(9);

        //Gets whether the selector wheel wraps when reaching the min/max value.
        //np.setWrapSelectorWheel(false);

        //Set a value change listener for NumberPicker
        //np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
        //@Override
        //  public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        //Display the newly selected number from picker

        // }

        // });
    }




    public void goButtonPressed(View view){
        NumberPicker weightHundredsNumberPicker = (NumberPicker) findViewById(R.id.sober_weight_hundreds);
        NumberPicker weightTensNumberPicker = (NumberPicker) findViewById(R.id.sober_weight_tens);
        NumberPicker weightOnesNumberPicker = (NumberPicker) findViewById(R.id.sober_weight_ones);
        Log.d(TAG, "onValueChange: weightHundreds "+ (100*weightHundredsNumberPicker.getValue()+10*weightTensNumberPicker.getValue()+weightOnesNumberPicker.getValue()));

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        double genderConstant;
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_button_male:
                if (checked) {
                    genderConstant = 0.73;
                }// gender constant r for males
                break;
            case R.id.radio_button_female:
                if (checked) {
                    genderConstant = 0.66;
                }// gender constant r for females
                break;
        }
    }


}

