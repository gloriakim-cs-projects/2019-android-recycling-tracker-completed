package com.codepath.wmgf8;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.File;

public class bottom_nav_record_recycle extends AppCompatActivity {

    String[] oic_array = {"Aluminum Can", "Backpack", "Ball", "Bicycle", "Binder", "Candy Wrapper", "Cardboard", "Carton", "Cellphone", "Charger", "Chips", "Clothing", "Coffee Cup", "Coffee Machine", "Comb", "Computer", "Detergent", "Earphone", "Eraser", "Fan", "Food Scrap", "Frying Pan", "Furniture", "Glass Bottle", "Glass Cup", "Glass Jar", "Glasses", "Hair Dryer", "Hairbrush", "Hanger", "Juice Box", "Juice Pouch", "Lamp", "Laptop", "Lightbulb", "Medicine", "Mug", "Paper", "Paper Cup", "Paper Plate", "Paper Towel", "Pen", "Pillow", "Pizza Box", "Plastic Bag", "Plastic Cup", "Plastic Drink Bottle", "Plastic Jug", "Plastic Liquid Bottle", "Plastic Suace Bottle", "Plastic Utensil", "Pot", "Refrigerator", "Rug", "Scissors", "Shoes", "Silverware", "Steel Can", "Straw", "Styrofoam", "Styrofoam Box", "Surge Protector", "Textile", "Tissue", "To Go Box", "Toothbrush", "Toothpaste", "Towel", "Trash Bag", "TV", "Umbrella", "USB Flash Drive"};
    int max_num = 0;
    int max_image = 0;
    TextView total_collected, max, max_text;
    int[] total_each_item_collected = new int[75];
    String max_text_saved = "";
    ImageView top1_image;

    //all items
    GridView gridView;
    String[] recycle_label = new String[75];
    int num = 0;
    String[] recycle_num = new String[75];
    int[] recycle_image = new int[75];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav_record_recycle);

        //total number
        total_collected = (TextView) findViewById(R.id.total_line);

        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        int total_life_counter = settings.getInt("total_recycle_counter", 0);

        total_collected.setText("Recycled " + total_life_counter);

        //top 1
        //find the max number
        for (int i = 0; i < oic_array.length; i++) {
            if (oic_array[i].equals("Styrofoam") || oic_array[i].equals("Styrofoam Box") || oic_array[i].equals("Tissue") || oic_array[i].equals("To Go Box") || oic_array[i].equals("Toothbrush") || oic_array[i].equals("Toothpaste") || oic_array[i].equals("Straw") || oic_array[i].equals("Pizza Box")) {
                //do nothing; this is for the trash part
            } else {
                //grab recycle label
                recycle_label[num] = oic_array[i];

                //count max
                StringBuilder s3 = new StringBuilder("total_");
                s3.append(oic_array[i]);
                String s4 = s3.toString();
                total_each_item_collected[i] = Integer.valueOf(settings.getString(s4, "0"));
                if (max_num < total_each_item_collected[i]) {
                    max_num = total_each_item_collected[i];
                    max_text_saved = oic_array[i];
                    max_image = i;
                }
                //the number of recycled items = textView
                recycle_num[num] = Integer.toString(total_each_item_collected[i]);

                //the icon image of recycled item = imageView
                //FIXME
                StringBuilder s5 = new StringBuilder("oic_");
                s5.append(Integer.toString(i + 1));
                String s6 = s5.toString();

                recycle_image[num] = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + s6, null, null);
                //move to next position
                num += 1;
            }
        }
        //apply the max
        max = (TextView) findViewById(R.id.max_num);
        max.setText(Integer.toString(max_num));

        max_text = (TextView) findViewById(R.id.max_text);
        max_text.setText(max_text_saved);

        if (max_image > 0)
        {
            top1_image = (ImageView) findViewById(R.id.top1_image);
            top1_image.setImageResource(recycle_image[max_image]);
        }

        //display other items
        gridView = (GridView) findViewById(R.id.grid_view_recycle);
        bottom_nav_record_both_adapter adapter = new bottom_nav_record_both_adapter(this, recycle_num, recycle_image, num);

        gridView.setAdapter(adapter);

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
                Intent int1 = new Intent(bottom_nav_record_recycle.this, bottom_nav_camera.class);
                startActivity(int1);
            }
        });

        ic_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2 = new Intent(bottom_nav_record_recycle.this, bottom_nav_search.class);
                startActivity(int2);
            }
        });

        ic_record_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int3 = new Intent(bottom_nav_record_recycle.this, credits.class);
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
                Intent int4 = new Intent(bottom_nav_record_recycle.this, MainActivity.class);
                startActivity(int4);
            }
        });

        ic_goal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int5 = new Intent(bottom_nav_record_recycle.this, top_nav_goal.class);
                startActivity(int5);
            }
        });

        ic_collection_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int6 = new Intent(bottom_nav_record_recycle.this, bottom_nav_record.class);
                startActivity(int6);
            }
        });
    }
}
