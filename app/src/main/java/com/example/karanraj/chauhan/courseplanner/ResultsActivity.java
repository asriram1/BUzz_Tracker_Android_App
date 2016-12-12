package com.example.karanraj.chauhan.courseplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import android.widget.LinearLayout;

import android.widget.ImageButton;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import static android.R.attr.data;
import static android.R.attr.focusable;
import static android.R.attr.numberPickerStyle;

/**
 * Created by karanraj on 12/7/16.
 */

public class ResultsActivity extends AppCompatActivity {
    private static final String TAG = "ResultsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Bundle data = getIntent().getExtras();
        final String previousActivityTag = data.getString("TAG");

        // Text view that will navigate to the previous activity
        TextView previousTextView = (TextView) findViewById(R.id.results_previous_text_view);
        previousTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: previous clicked");
                if (previousActivityTag.equals("PacerActivity")) {
                    startActivity(new Intent(ResultsActivity.this, PacerActivity.class));
                } else if (previousActivityTag.equals("SoberUpActivity")) {
                    startActivity(new Intent(ResultsActivity.this, SoberUpActivity.class));
                }
                Log.d(TAG, "onClick: starting activity");
            }
        });

        Log.d(TAG, "onCreate: listener set!!");

        if (previousActivityTag.equals("PacerActivity")) {
            ArrayList<BeverageIntake> receivedBeverageIntakes = data.getParcelableArrayList("beverageIntakesArrayList");

            // Sort the array list according to intake times
            Collections.sort(receivedBeverageIntakes, new Comparator<BeverageIntake>() {
                @Override
                public int compare(BeverageIntake lhs, BeverageIntake rhs) {
                    return lhs.getTime().compareTo(rhs.getTime());
                }
            });

            // Create arrays that have useful data from array lists
            int[] intakeTimes = new int[receivedBeverageIntakes.size()];
            double[] intakeBAC = new double[receivedBeverageIntakes.size()];

            for (int i = 0; i < receivedBeverageIntakes.size(); i++) {
                intakeTimes[i] = receivedBeverageIntakes.get(i).getTime();
                intakeBAC[i] = receivedBeverageIntakes.get(i).getBacAdded();
            }




            // Calculate BAC level at regular intervals
//            int rowcheck = 0;
//            int numevents = receivedBeverageIntakes.size();
//            int startTime = intakeTimes[0];
//            int endTime = intakeTimes[numevents - 1];//Check casting
//
//            int[] timeArray = new int[(int)(Math.ceil((endTime - startTime)/100)*2)+1];
//            double[] BACArray = new double[(int)(Math.ceil((endTime - startTime)/100)*2)+1];
//            BACArray[0] = 0;
//            int counter = 0;
//
//            for (int t = startTime; t < endTime; t++) //iterate from first given time to last given time in array
//            {
//                timeArray[counter] = t;
//                if (counter != 0) {
//                    BACArray[counter] = BACArray[counter - 1] - 0.15; // give the t-1 value to t DEREFERENCE ACCORDINGLY
//                    if (BACArray[counter] < 0) {
//                        BACArray[counter] = 0;
//                    }
//
//                }
//
//                for (int r = rowcheck; r < numevents; r++) {
//                    if (intakeTimes[r] > t) {
//                        break;
//                    } else {
//                        BACArray[counter] = BACArray[counter] + intakeBAC[r];//calculate and add BAC to appropriate index
//                        rowcheck++;
//                    }
//                }
//
//                counter++;
//            }



            // TODO: 12/10/16 anirudhs code. add beautified table here

            TableLayout BAC = (TableLayout)findViewById(R.id.BAC_table);
            BAC.setStretchAllColumns(true);
            BAC.bringToFront();
            TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            BAC.setWeightSum(2);

            tableLayoutParams.setMargins(1,1,1,1);

            for(int i = 0; i < receivedBeverageIntakes.size(); i++) {

                TableRow tr = new TableRow(this);
                tr.setGravity(Gravity.CENTER);
                tr.setBackgroundColor(getResources().getColor(R.color.grey));
                tr.setLayoutParams(tableLayoutParams);

                TextView c1 = new TextView(this);
                TextView c2 = new TextView(this);
                c2.setPadding(0,8,0,8);
                c1.setPadding(0,8,0,8);
                c1.setText(""+intakeTimes[i]);
                c2.setText(String.valueOf(""+intakeBAC[i]));

                tr.addView(c1);
                tr.addView(c2);
                BAC.addView(tr);
                c1.setTextSize(20);
                c2.setTextSize(20);

            }


//            // FIXME: 12/9/16 array out of bounds error for loop
//            for (int t = startTime; t < endTime; t++) //iterate from first given time to last given time in array
//            {
//                timeArray[counter] = t;
//                if (counter != 0) {
//                    BACArray[counter] = BACArray[counter - 1] - 0.15; // give the t-1 value to t DEREFERENCE ACCORDINGLY
//                    if (BACArray[counter] < 0) {
//                        BACArray[counter] = 0;
//                    }
//
//                }
//
//                for (int r = rowcheck; r < numevents; r++) {
//                    if (intakeTimes[r] > t) {
//                        break;
//                    } else {
//                        BACArray[counter] = BACArray[counter] + intakeBAC[r];//calculate and add BAC to appropriate index
//                        rowcheck++;
//                    }
//                }
//
//                counter++;
//            }
//
//            for (int i = 0; i < BACArray.length; i++) {
//                Log.d(TAG, "bac is "+BACArray[i]+" at time "+timeArray[i]);
//            }


//            previousTextView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startActivity(new Intent(ResultsActivity.this, PacerActivity.class));
//                }
//            });


        } else if(previousActivityTag.equals("SoberUpActivity")) {
//            int beerBottlesNum = data.getInt("beerBottleNum");
//            int vodkaShotsNum = data.getInt("vodkaShotNum");
//            int liquorGlassNum = data.getInt("liquorGlassNum");
//            int wineGlassNum = data.getInt("wineGlassNum");
//            int userWeightInput = data.getInt("userWeightVal");
//            double userGenderConstant = data.getDouble("genderConstantVal");

            double[] bacValuesArray = data.getDoubleArray("bacArray");

            Log.d(TAG, "received arra ylist of size"+ bacValuesArray.length);

            for (double d : bacValuesArray) {
                Log.d(TAG, "onCreate: double is "+d);
            }
            
//            ArrayList<Double> bacValuesArrayList = new ArrayList<>();
//            bacValuesArrayList.add(1.2);
//            bacValuesArrayList.add(1.05);
//            bacValuesArrayList.add(0.9);
//            bacValuesArrayList.add(0.75);
//            bacValuesArrayList.add(0.6);

            createResultTable(bacValuesArray);

        }
    }
    
    private void createResultTable(double[] bacValuesArray) {
        Calendar calendar = Calendar.getInstance();
        int hours = calendar.getTime().getHours();
        int minutes = calendar.getTime().getMinutes();
        DecimalFormat decimalFormat = new DecimalFormat("00");

        TableLayout tableLayout = (TableLayout) findViewById(R.id.BAC_table);
        tableLayout.setStretchAllColumns(true);
        tableLayout.setWeightSum(2);

        // width, height, weight
        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
        // setting margins
        tableLayoutParams.setMargins(1,1,1,1);
        
        for (double currentBac : bacValuesArray) {
            TableRow tableRow = new TableRow(this);

            tableRow.setGravity(Gravity.CENTER);
            tableRow.setBackgroundColor(getResources().getColor(R.color.grey));
            tableRow.setLayoutParams(tableLayoutParams);

            TextView timeTextView = new TextView(this);
            TextView bacTextView = new TextView(this);
            TextView separatorTextView = new TextView(this);
            separatorTextView.setHeight(1);

            timeTextView.setPadding(0,8,0,8);
            bacTextView.setPadding(0,8,0,8);

            timeTextView.setGravity(Gravity.CENTER);
            bacTextView.setGravity(Gravity.CENTER);

            timeTextView.setTextSize(20);
            bacTextView.setTextSize(20);

            timeTextView.setText(""+decimalFormat.format(hours)+":"+decimalFormat.format(minutes));
            bacTextView.setText(""+currentBac);

            tableRow.addView(timeTextView);
            tableRow.addView(bacTextView);

            tableLayout.addView(tableRow);

            if (hours<23) {
                hours++;
            } else {
                hours = 0;
            }

        }

        TableRow tableRow = new TableRow(this);

        tableRow.setGravity(Gravity.CENTER);
        tableRow.setBackgroundColor(getResources().getColor(R.color.grey));
        tableRow.setLayoutParams(tableLayoutParams);

        TextView timeTextView = new TextView(this);
        TextView bacTextView = new TextView(this);
        TextView separatorTextView = new TextView(this);
        separatorTextView.setHeight(1);

        timeTextView.setPadding(0,8,0,8);
        bacTextView.setPadding(0,8,0,8);

        timeTextView.setGravity(Gravity.CENTER);
        bacTextView.setGravity(Gravity.CENTER);

        timeTextView.setTextSize(20);
        bacTextView.setTextSize(20);

        timeTextView.setText(""+decimalFormat.format(hours)+":"+decimalFormat.format(minutes));
        bacTextView.setText("Sober Now!");

        tableRow.addView(timeTextView);
        tableRow.addView(bacTextView);

        tableLayout.addView(tableRow);
    }
}