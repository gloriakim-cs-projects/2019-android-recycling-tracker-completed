package com.codepath.wmgf8;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class bottom_nav_camera_yes_button extends AppCompatActivity {

    ImageButton more_info;
    TextView collected_number, item_title;
    ImageView item;

    GridView gridView;
    File[] listFile;
    String[] FilePathStrings;

    String TAG="";

    int position = 0;

    String[] oic_array = {"Aluminum Can", "Backpack", "Ball", "Bicycle", "Binder", "Candy Wrapper", "Cardboard", "Carton", "Cellphone", "Charger", "Chips", "Clothing", "Coffee Cup", "Coffee Machine", "Comb", "Computer", "Detergent", "Earphone", "Eraser", "Fan", "Food Scrap", "Frying Pan", "Furniture", "Glass Bottle", "Glass Cup", "Glass Jar", "Glasses", "Hair Dryer", "Hairbrush", "Hanger", "Juice Box", "Juice Pouch", "Lamp", "Laptop", "Lightbulb", "Medicine", "Mug", "Paper", "Paper Cup", "Paper Plate", "Paper Towel", "Pen", "Pillow", "Pizza Box", "Plastic Bag", "Plastic Cup", "Plastic Drink Bottle", "Plastic Jug", "Plastic Liquid Bottle", "Plastic Suace Bottle", "Plastic Utensil", "Pot", "Refrigerator", "Rug", "Scissors", "Shoes", "Silverware", "Steel Can", "Straw", "Styrofoam", "Styrofoam Box", "Surge Protector", "Textile", "Tissue", "To Go Box", "Toothbrush", "Toothpaste", "Towel", "Trash Bag", "TV", "Umbrella", "USB Flash Drive"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav_camera_yes_button);

        more_info = (ImageButton) findViewById(R.id.info_bar);
        collected_number = (TextView) findViewById(R.id.collected_number);
        item_title = (TextView) findViewById(R.id.item_title);
        item = (ImageView) findViewById(R.id.item);
        //gridView = findViewById(R.id.myGrid);

        //item position
        Intent i = getIntent();
        final String topLabels = i.getStringExtra("topLabels");
        item_title.setText(topLabels);

        for (int j = 0; j < oic_array.length; j++) {
            if (topLabels.equals(oic_array[j])) {
                position = j + 1;
            }
        }

        //edit icon image
        StringBuilder s = new StringBuilder("oic_");
        s.append(position);
        String s2 = s.toString();

        int id = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + s2, null, null);
        item.setImageResource(id);

        //more info button
        more_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move to detailed description
                Intent i = new Intent(bottom_nav_camera_yes_button.this, bottom_nav_search_details.class);
                i.putExtra("label", topLabels);
                startActivity(i);
            }
        });

        //collected number
        StringBuilder s3 = new StringBuilder("total_");
        s3.append(topLabels);
        String s4 = s3.toString();

        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        int total_each_item_collected = Integer.valueOf(settings.getString(s4, "0"));

        collected_number.setText(total_each_item_collected + " Items");

        //show captured photos
        displayPhoto(); //이거
    }


    public void displayPhoto() {
        Intent intent = getIntent();
        String topLabels = intent.getStringExtra("topLabels");

        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        SharedPreferences.Editor editor = settings.edit();

        //create a folder
        String folder_main = "Recycling Tracker";
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File f = new File(file, folder_main);
        if (!f.exists()) {
            f.mkdirs();
        }

        //create anohter folder
        File f1 = new File(f.getAbsolutePath(), topLabels);
        if (!f1.exists()) {
            f1.mkdirs();
        }

        listFile = f1.listFiles();
        FilePathStrings = new String[listFile.length];
        for (int i = 0; i < listFile.length; i++)
        {
            FilePathStrings[i] = listFile[i].getAbsolutePath();
        }

        gridView = (GridView)findViewById(R.id.grid_view);
        bottom_nav_camera_yes_button_adapter adapter = new bottom_nav_camera_yes_button_adapter(this, FilePathStrings);
        gridView.setAdapter(adapter);
    }
}
