package com.example.karanraj.chauhan.courseplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button pacerButton = (Button) findViewById(R.id.pacer_button);
        pacerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PacerActivity.class);
                startActivity(intent);
            }
        });
        Button soberupButton = (Button) findViewById(R.id.sober_up_button);
        pacerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SoberUpActivity.class);
                startActivity(intent);

    }
}
