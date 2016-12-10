package com.example.karanraj.chauhan.courseplanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by anand on 12/10/2016.
 */

public class AboutUsActivity extends AppCompatActivity {

    private TextView resourceTitle;
    private TextView phone;
    private TextView ambulance;
    private TextView helpline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        resourceTitle = (TextView) findViewById(R.id.resourceTitle);
        phone = (TextView) findViewById(R.id.phone);
        ambulance = (TextView) findViewById(R.id.ambulance);
        helpline = (TextView) findViewById(R.id.helpline);

    }


}
