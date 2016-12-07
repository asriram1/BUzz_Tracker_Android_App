package com.example.karanraj.chauhan.courseplanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by karanraj on 12/7/16.
 */

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Bundle data = getIntent().getExtras();
//        BeverageIntake[] test = data.getParcelable("beverageIntakes");


        TextView testtv = (TextView) findViewById(R.id.test_tv);
//        testtv.setText(beverageIntakes[0].getName()+beverageIntakes[0].getTime()+beverageIntakes[0].getQuantity());
    }
}
