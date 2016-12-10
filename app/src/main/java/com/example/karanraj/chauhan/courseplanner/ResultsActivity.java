package com.example.karanraj.chauhan.courseplanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static android.R.attr.data;
import static android.R.attr.numberPickerStyle;

/**
 * Created by karanraj on 12/7/16.
 */

public class ResultsActivity extends AppCompatActivity {
    private static final String TAG = "ResultsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Bundle data = getIntent().getExtras();
        String previousActivityTag = data.getString("TAG");

        Log.d(TAG, "onCreate: tag is " + previousActivityTag);

        if (previousActivityTag.equals("PacerActivity")) {
            Log.d(TAG, "onCreate: pacer");
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

            int[] timeArray = new int[(int)(Math.ceil((endTime - startTime)/100)*2)+1];
            double[] BACArray = new double[(int)(Math.ceil((endTime - startTime)/100)*2)+1];
            BACArray[0] = 0;
            int counter = 0;

            for (int t = startTime; t < endTime; t++) //iterate from first given time to last given time in array
            {
                timeArray[counter] = t;
                if (counter != 0) {
                    BACArray[counter] = BACArray[counter - 1] - 0.15; // give the t-1 value to t DEREFERENCE ACCORDINGLY
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

            // TODO: 12/9/16 add values calculated from last step into thte table
            // Get results table and add rows as needed
            /*TableLayout tableLayout = new TableLayout(getApplicationContext());
            TableRow row;
            TextView view;

            numevents=5;
            Log.d(TAG, "onCreate: reached pt 1");
            for(int i =0; i<numevents; i++) {
                row = new TableRow(getApplicationContext());
                for (int j = 0; j < 2; j++) {
                    view = new TextView(getApplicationContext());
                    view.setText("test");
                    view.setPadding(20, 20, 20, 20);
                    row.addView(view);
                }
                tableLayout.addView(row);

            }
            Log.d(TAG, "onCreate: reached pt 2");

            setContentView(tableLayout);

            Log.d(TAG, "onCreate: reached pt 3"); */

            TableLayout prices = (TableLayout)findViewById(R.id.BAC_table);
            prices.setStretchAllColumns(true);
            prices.bringToFront();
            for(int i = 0; i < numevents; i++){
                TableRow tr =  new TableRow(this);
                TextView c1 = new TextView(this);
                c1.setText(timeArray[i]);
                TextView c2 = new TextView(this);
                c2.setText(String.valueOf(BACArray[i]));
                tr.addView(c1);
                tr.addView(c2);
                prices.addView(tr);
            }


        } else if(previousActivityTag.equals("SoberUpActivity")) {
            Log.d(TAG, "onCreate: sobering up now");
            int beerBottlesNum = data.getInt("beerBottleNum");
            int vodkaShotsNum = data.getInt("vodkaShotNum");
            int liquorGlassNum = data.getInt("liquorGlassNum");
            int wineGlassNum = data.getInt("wineGlassNum");
            int userWeightInput = data.getInt("userWeightVal");
            double userGenderConstant = data.getDouble("genderConstantVal");

            Toast.makeText(ResultsActivity.this, "number of beer bottles" + data.getInt("beerBottleNum"), Toast.LENGTH_LONG).show();
        }

    Log.d(TAG,"onCreate: nothing obtained");
    }
}

