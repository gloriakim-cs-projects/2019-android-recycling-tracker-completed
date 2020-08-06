package com.codepath.wmgf8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ImageButton ic_camera_button, ic_search_button, ic_record_button;
    Button ic_goal_button, ic_collection_button, credits_button;
    TextView total_count, percent, percent2;
    int compared_percent;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            SharedPreferences settings = getSharedPreferences("PREFS", 0);
            SharedPreferences.Editor editor = settings.edit();

            /////////////////////
            /* first time tour */
            ////////////////////
            String first_time = settings.getString("first_time", "Yes");

            if (first_time.equals("Yes")) {
                Intent intent = new Intent(MainActivity.this, first_time_tour.class);
                startActivity(intent);
                editor.putString("first_time", "No");
                editor.apply();
            }

            /////////////////////
            /* credits button */
            ////////////////////
            credits_button = (Button) findViewById(R.id.credits_button);
            credits_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent int0 = new Intent(MainActivity.this, credits.class);
                    startActivity(int0);
                }
            });

            /////////////////////
            /* weekly counter */
            ////////////////////

            //total collected item
    //        int total_number = settings.getInt("total_day", 0);
            int total_number = settings.getInt("total_recycle_counter", 0);
            int total_current_week = settings.getInt("total_current_week", 0);
            int total_last_week = settings.getInt("total_last_week", 0);

            Calendar calendar = Calendar.getInstance();
            int currentWeek = calendar.get(Calendar.WEEK_OF_MONTH);
            int lastWeek = settings.getInt("week", 0);

            if (lastWeek != currentWeek) { //week 다름
                editor.putInt("week", currentWeek);
                total_last_week = total_current_week;
                editor.putInt("total_last_week", total_last_week);
                total_current_week = 0;
                editor.putInt("total_current_week", total_current_week);
                editor.commit();
            }

            total_count = (TextView) findViewById(R.id.number);
            total_count.setText(Integer.toString(total_number));


            percent = (TextView) findViewById(R.id.percent);
            percent2 = (TextView) findViewById(R.id.percent2);


            if (total_last_week == 0) {
                compared_percent = total_current_week / 1;
                percent.setText(compared_percent + "% MORE");
                percent2.setText("THAN THE LAST WEEK");
            }
            else if (total_current_week == 0) {
                compared_percent = total_last_week / 1;
                percent.setText(compared_percent + "% LESS");
                percent2.setText("THAN THE LAST WEEK");
            }
            else if (total_current_week > total_last_week) {
                compared_percent = total_current_week / total_last_week;
                percent.setText(compared_percent + "% MORE");
                percent2.setText("THAN THE LAST WEEK");
            }
            else if (total_current_week < total_last_week) {
                compared_percent = total_last_week / total_current_week;
                percent.setText(compared_percent + "% LESS");
                percent2.setText("THAN THE LAST WEEK");
            }
            else {
                percent.setText("SAME");
                percent2.setText("AS THE LAST WEEK");
            }

            ///////////////////////
            /* bottom nav camera */
            ///////////////////////
            ic_camera_button = (ImageButton) findViewById(R.id.ic_main_camera);
            ic_search_button = (ImageButton) findViewById(R.id.image_search);
            ic_record_button = (ImageButton) findViewById(R.id.image_record);

            ic_camera_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent int1 = new Intent(MainActivity.this, bottom_nav_camera.class);
                    startActivity(int1);
                }
            });

            ic_search_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent int2 = new Intent(MainActivity.this, bottom_nav_search.class);
                    startActivity(int2);
                }
            });

            ic_record_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent int3 = new Intent(MainActivity.this, top_nav_collect.class);
                    startActivity(int3);
                }
            });

            ////////////////////
            /* top navigation */
            ///////////////////

            ic_goal_button = (Button) findViewById(R.id.ic_goal_button);
            ic_collection_button = (Button) findViewById(R.id.ic_collection_button);

            ic_goal_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent int5 = new Intent(MainActivity.this, top_nav_goal.class);
                    startActivity(int5);
                }
            });

            ic_collection_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Intent int6 = new Intent(MainActivity.this, top_nav_collect.class);
                    Intent int6 = new Intent(MainActivity.this, bottom_nav_record.class);
                    startActivity(int6);
                }
            });


    }


}