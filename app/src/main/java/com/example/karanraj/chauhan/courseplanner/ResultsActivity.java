package com.example.karanraj.chauhan.courseplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;


/**
 * Created by karanraj on 12/7/16.
 */

public class ResultsActivity extends AppCompatActivity {
    private static final String TAG = "ResultsActivity";
    DecimalFormat decimalFormat = new DecimalFormat("00");

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

        // Image view that will navigate to the previous activity
        ImageView previousImageView = (ImageView) findViewById(R.id.results_previous_arrow_image_view);
        previousImageView.setOnClickListener(new View.OnClickListener() {
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
            int rowcheck = 0;
            int numevents = receivedBeverageIntakes.size();
            int startTime = intakeTimes[0];
            int endTime = intakeTimes[numevents - 1];//Check casting

            if (endTime == startTime) {
                endTime = startTime+1;
            }
            startTime = startTime/100;
            endTime = endTime/100 + 1;
            int[] timeArray = new int[(endTime - startTime)+1];
            double[] BACArray = new double[(endTime - startTime)+1];
            BACArray[0] = 0;
            int counter = 0;

            for (int t = startTime*100; t <= endTime*100; t = t+100) //iterate from first given time to last given time in array
            {
                timeArray[counter] = t%2400;
                if (counter != 0) {
                    BACArray[counter] = BACArray[counter - 1] - 0.015; // give the t-1 value to t DEREFERENCE ACCORDINGLY
                    if (BACArray[counter] < 0) {
                        BACArray[counter] = 0;
                    }
                }

                for (int r = rowcheck; r < numevents; r++) {
                    if (intakeTimes[r] > t) {
                        break;
                    } else {
                        BACArray[counter] = BACArray[counter] + intakeBAC[r];//calculate and add BAC to appropriate index
                        rowcheck++;
                    }
                }

                counter++;
            }

            TableLayout pacerResultsTableLayout = (TableLayout)findViewById(R.id.BAC_table);
            pacerResultsTableLayout.setStretchAllColumns(true);
            pacerResultsTableLayout.setWeightSum(2);
//            pacerResultsTableLayout.bringToFront();
            TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            tableLayoutParams.setMargins(1,1,1,1);

            for(int i = 0; i < timeArray.length-1; i++) {

                TableRow tableRow = new TableRow(this);
                tableRow.setGravity(Gravity.CENTER);
                if (BACArray[i] < 0.05) {
                    tableRow.setBackgroundColor(getResources().getColor(R.color.green));
                } else if (BACArray[i] < 0.12) {
                    tableRow.setBackgroundColor(getResources().getColor(R.color.yellow));
                } else {
                    tableRow.setBackgroundColor(getResources().getColor(R.color.red));
                }
                tableRow.setLayoutParams(tableLayoutParams);

                TextView timeTextView = new TextView(this);
                TextView bacTextView = new TextView(this);

                timeTextView.setPadding(0,8,0,8);
                bacTextView.setPadding(0,8,0,8);

                timeTextView.setGravity(Gravity.CENTER);
                bacTextView.setGravity(Gravity.CENTER);

//                Log.d(TAG, "onCreate:timearray[i] "+timeArray[i]+" "+decimalFormat.format(timeArray[i]/100));
                timeTextView.setText((""+decimalFormat.format(timeArray[i]/100)+":00"));
                bacTextView.setText(new DecimalFormat("0.000").format(BACArray[i]));

                timeTextView.setTextSize(20);
                bacTextView.setTextSize(20);

                tableRow.addView(timeTextView);
                tableRow.addView(bacTextView);
                pacerResultsTableLayout.addView(tableRow);
            }

            int lastTime = timeArray[timeArray.length-1];
            double lastBac = BACArray[BACArray.length-1];
            ArrayList<Double> remainderBacArrayList = new ArrayList<>();
            while (lastBac > 0.05) {
                lastBac -= 0.015;
                remainderBacArrayList.add(lastBac);
            }
            for (double currentBac : remainderBacArrayList) {
                TableRow tableRow = new TableRow(this);

                tableRow.setGravity(Gravity.CENTER);
                if (currentBac < 0.05) {
                    tableRow.setBackgroundColor(getResources().getColor(R.color.green));
                } else if (currentBac < 0.12) {
                    tableRow.setBackgroundColor(getResources().getColor(R.color.yellow));
                } else {
                    tableRow.setBackgroundColor(getResources().getColor(R.color.red));
                }
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

                timeTextView.setText(""+decimalFormat.format(lastTime)+":00");
                bacTextView.setText(new DecimalFormat("0.000").format(currentBac));

                tableRow.addView(timeTextView);
                tableRow.addView(bacTextView);

                pacerResultsTableLayout.addView(tableRow);

                if (lastTime<23) {
                    lastTime++;
                } else {
                    lastTime = 0;
                }

            }


        } else if(previousActivityTag.equals("SoberUpActivity")) {

            double[] bacValuesArray = data.getDoubleArray("bacArray");

            Log.d(TAG, "received arra ylist of size"+ bacValuesArray.length);

            for (double d : bacValuesArray) {
                Log.d(TAG, "onCreate: double is "+d);
            }

            createResultTable(bacValuesArray);

        }
    }

    private void createResultTable(double[] bacValuesArray) {
        Calendar calendar = Calendar.getInstance();
        int hours = calendar.getTime().getHours();
        int minutes = calendar.getTime().getMinutes();

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
            if (currentBac < 0.05) {
                tableRow.setBackgroundColor(getResources().getColor(R.color.green));
            } else if (currentBac < 0.12) {
                tableRow.setBackgroundColor(getResources().getColor(R.color.yellow));
            } else {
                tableRow.setBackgroundColor(getResources().getColor(R.color.red));
            }
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
    }
}