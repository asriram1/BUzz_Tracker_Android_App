package com.example.karanraj.chauhan.courseplanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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

// TIME NEEDS TO BE CONVERTED FROM STRING TO DOUBLE
            String[] intakeTimes = new String[receivedBeverageIntakes.size()];
            double[] intakeBAC = new double[receivedBeverageIntakes.size()];

            int[] intakeTimes = new int[receivedBeverageIntakes.size()];
            double[] intakeBAC = new double[receivedBeverageIntakes.size()];
//            int[] intakeQuantities = new int[receivedBeverageIntakes.size()];


            for (int i = 0; i < receivedBeverageIntakes.size(); i++) {
                intakeTimes[i] = receivedBeverageIntakes.get(i).getTime();

                intakeBAC[i] = receivedBeverageIntakes.get(i).getBacAdded();
            }

            //Algo goes here

            int rowcheck=0;
            int numevents = receivedBeverageIntakes.size();
            int startTime= intakeTimes[0];
            int endTime= intakeTimes[numevents-1];//Check casting
            int [] timeArray = new int[endTime- startTime];
            double [] BACArray = new double [endTime- startTime];
            BACArray[0] =0;
            int counter = 0;

            for (int t = startTime; t <= endTime; t++) //iterate from first given time to last given time in array
            {
                timeArray[counter] = t;
                if (counter != 0)
                {
                    BACArray[counter] = BACArray[counter-1] - 0.15; // give the t-1 value to t DEREFERENCE ACCORDINGLY
                    if (BACArray[counter]<0){
                        BACArray[counter]= 0;
                    }

                }

                for (int r = rowcheck; r < numevents; r++)
                {
                    if (intakeTimes[r] > t)
                    {
                        break;
                    }
                    else
                    {
                        BACArray[counter] = BACArray[counter] + intakeBAC[r];//calculate and add BAC to appropriate index
                        rowcheck++;
                    }
                }

                counter++;
            }




//                intakeQuantities[i] = receivedBeverageIntakes.get(i).getQuantity();
                intakeBAC[i] = receivedBeverageIntakes.get(i).getBacAdded();
            }

            int rowcheck=0;
            int numevents = receivedBeverageIntakes.size();
            int startTime= intakeTimes[0];
            int endTime= intakeTimes[numevents-1];//Check casting
            double [] timeArray = new double[endTime- startTime];
            double [] BACArray = new double [endTime- startTime];
            BACArray[0] =0;
            int counter = 0;

            for (int t = startTime; t <= endTime; t++) //iterate from first given time to last given time in array
            {
                timeArray[counter] = t;
                if (counter != 0)
                {
                    BACArray[counter] = BACArray[counter-1] - 0.15; // give the t-1 value to t DEREFERENCE ACCORDINGLY
                    if (BACArray[counter]<0){
                        BACArray[counter]= 0;
                    }

                }

                for (int r = rowcheck; r < numevents; r++)
                {
                    if (intakeTimes[r] > t)
                    {
                        break;
                    }
                    else
                    {
                        BACArray[counter] = BACArray[counter] + intakeBAC[r];//calculate and add BAC to appropriate index
                        rowcheck++;
                    }
                }

                counter++;
            }



            TableLayout ll = (TableLayout) findViewById(R.id.bac_levels_table_layout);


            for (int i = 0; i <receivedBeverageIntakes.size(); i++) {

                TableRow row= new TableRow(this);
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);
                TextView tv = new TextView(this);
                TextView qty = new TextView(this);
                qty.setText("10");
                row.addView(qty);
                row.addView(tv);
                ll.addView(row,i);
            }

        } else if (previousActivityTag.equals("SoberUpActivity")) {
            // do stuff
            Log.d(TAG, "onCreate: sobering up now");
            int beerBottlesNum = data.getInt("beerBottleNum");
            int vodkaShotsNum = data.getInt("vodkaShotNum");
            int liquorGlassNum = data.getInt("liquorGlassNum");
            int wineGlassNum = data.getInt("wineGlassNum");
            int userWeightInput = data.getInt("userWeightVal");
            double userGenderConstant = data.getDouble("genderConstantVal");

            Toast.makeText(ResultsActivity.this, "number of beer bottles" + data.getInt("beerBottleNum"), Toast.LENGTH_LONG).show();
        }


        Log.d(TAG, "onCreate: nothing obtained");
    }
}

