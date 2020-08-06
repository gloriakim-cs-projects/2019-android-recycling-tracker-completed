package com.codepath.wmgf8;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class top_nav_collect extends AppCompatActivity {
    //navigation
    ImageButton ic_camera_button, ic_search_button, ic_record_button;
    Button ic_today_button, ic_goal_button, ic_collection_button;

    //collection
    String[] numberWord = {"1","2","3","4","5","6","7","8","9","10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23","24","25","26","27","28"};

    int[] numberImage = {R.drawable.ic_badge1, R.drawable.ic_badge2, R.drawable.ic_badge3, R.drawable.ic_badge4, R.drawable.ic_badge5, R.drawable.ic_badge6,
            R.drawable.ic_badge7, R.drawable.ic_badge8, R.drawable.ic_badge9, R.drawable.ic_badge10, R.drawable.ic_badge11, R.drawable.ic_badge12,
            R.drawable.ic_badge13, R.drawable.ic_badge14, R.drawable.ic_badge15, R.drawable.ic_badge16, R.drawable.ic_badge17, R.drawable.ic_badge18,
            R.drawable.ic_badge19, R.drawable.ic_badge20, R.drawable.ic_badge21, R.drawable.ic_badge22, R.drawable.ic_badge23, R.drawable.ic_badge24,
            R.drawable.ic_badge25, R.drawable.ic_badge26, R.drawable.ic_badge27, R.drawable.ic_badge0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_nav_collect);

        ///////////////////////
        /* collection */
        ///////////////////////
        GridView gridView = (GridView) findViewById(R.id.grid_view);

        MainAdapter adapter = new MainAdapter(this, numberWord, numberImage);
        gridView.setAdapter(adapter);

        ///////////////////////
        /* bottom nav camera */
        ///////////////////////

        ic_camera_button = (ImageButton) findViewById(R.id.ic_main_camera);
        ic_search_button = (ImageButton) findViewById(R.id.image_search);
        ic_record_button = (ImageButton) findViewById(R.id.image_record);

        ic_camera_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(top_nav_collect.this, bottom_nav_camera.class);
                startActivity(int1);
            }
        });

        ic_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2 = new Intent(top_nav_collect.this, bottom_nav_search.class);
                startActivity(int2);
            }
        });

        ic_record_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            }
        });

        ////////////////////
        /* top navigation */
        ///////////////////

        ic_today_button = (Button) findViewById(R.id.ic_today_button);
        ic_goal_button = (Button) findViewById(R.id.ic_goal_button);

        ic_today_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int4 = new Intent(top_nav_collect.this, MainActivity.class);
                startActivity(int4);
            }
        });

        ic_goal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int5 = new Intent(top_nav_collect.this, top_nav_goal.class);
                startActivity(int5);
            }
        });
    }
}
