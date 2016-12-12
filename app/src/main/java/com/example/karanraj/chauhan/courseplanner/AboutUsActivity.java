package com.example.karanraj.chauhan.courseplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by karanraj on 12/10/16.
 */

public class AboutUsActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about_us);



    }
    public void onPrevButtonClick_aboutus(View view){
        Intent intentToMainActivity = new Intent(AboutUsActivity.this, MainActivity.class);
        startActivity(intentToMainActivity);

    }
}
