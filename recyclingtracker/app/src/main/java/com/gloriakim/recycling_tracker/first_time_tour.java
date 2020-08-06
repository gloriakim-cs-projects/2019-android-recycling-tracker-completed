package com.gloriakim.recycling_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class first_time_tour extends AppCompatActivity {

    ImageButton got_it_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_tour);

        got_it_button = (ImageButton) findViewById(R.id.got_it_button);

        got_it_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move to detailed description
                Intent i = new Intent(first_time_tour.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
