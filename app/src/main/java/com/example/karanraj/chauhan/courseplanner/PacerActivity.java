package com.example.karanraj.chauhan.courseplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.x;

/**
 * Created by karanraj
 * Pacer functionality of app gives the user an estimate of their BAC at regular intervals of time
 * based on alcohol consumption history
 */


public class PacerActivity extends AppCompatActivity {

    private final static String TAG = "PacerActivity";

    // Constant value used in BAC calculation. Its value is 0.73 for males, 0.66 for females
    private double mGenderConstant = 0;

    // Stores weight of the user according to inputs from NumberPickers
    private int mUserWeight = 0;

    // Spinners for beverage options & info, quantity, time of consumption
    private Spinner mBeverageOptionsSpinner;
    private Spinner mQuantitySpinner;
    private Spinner mTimeSpinner;

    // NumberPickers for hundreds, tens, and ones digits of weight
    private NumberPicker mWeightHundredsNumberPicker;
    private NumberPicker mWeightTensNumberPicker;
    private NumberPicker mWeightOnesNumberPicker;

    // ArrayList that will contain all user inputs about beverages consumption, i.e., name, quantity and time
    private ArrayList<BeverageIntake> beverageIntakes = new ArrayList<>(5);

    private TableLayout mBeverageIntakesTable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacer);

        // Find by id and assign NumberPickers
        mWeightHundredsNumberPicker = (NumberPicker) findViewById(R.id.pacer_weight_hundreds);
        mWeightTensNumberPicker = (NumberPicker) findViewById(R.id.pacer_weight_tens);
        mWeightOnesNumberPicker = (NumberPicker) findViewById(R.id.pacer_weight_ones);

        // Specify the maximum and minimum digit for all NumberPickers
        mWeightHundredsNumberPicker.setMaxValue(3);
        mWeightHundredsNumberPicker.setMinValue(0);
        mWeightTensNumberPicker.setMaxValue(9);
        mWeightTensNumberPicker.setMinValue(0);
        mWeightOnesNumberPicker.setMaxValue(9);
        mWeightOnesNumberPicker.setMinValue(0);

        // Set whether the selector wheel wraps on reaching the min/max value.
        mWeightHundredsNumberPicker.setWrapSelectorWheel(true);
        mWeightTensNumberPicker.setWrapSelectorWheel(true);
        mWeightOnesNumberPicker.setWrapSelectorWheel(true);

        // Find by id and assign Spinners
        mBeverageOptionsSpinner = (Spinner) findViewById(R.id.beverage_options_spinner);
        mQuantitySpinner = (Spinner) findViewById(R.id.quantity_spinner);
        mTimeSpinner = (Spinner) findViewById(R.id.time_spinner);

        // Arrays containing options inside spinners that user can select
        String[] BEVERAGE_OPTIONS_ARRAY = {"Regular Beer (5%, 12oz)", "Light Beer (4%, 12oz)",
                "Table Wine (12%, 5oz)", "Wine Cooler (5%, 12oz)", "Vodka (40%, 1.25oz)",
                "Gin (40%, 1.25oz)", "Rum (40%, 1.25oz)", "Tequila (40%, 1.25oz)",
                "Bourbon (40%, 1.25oz)", "Scotch (40%, 1.25oz)"};

        Integer[] QUANTITY_OPTIONS_ARRAY = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        String[] TIME_OPTIONS_ARRAY = {"00:00", "00:30", "01:00", "01:30", "02:00", "02:30",
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

        mBeverageOptionsSpinner.setAdapter(beverageOptionsSpinnerAdapter);
        mQuantitySpinner.setAdapter(quantitySpinnerAdapter);
        mTimeSpinner.setAdapter(timeSpinnerAdapter);

        mBeverageIntakesTable = (TableLayout) findViewById(R.id.beverage_intakes_table_layout);

        if (mBeverageIntakesTable.getChildCount() != 0) {
            mBeverageIntakesTable.removeAllViews();
        }

        final float sourceTextSize = ((TextView) findViewById(R.id.label_quantity)).getTextSize();

        Button deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (beverageIntakes.size()>0) {
                    beverageIntakes.remove(beverageIntakes.size() - 1);
                    mBeverageIntakesTable.removeViewAt(mBeverageIntakesTable.getChildCount()-1);
                    Toast.makeText(PacerActivity.this, "Removed!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(PacerActivity.this, "No beverages added yet", Toast.LENGTH_LONG).show();
                }
            }
        });

        // When add button is clicked, the current selections in Spinners are added to the appropriate AraryLists
        Button addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // If the gender constant has not already been defined then define it
                if (mGenderConstant == 0) {
                    int checkedRadioButtonId = ((RadioGroup)findViewById(R.id.sex_radio_group)).getCheckedRadioButtonId();
                    // If gender is not selected then radio button id is -1. In this case, prompt the user to select gender
                    // Set value of gender constant according to user's input
                    switch (checkedRadioButtonId) {
                        case -1:
                            Toast.makeText(PacerActivity.this, "Please select a gender", Toast.LENGTH_LONG).show();
                            return;
                        case R.id.radio_button_male:
                            Log.d(TAG, "onClick: sex is male");
                            mGenderConstant = 0.73;
                            break;
                        case R.id.radio_button_female:
                            Log.d(TAG, "onClick: sex is female");
                            mGenderConstant = 0.66;
                            break;
                        default: return;
                    }
                }


                mUserWeight = +100*mWeightHundredsNumberPicker.getValue()+10*mWeightTensNumberPicker.getValue()
                        +mWeightOnesNumberPicker.getValue();
                Log.d(TAG, "onClick: weight is "+mUserWeight);

                // If the weight is 0, i.e., user did not input weight, then prompt the user for weight
                if (mUserWeight == 0) {
                    Toast.makeText(PacerActivity.this, "Please enter your weight", Toast.LENGTH_LONG).show();
                    return;
                }

                // Properties of current alcohol intake
                String name = mBeverageOptionsSpinner.getSelectedItem().toString();
                int quantity = (int) mQuantitySpinner.getSelectedItem();
                String time = mTimeSpinner.getSelectedItem().toString();
                double bac = BACCalculatorFunctions.pacerAlcoholCalculator(mGenderConstant, mUserWeight, quantity, name);

                BeverageIntake current = new BeverageIntake(name, quantity, time, bac);
                beverageIntakes.add(current);

                Toast.makeText(PacerActivity.this, "Added!", Toast.LENGTH_SHORT).show();

                // Update the table layout to show the last added beverage intake information
                TableRow tr = new TableRow(PacerActivity.this);
                tr.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                TextView tv = new TextView(PacerActivity.this);
                tv.setPadding(8,8,8,8);
                tv.setText(quantity + "x " + name + " at " + time);
                tv.setTextSize(sourceTextSize / getResources().getDisplayMetrics().density);
                tr.addView(tv);

                mBeverageIntakesTable.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
                ((ScrollView) findViewById(R.id.pacer_scroll_view)).fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

        // Text view that will navigate to the previous activity
        TextView previousTextView = (TextView) findViewById(R.id.pacer_previous_text_view);
        previousTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(PacerActivity.this, MainActivity.class));
            }
        });

        // Image view that will navigate to the previous activity
        ImageView previousImageView = (ImageView) findViewById(R.id.pacer_previous_arrow_image_view);
        previousImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PacerActivity.this, MainActivity.class));
            }
        });


        // Text view that will navigate to the next activity
        TextView nextTextView = (TextView) findViewById(R.id.pacer_next_text_view);
        nextTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Next activity will not be started until all the required user input is received
                if (beverageIntakes.size() == 0) {
                    Toast.makeText(PacerActivity.this, "Please add at least one beverage to proceed",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                // Add the array list of BeverageIntake objects and current activity identifier (TAG) and
                // start the next activity
                Intent intentToResultsActivity = new Intent(PacerActivity.this, ResultsActivity.class);
                intentToResultsActivity.putParcelableArrayListExtra("beverageIntakesArrayList", beverageIntakes);
                intentToResultsActivity.putExtra("TAG", TAG);
                startActivity(intentToResultsActivity);
            }
        });

        // Text view that will navigate to the next activity
        ImageView nextImageView = (ImageView) findViewById(R.id.pacer_next_arrow_image_view);
        nextImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Next activity will not be started until all the required user input is received
                if (beverageIntakes.size() == 0) {
                    Toast.makeText(PacerActivity.this, "Please add at least one beverage to proceed",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                // Add the array list of BeverageIntake objects and current activity identifier (TAG) and
                // start the next activity
                Intent intentToResultsActivity = new Intent(PacerActivity.this, ResultsActivity.class);
                intentToResultsActivity.putParcelableArrayListExtra("beverageIntakesArrayList", beverageIntakes);
                intentToResultsActivity.putExtra("TAG", TAG);
                startActivity(intentToResultsActivity);
            }
        });

    }
}