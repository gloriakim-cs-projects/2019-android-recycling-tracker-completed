package com.codepath.wmgf8;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class bottom_nav_camera_no_button_details extends AppCompatActivity {

    String[] oic_array = {"Aluminum Can", "Backpack", "Ball", "Bicycle", "Binder", "Candy Wrapper", "Cardboard", "Carton", "Cellphone", "Charger", "Chips", "Clothing", "Coffee Cup", "Coffee Machine", "Comb", "Computer", "Detergent", "Earphone", "Eraser", "Fan", "Food Scrap", "Frying Pan", "Furniture", "Glass Bottle", "Glass Cup", "Glass Jar", "Glasses", "Hair Dryer", "Hairbrush", "Hanger", "Juice Box", "Juice Pouch", "Lamp", "Laptop", "Lightbulb", "Medicine", "Mug", "Paper", "Paper Cup", "Paper Plate", "Paper Towel", "Pen", "Pillow", "Pizza Box", "Plastic Bag", "Plastic Cup", "Plastic Drink Bottle", "Plastic Jug", "Plastic Liquid Bottle", "Plastic Suace Bottle", "Plastic Utensil", "Pot", "Refrigerator", "Rug", "Scissors", "Shoes", "Silverware", "Steel Can", "Straw", "Styrofoam", "Styrofoam Box", "Surge Protector", "Textile", "Tissue", "To Go Box", "Toothbrush", "Toothpaste", "Towel", "Trash Bag", "TV", "Umbrella", "USB Flash Drive"};
    int[] oic_image_array = new int[]{R.drawable.oic_1, R.drawable.oic_2, R.drawable.oic_3, R.drawable.oic_4, R.drawable.oic_5, R.drawable.oic_6, R.drawable.oic_7, R.drawable.oic_8, R.drawable.oic_9, R.drawable.oic_10, R.drawable.oic_11, R.drawable.oic_12, R.drawable.oic_13, R.drawable.oic_14, R.drawable.oic_15, R.drawable.oic_16, R.drawable.oic_17, R.drawable.oic_18, R.drawable.oic_19, R.drawable.oic_20, R.drawable.oic_21, R.drawable.oic_22, R.drawable.oic_23, R.drawable.oic_24, R.drawable.oic_25, R.drawable.oic_26, R.drawable.oic_27, R.drawable.oic_28, R.drawable.oic_29, R.drawable.oic_30, R.drawable.oic_31, R.drawable.oic_32, R.drawable.oic_33, R.drawable.oic_34, R.drawable.oic_35, R.drawable.oic_36, R.drawable.oic_37, R.drawable.oic_38, R.drawable.oic_39, R.drawable.oic_40, R.drawable.oic_41, R.drawable.oic_42, R.drawable.oic_43, R.drawable.oic_44, R.drawable.oic_45, R.drawable.oic_46, R.drawable.oic_47, R.drawable.oic_48, R.drawable.oic_49, R.drawable.oic_50, R.drawable.oic_51, R.drawable.oic_52, R.drawable.oic_53, R.drawable.oic_54, R.drawable.oic_55, R.drawable.oic_56, R.drawable.oic_57, R.drawable.oic_58, R.drawable.oic_59, R.drawable.oic_60, R.drawable.oic_61, R.drawable.oic_62, R.drawable.oic_63, R.drawable.oic_64, R.drawable.oic_65, R.drawable.oic_66, R.drawable.oic_67, R.drawable.oic_68, R.drawable.oic_69, R.drawable.oic_70, R.drawable.oic_71, R.drawable.oic_72};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav_camera_no_button_details);


        TextView context = (TextView) findViewById(R.id.context);
        Button yes_button = (Button) findViewById(R.id.yes_button);
        Button no_button = (Button) findViewById(R.id.no_button);
        TextView background = (TextView) findViewById(R.id.background);

        Intent intent = getIntent();
        final String label = intent.getStringExtra("label"); //e.g., "Furniture"
        int position = 0;

        //grab the position for buttons
        for (int i = 0; i < oic_array.length; i++)
        {
            if ((oic_array[i]).equals(label))
            {
                position = i;
            }
        }

        //set context
        context.setText("Did you select \"" + oic_array[position] + "\"?");

        //set background image
        background.setBackgroundResource(oic_image_array[position]);
        background.setAlpha((float) 0.15);

        //yes button
        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //collected number
                Intent i = getIntent();
                final String topLabels = i.getStringExtra("topLabels");
                StringBuilder s3 = new StringBuilder("total_");
                s3.append(topLabels);
                String s4 = s3.toString();
                SharedPreferences settings = getSharedPreferences("PREFS", 0);
                int total_each_item_collected = Integer.valueOf(settings.getString(s4, "0"));
                Log.i("Log", String.valueOf(total_each_item_collected));

                //move to yes_button activity
                Intent int_yes = new Intent(bottom_nav_camera_no_button_details.this, bottom_nav_camera_yes_button.class);
                //passing the topLabels[0] to yes_button activity
                int_yes.putExtra("topLabels", label);
                startActivity(int_yes);
            }
        });

        //no button
        no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });


    }
}
