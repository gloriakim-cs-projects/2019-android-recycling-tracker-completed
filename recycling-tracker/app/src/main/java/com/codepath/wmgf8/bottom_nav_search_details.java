package com.codepath.wmgf8;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class bottom_nav_search_details extends AppCompatActivity {

    //FIXME: Please note that if the below oic_array changes, the oic_array in top_nav_goal.java & bottom_nav_camera.java & bottom_nav_search.java also needs to change
    String[] oic_array = {"Backpack", "Ball", "Bicycle", "Binder", "Blanket", "Bulletin Board", "Can", "Candy Wrapper", "Car", "Cardboard", "Cellphone", "Charger", "Chips", "Clothing", "Coffee Machine", "Comb", "Computer", "Detergent", "Earphone", "Eraser", "Extension Cord", "Fan", "Food", "Frying Pan", "Furniture", "Glass Cup", "Glasses", "Hair Dryer", "Hairbrush", "Hanger", "Jam", "Ketchup Bottle", "Kettle", "Lamp", "Laptop", "Lightbulb", "Medicine", "Milk Carton", "Milk Jug", "Mug", "Notebook", "Paper", "Paper Cup", "Paper Plate", "Paper Towel", "Pen", "Pencil", "Pillow", "Pizza Box", "Plastic Bag", "Plastic Bottle", "Plastic Cup", "Plastic Jug", "Plastic Utensil", "Pot", "Refrigerator", "Rug", "Scissors", "Shoes", "Silverware", "Straw", "Styrofoam", "Styrofoam Box", "Tissue", "To Go Box", "Toothbrush", "Toothpaste", "Towel", "Trash Bag", "TV", "Umbrella", "USB Cable", "USB Flash Drive", "Water Bottle", "Whiteboard"};
    int[] oic_image_array = new int[]{R.drawable.oic_1, R.drawable.oic_2, R.drawable.oic_3, R.drawable.oic_4, R.drawable.oic_5, R.drawable.oic_6, R.drawable.oic_7, R.drawable.oic_8, R.drawable.oic_9, R.drawable.oic_10, R.drawable.oic_11, R.drawable.oic_12, R.drawable.oic_13, R.drawable.oic_14, R.drawable.oic_15, R.drawable.oic_16, R.drawable.oic_17, R.drawable.oic_18, R.drawable.oic_19, R.drawable.oic_20, R.drawable.oic_21, R.drawable.oic_22, R.drawable.oic_23, R.drawable.oic_24, R.drawable.oic_25, R.drawable.oic_26, R.drawable.oic_27, R.drawable.oic_28, R.drawable.oic_29, R.drawable.oic_30, R.drawable.oic_31, R.drawable.oic_32, R.drawable.oic_33, R.drawable.oic_34, R.drawable.oic_35, R.drawable.oic_36, R.drawable.oic_37, R.drawable.oic_38, R.drawable.oic_39, R.drawable.oic_40, R.drawable.oic_41, R.drawable.oic_42, R.drawable.oic_43, R.drawable.oic_44, R.drawable.oic_45, R.drawable.oic_46, R.drawable.oic_47, R.drawable.oic_48, R.drawable.oic_49, R.drawable.oic_50, R.drawable.oic_51, R.drawable.oic_52, R.drawable.oic_53, R.drawable.oic_54, R.drawable.oic_55, R.drawable.oic_56, R.drawable.oic_57, R.drawable.oic_58, R.drawable.oic_59, R.drawable.oic_60, R.drawable.oic_61, R.drawable.oic_62, R.drawable.oic_63, R.drawable.oic_64, R.drawable.oic_65, R.drawable.oic_66, R.drawable.oic_67, R.drawable.oic_68, R.drawable.oic_69, R.drawable.oic_70, R.drawable.oic_71, R.drawable.oic_72, R.drawable.oic_73, R.drawable.oic_74, R.drawable.oic_75};
    //region oic_description_array
    String[] oic_description_array = {
            "Backpack can be donated to GoodWill.",
            "Ball can be donated to GoodWill.",
            "Bicycle",
            "Binder",
            "Blanket",
            "Bulletin Board",
            "Can",
            "Candy wrappers are not recyclable because (1) they may have possible food contamination and (2) they are made of a combination of plastic and aluminum, which is very difficult to separate.",
            "Car",
            "Cardboard",
            "Cellphone",
            "Charger",
            "Chips",
            "Clothing",
            "Coffee Machine",
            "Comb",
            "Computer",
            "Detergent",
            "Earphone",
            "Eraser",
            "Extension Cord",
            "Fan",
            "Food",
            "Frying Pan",
            "Furniture",
            "Glass Cup",
            "Glasses",
            "Hair Dryer",
            "Hairbrush",
            "Hanger",
            "Jam",
            "Ketchup Bottle",
            "Kettle",
            "Lamp",
            "Laptop",
            "Lightbulb",
            "Medicine",
            "Milk Carton",
            "Milk Jug",
            "Mug",
            "Notebook",
            "Paper",
            "Paper Cup",
            "Paper Plate",
            "Paper Towel",
            "Pen",
            "Pencil",
            "Pillow",
            "Pizza Box",
            "Plastic Bag",
            "Plastic Bottle",
            "Plastic Cup",
            "Plastic Jug",
            "Plastic Utensil",
            "Pot",
            "Refrigerator",
            "Rug",
            "Scissors",
            "Shoes",
            "Silverware",
            "Straw",
            "Styrofoam",
            "Styrofoam Box",
            "Tissue",
            "To Go Box",
            "Toothbrush",
            "Toothpaste",
            "Towel",
            "Trash Bag",
            "TV",
            "Umbrella",
            "USB Cable",
            "USB Flash Drive",
            "Water Bottle",
            "Whiteboard"
    };
    //endregion oic_description_array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav_search_details);

        TextView title = (TextView) findViewById(R.id.title_name);
        TextView background = (TextView) findViewById(R.id.background);
        Button close_button = (Button) findViewById(R.id.close_button);
        TextView title_description = (TextView) findViewById(R.id.title_description);
        TextView title_description2 = (TextView) findViewById(R.id.title_description2);

        Intent intent = getIntent();
        String position = intent.getStringExtra("position");
        //Toast.makeText(this, position, Toast.LENGTH_SHORT).show();

        //set title
        title.setText(oic_array[Integer.parseInt(position)]);
        //set background image
        background.setBackgroundResource(oic_image_array[Integer.parseInt(position)]);
        background.setAlpha((float) 0.15);
        //close button
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                // Do something
                finish();
            }
        });
        //description
        String unrecyclable = oic_array[Integer.parseInt(position)];
        //FIXME: check if there is any ohter item that is unrecyclable
        if (unrecyclable == "Styrofoam"|| unrecyclable == "Styrofoam Box" || unrecyclable == "Tissue" || unrecyclable == "To Go Box" || unrecyclable == "Toothbrush" || unrecyclable == "Straw") {
            title_description.setText("Please do NOT recycle.");
            title_description2.setText(oic_description_array[Integer.parseInt(position)]);
        }
        else {
            title_description.setText("Please DO recycle.");
            title_description2.setText(oic_description_array[Integer.parseInt(position)]);
        }
    }
}