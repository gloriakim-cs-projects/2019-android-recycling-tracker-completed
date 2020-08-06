package com.codepath.wmgf8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    ImageButton ic_camera_button, ic_search_button, ic_record_button;
    Button ic_today_button, ic_goal_button, ic_collection_button;
    View rootView;
    OutputStream outputStream;
    TextView total_count, percent, percent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //total collected item
        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        int total_number = settings.getInt("total_day", 0);
        int total_c_week = settings.getInt("total_current_week", 0);
        int total_l_week = settings.getInt("total_last_week", 0);

        total_count = (TextView) findViewById(R.id.number);
        total_count.setText(Integer.toString(total_number));

        percent = (TextView) findViewById(R.id.percent);
        percent2 = (TextView) findViewById(R.id.percent2);

        if (total_l_week == 0) {
            total_l_week = 1;
        }
        if (total_c_week == 0) {
            total_c_week = 1;
        }
        int compared_percent = total_c_week / total_l_week * 100;
        if (total_c_week > total_l_week) {
            percent.setText(Integer.toString(compared_percent) + "% MORE");
        }
        else if (total_c_week < total_l_week){
            percent.setText(Integer.toString(total_c_week) + "% LESS");
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
                Intent int3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            }
        });

        ////////////////////
        /* top navigation */
        ///////////////////

//        ic_today_button = (Button) findViewById(R.id.ic_today_button);
        ic_goal_button = (Button) findViewById(R.id.ic_goal_button);
        ic_collection_button = (Button) findViewById(R.id.ic_collection_button);

//        ic_today_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent int4 = new Intent(MainActivity.this, MAinActivity.class);
//                startActivity(int4);
//            }
//        });

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
                Intent int6 = new Intent(MainActivity.this, top_nav_collect.class);
                startActivity(int6);
            }
        });


    }


}
//        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
//        viewPager2.setAdapter(new frag_pager_adapter(this));

//        TabLayout tabLayout = findViewById(R.id.tabLayout);
//        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
//                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                switch (position) {
//                    case 0: {
//                        tab.setText("Today");
//                        tab.setIcon(R.drawable.ic_today);
//                        break;
//                    }
//                    case 1: {
//                        tab.setText("Goals");
//                        tab.setIcon(R.drawable.ic_goal);
//                        break;
//                    }
//                    case 2: {
//                        tab.setText("Collection");
//                        tab.setIcon(R.drawable.ic_collect);
//                        break;
//                    }
//                }
//
//            }
//        }
//        );
//        tabLayoutMediator.attach();
//    }

//}
