package com.codepath.wmgf8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class bottom_nav_camera_no_button extends AppCompatActivity {
    String TAG = "";
    ImageButton search_button, ic_retake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav_camera_no_button);

        search_button = (ImageButton) findViewById(R.id.search_bar);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(bottom_nav_camera_no_button.this, bottom_nav_search.class);
                startActivity(int1);
            }
        });

        ic_retake = (ImageButton) findViewById(R.id.ic_retake);
        ic_retake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(bottom_nav_camera_no_button.this, bottom_nav_camera.class);
                startActivity(int2);
            }
        });

    }
}
