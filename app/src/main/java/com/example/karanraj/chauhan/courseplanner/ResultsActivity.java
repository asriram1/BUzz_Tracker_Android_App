package com.example.karanraj.chauhan.courseplanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by karanraj on 12/7/16.
 */

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Bundle data = getIntent().getExtras();
        ArrayList<BeverageIntake> receivedBeverageIntakes = data.getParcelableArrayList("beverageIntakesArrayList");

        String[] intakeTimes = new String[receivedBeverageIntakes.size()];
        int[] intakeQuantities = new int[receivedBeverageIntakes.size()];

        for (int i = 0; i < receivedBeverageIntakes.size(); i++) {
            intakeTimes[i] = receivedBeverageIntakes.get(i).getTime();
            intakeQuantities[i] = receivedBeverageIntakes.get(i).getQuantity();
        }

        //TextView testtv = (TextView) findViewById(R.id.test_tv);
        //testtv.setText(receivedBeverageIntakes.get(0).getName()+receivedBeverageIntakes.get(0).getTime()+receivedBeverageIntakes.get(0).getQuantity());
    }
}
