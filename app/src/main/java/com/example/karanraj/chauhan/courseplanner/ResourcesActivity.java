package com.example.karanraj.chauhan.courseplanner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import java.util.Calendar;

/**
 * Created by anirudh
 */

public class ResourcesActivity extends AppCompatActivity {

    private TextView resourceTitle;
    private TextView phone;
    private TextView ambulance;
    private TextView helpline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        resourceTitle = (TextView) findViewById(R.id.resourceTitle);
        phone = (TextView) findViewById(R.id.phone);
        ambulance = (TextView) findViewById(R.id.ambulance);
        helpline = (TextView) findViewById(R.id.helpline);


    }

    public void onPrevButtonClick_aboutus(View view){
        Intent intentToMainActivity = new Intent(ResourcesActivity.this, MainActivity.class);
        startActivity(intentToMainActivity);

    }
}
