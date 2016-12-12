package com.example.karanraj.chauhan.courseplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by anand
 */

public class SoberUpActivity extends AppCompatActivity {
    private final static String TAG = "SoberUpActivity";
    private int weightHundreds = 0, weightTens = 0, weightOnes = 0;
    private int beerBottles = 0, wineGlass = 0, vodkaShots = 0, liquorGlass = 0;
    double genderConstant;
    int userWeight;


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

        // Set whether the selector wheel wraps on reaching the min/max value.
        boolean set_wrap = false;
        weightHundredsNumberPicker.setWrapSelectorWheel(set_wrap);
        weightTensNumberPicker.setWrapSelectorWheel(set_wrap);
        weightOnesNumberPicker.setWrapSelectorWheel(set_wrap);

        //Alcohol quantity number pickers
        NumberPicker beerNumberPicker = (NumberPicker) findViewById(R.id.beer_one_up);
        NumberPicker vodkaNumberPicker = (NumberPicker) findViewById(R.id.vodka_one_up);
        NumberPicker wineNumberPicker = (NumberPicker) findViewById(R.id.wine_one_up);
        NumberPicker liquorNumberPicker = (NumberPicker) findViewById(R.id.liquor_one_up);



        //Max and min digits for alcohol num pickers
        int maxAlcoholValue = 15;
        int minAlcoholValue = 0;
        beerNumberPicker.setMaxValue(maxAlcoholValue);
        vodkaNumberPicker.setMaxValue(maxAlcoholValue);
        wineNumberPicker.setMaxValue(maxAlcoholValue);
        liquorNumberPicker.setMaxValue(maxAlcoholValue);
        beerNumberPicker.setMinValue(minAlcoholValue);
        vodkaNumberPicker.setMinValue(minAlcoholValue);
        wineNumberPicker.setMinValue(minAlcoholValue);
        liquorNumberPicker.setMinValue(minAlcoholValue);

        //loop (wrap) selection for alcohol num pickers
        beerNumberPicker.setWrapSelectorWheel(set_wrap);
        vodkaNumberPicker.setWrapSelectorWheel(set_wrap);
        wineNumberPicker.setWrapSelectorWheel(set_wrap);
        liquorNumberPicker.setWrapSelectorWheel(set_wrap);


    }




    public void goButtonPressed(View view){
        NumberPicker weightHundredsNumberPicker = (NumberPicker) findViewById(R.id.sober_weight_hundreds);
        NumberPicker weightTensNumberPicker = (NumberPicker) findViewById(R.id.sober_weight_tens);
        NumberPicker weightOnesNumberPicker = (NumberPicker) findViewById(R.id.sober_weight_ones);
        userWeight=(100*weightHundredsNumberPicker.getValue()+10*weightTensNumberPicker.getValue()+weightOnesNumberPicker.getValue());

        //Alcohol quantity number pickers
        NumberPicker beerNumberPicker = (NumberPicker) findViewById(R.id.beer_one_up);
        NumberPicker vodkaNumberPicker = (NumberPicker) findViewById(R.id.vodka_one_up);
        NumberPicker wineNumberPicker = (NumberPicker) findViewById(R.id.wine_one_up);
        NumberPicker liquorNumberPicker = (NumberPicker) findViewById(R.id.liquor_one_up);

        beerBottles = beerNumberPicker.getValue();
        vodkaShots = vodkaNumberPicker.getValue();
        wineGlass = wineNumberPicker.getValue();
        liquorGlass = liquorNumberPicker.getValue();


        //Log.d(TAG, "onValueChange: weightHundreds "+ userWeight );
        RadioGroup sexRadioButton = (RadioGroup) findViewById(R.id.sex_radio_group_sober);
        //Log.d(TAG, "onValueChange: gender value "+ sexRadioButton.getCheckedRadioButtonId());
        if(sexRadioButton.getCheckedRadioButtonId()==-1){
            Toast.makeText(SoberUpActivity.this,"Please select a gender", Toast.LENGTH_LONG).show();
            return;
        }
        if(sexRadioButton.getCheckedRadioButtonId()==R.id.radio_button_female_sober){
            genderConstant=0.66; //Female gender constant
            Log.d(TAG, "onValueChange: Gender is female ");

        }
        if(sexRadioButton.getCheckedRadioButtonId()==R.id.radio_button_male_sober){
            genderConstant=0.73; //Male gender constant
            Log.d(TAG, "onValueChange: Gender is male ");
        }
        if(userWeight==0){
            Toast.makeText(SoberUpActivity.this,"Please enter your weight", Toast.LENGTH_LONG).show();
            return;
        }else {
            Log.d(TAG, "onValueChange: User Weight is "+ userWeight);
        }
        if(liquorGlass +vodkaShots+wineGlass+beerBottles==0){
            Toast.makeText(SoberUpActivity.this,"Please add at least one beverage to proceed", Toast.LENGTH_LONG).show();
            return;
        }
        ArrayList<Double> BACArrayList_final = BACCalculatorFunctions.soberalcoholcalculator(genderConstant,userWeight,beerBottles,vodkaShots,wineGlass,liquorGlass);
        Intent intentToResultsActivity = new Intent(SoberUpActivity.this, ResultsActivity.class);
//        intentToResultsActivity.putExtra("beerBottleNum", beerBottles);
//        intentToResultsActivity.putExtra("vodkaShotNum", vodkaShots);
//        intentToResultsActivity.putExtra("liquorGlassNum", liquorGlass);
//        intentToResultsActivity.putExtra("wineGlassNum", wineGlass);
//        intentToResultsActivity.putExtra("userWeightVal", userWeight);
//        intentToResultsActivity.putExtra("genderConstantVal", genderConstant);
        double[] bacArray = new double[BACArrayList_final.size()];
        for (int i = 0; i < BACArrayList_final.size(); i++) {
            bacArray[i] = BACArrayList_final.get(i);
        }
        intentToResultsActivity.putExtra("bacArray", bacArray);
        //Log.d(TAG, "goButtonPressed: "+ BACArrayList_final.get(0));


//        intentToResultsActivity.putExtra("bacArray",BACArrayList_final.toArray());
        intentToResultsActivity.putExtra("TAG", TAG);

        startActivity(intentToResultsActivity);


    }
    public void onPrevButtonClick(View view){
        Intent intentToMainActivity = new Intent(SoberUpActivity.this, MainActivity.class);
        startActivity(intentToMainActivity);

    }
    public void onBeerButtonClick(View view){


        Toast.makeText(SoberUpActivity.this,"Beer ", Toast.LENGTH_LONG).show();


    }
    public void onWineButtonClick(View view){
        Toast.makeText(SoberUpActivity.this,"Wine XXXXXXXXXX", Toast.LENGTH_LONG).show();
    }
    public void onWhiskeyButtonClick(View view){
        Toast.makeText(SoberUpActivity.this,"Whiskey XXXXXXXXXX", Toast.LENGTH_LONG).show();
    }
    public void onVodkaButtonClick(View view){
        Toast.makeText(SoberUpActivity.this,"Vodka XXXXXXXXXX", Toast.LENGTH_LONG).show();
    }
    public void onRadioButtonClicked_sober(View view) {
        // Is the button now checked?

        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_button_male_sober:
                if (checked) {
                    genderConstant = 0.73;
                }// gender constant r for males
                break;
            case R.id.radio_button_female_sober:
                if (checked) {
                    genderConstant = 0.66;
                }// gender constant r for females
                break;
        }
    }


}

