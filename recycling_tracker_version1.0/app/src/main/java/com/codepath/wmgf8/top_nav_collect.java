package com.codepath.wmgf8;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class top_nav_collect extends AppCompatActivity {
    //navigation
    ImageButton ic_camera_button, ic_search_button, ic_record_button;
    Button ic_today_button, ic_goal_button, ic_collection_button;

    //collection
    String[] numberWord = {"Day 1","Day 2","Day 3","Day 4","Day 5","Day 6","Day 7","Day 8","Day 9","Day 10", "Day 11", "Day 12", "Day 13", "Day 14", "Day 15", "Day 16", "Day 17", "Day 18", "Day 19", "Day 20", "Day 21", "Day 22", "Day 23","Day 24","Day 25","Day 26","Day 27","Day 28"};

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
        /* daily reward */
        ///////////////////////
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        int lastDay = settings.getInt("collect_today",0);
        int daily_award_count = settings.getInt("daily_award_count", 0);

        if (lastDay != currentDay) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("collect_today", currentDay);

            daily_award_count += 1;
            editor.putInt("daily_award_count", daily_award_count);
            editor.commit();
        }
        if (daily_award_count >= 28) {
            Toast.makeText(this, "Congratulations! You collected all awards.", Toast.LENGTH_LONG).show();
        }

        ///////////////////////
        /* collection */
        ///////////////////////
        GridView gridView = (GridView) findViewById(R.id.grid_view);

        top_nav_collect_adapter adapter = new top_nav_collect_adapter(this, numberWord, numberImage, daily_award_count);
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
                Intent int3 = new Intent(top_nav_collect.this, credits.class);
                startActivity(int3);
            }
        });

        ////////////////////
        /* top navigation */
        ///////////////////

        ic_today_button = (Button) findViewById(R.id.ic_today_button);
        ic_goal_button = (Button) findViewById(R.id.ic_goal_button);
        ic_collection_button = (Button) findViewById(R.id.ic_collection_button);

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

        ic_collection_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int6 = new Intent(top_nav_collect.this, bottom_nav_record.class);
                startActivity(int6);
            }
        });
    }
}
