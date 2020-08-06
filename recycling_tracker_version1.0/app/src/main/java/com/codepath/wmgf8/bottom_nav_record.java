package com.codepath.wmgf8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class bottom_nav_record extends AppCompatActivity {

    //FIXME: Please changes all the below oic_array in: (1) bottom_nav_record.java, (2) bottom_nav_search.java, (3) bottom_nav_search_details.java (4) bottom_nav_camera.java, (5) top_nav_goal.java
    String[] oic_array = {"Aluminum Can", "Backpack", "Ball", "Bicycle", "Binder", "Candy Wrapper", "Cardboard", "Carton", "Cellphone", "Charger", "Chips", "Clothing", "Coffee Cup", "Coffee Machine", "Comb", "Computer", "Detergent", "Earphone", "Eraser", "Fan", "Food Scrap", "Frying Pan", "Furniture", "Glass Bottle", "Glass Cup", "Glass Jar", "Glasses", "Hair Dryer", "Hairbrush", "Hanger", "Juice Box", "Juice Pouch", "Lamp", "Laptop", "Lightbulb", "Medicine", "Mug", "Paper", "Paper Cup", "Paper Plate", "Paper Towel", "Pen", "Pillow", "Pizza Box", "Plastic Bag", "Plastic Cup", "Plastic Drink Bottle", "Plastic Jug", "Plastic Liquid Bottle", "Plastic Suace Bottle", "Plastic Utensil", "Pot", "Refrigerator", "Rug", "Scissors", "Shoes", "Silverware", "Steel Can", "Straw", "Styrofoam", "Styrofoam Box", "Surge Protector", "Textile", "Tissue", "To Go Box", "Toothbrush", "Toothpaste", "Towel", "Trash Bag", "TV", "Umbrella", "USB Flash Drive"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav_record);

        ImageButton RecycleButton = (ImageButton) findViewById(R.id.ic_recycle_bin);
        ImageButton TrashButton = (ImageButton) findViewById(R.id.ic_trash_bin);

        RecycleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(bottom_nav_record.this, bottom_nav_record_recycle.class);
                startActivity(int1);
            }
        });

        TrashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2 = new Intent(bottom_nav_record.this, bottom_nav_record_trash.class);
                startActivity(int2);
            }
        });


        ///////////////////////
        /* bottom nav camera */
        ///////////////////////
        ImageButton ic_camera_button, ic_search_button, ic_record_button;

        ic_camera_button = (ImageButton) findViewById(R.id.ic_main_camera);
        ic_search_button = (ImageButton) findViewById(R.id.image_search);
        ic_record_button = (ImageButton) findViewById(R.id.image_record);

        ic_camera_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(bottom_nav_record.this, bottom_nav_camera.class);
                startActivity(int1);
            }
        });

        ic_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2 = new Intent(bottom_nav_record.this, bottom_nav_search.class);
                startActivity(int2);
            }
        });

        ic_record_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int3 = new Intent(bottom_nav_record.this, credits.class);
                startActivity(int3);
            }
        });

        ////////////////////
        /* top navigation */
        ///////////////////
        Button ic_today_button, ic_goal_button, ic_collection_button;

        ic_today_button = (Button) findViewById(R.id.ic_today_button);
        ic_goal_button = (Button) findViewById(R.id.ic_goal_button);
        ic_collection_button = (Button) findViewById(R.id.ic_collection_button);

        ic_today_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int4 = new Intent(bottom_nav_record.this, MainActivity.class);
                startActivity(int4);
            }
        });

        ic_goal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int5 = new Intent(bottom_nav_record.this, top_nav_goal.class);
                startActivity(int5);
            }
        });

//        ic_collection_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent int6 = new Intent(bottom_nav_record.this, bottom_nav_record.class);
//                startActivity(int6);
//            }
//        });
    }
}
