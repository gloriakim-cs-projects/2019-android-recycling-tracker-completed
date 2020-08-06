package com.codepath.wmgf8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class bottom_nav_search_details extends AppCompatActivity {

    //FIXME: Please changes all the below oic_array in: (1) bottom_nav_record.java, (2) bottom_nav_search.java, (3) bottom_nav_search_details.java (4) bottom_nav_camera.java, (5) top_nav_goal.java
    String[] oic_array = {"Aluminum Can", "Backpack", "Ball", "Bicycle", "Binder", "Candy Wrapper", "Cardboard", "Carton", "Cellphone", "Charger", "Chips", "Clothing", "Coffee Cup", "Coffee Machine", "Comb", "Computer", "Detergent", "Earphone", "Eraser", "Fan", "Food Scrap", "Frying Pan", "Furniture", "Glass Bottle", "Glass Cup", "Glass Jar", "Glasses", "Hair Dryer", "Hairbrush", "Hanger", "Juice Box", "Juice Pouch", "Lamp", "Laptop", "Lightbulb", "Medicine", "Mug", "Paper", "Paper Cup", "Paper Plate", "Paper Towel", "Pen", "Pillow", "Pizza Box", "Plastic Bag", "Plastic Cup", "Plastic Drink Bottle", "Plastic Jug", "Plastic Liquid Bottle", "Plastic Suace Bottle", "Plastic Utensil", "Pot", "Refrigerator", "Rug", "Scissors", "Shoes", "Silverware", "Steel Can", "Straw", "Styrofoam", "Styrofoam Box", "Surge Protector", "Textile", "Tissue", "To Go Box", "Toothbrush", "Toothpaste", "Towel", "Trash Bag", "TV", "Umbrella", "USB Flash Drive"};
    int[] oic_image_array = new int[]{R.drawable.oic_1, R.drawable.oic_2, R.drawable.oic_3, R.drawable.oic_4, R.drawable.oic_5, R.drawable.oic_6, R.drawable.oic_7, R.drawable.oic_8, R.drawable.oic_9, R.drawable.oic_10, R.drawable.oic_11, R.drawable.oic_12, R.drawable.oic_13, R.drawable.oic_14, R.drawable.oic_15, R.drawable.oic_16, R.drawable.oic_17, R.drawable.oic_18, R.drawable.oic_19, R.drawable.oic_20, R.drawable.oic_21, R.drawable.oic_22, R.drawable.oic_23, R.drawable.oic_24, R.drawable.oic_25, R.drawable.oic_26, R.drawable.oic_27, R.drawable.oic_28, R.drawable.oic_29, R.drawable.oic_30, R.drawable.oic_31, R.drawable.oic_32, R.drawable.oic_33, R.drawable.oic_34, R.drawable.oic_35, R.drawable.oic_36, R.drawable.oic_37, R.drawable.oic_38, R.drawable.oic_39, R.drawable.oic_40, R.drawable.oic_41, R.drawable.oic_42, R.drawable.oic_43, R.drawable.oic_44, R.drawable.oic_45, R.drawable.oic_46, R.drawable.oic_47, R.drawable.oic_48, R.drawable.oic_49, R.drawable.oic_50, R.drawable.oic_51, R.drawable.oic_52, R.drawable.oic_53, R.drawable.oic_54, R.drawable.oic_55, R.drawable.oic_56, R.drawable.oic_57, R.drawable.oic_58, R.drawable.oic_59, R.drawable.oic_60, R.drawable.oic_61, R.drawable.oic_62, R.drawable.oic_63, R.drawable.oic_64, R.drawable.oic_65, R.drawable.oic_66, R.drawable.oic_67, R.drawable.oic_68, R.drawable.oic_69, R.drawable.oic_70, R.drawable.oic_71, R.drawable.oic_72};
    //region oic_description_array
    String[] oic_description_array = {
            //up to 2 sentences (due to size)
            "", //Aluminum Can
            "", //"Backpack",
            "", //"Ball",
            "", //"Bicycle",
            "The middle ring is recyclable metal and the cardboard panels are recyclable. Because vinyl can jam recycling machinery, please place vinyl in a trashcan.", //"Binder",
            "Otherwise, it may jam recycling machinery.", //"Candy Wrapper"
            "Please keep it dry and empty. A wet cardboard is almost impossible to recycle because it can be easily moldy and weakens the paper fibers.",
            "", //"Carton",
            "", //"Cellphone",
            "", //"Charger",
            "Otherwise, it may jam recycling machinery.", //"Chips",
            "", //"Clothing",
            "The item is not recyclable due to its wax-line/plastic-coating.",
            "", //"Coffee Machine",
            "Most combs are made of recyclable plastic #3. Clean any hairs or contamination and place your comb in a recycling bin.", //"Comb",
            "", //"Computer",
            "", //"Detergent",
            "", //"Earphone",
            "", //"Eraser",
            "", //"Fan",
            "", //"Food Scrap",
            "", //"Frying Pan",
            "", //"Furniture",
            "", //"Glass Bottle",
            "", //"Glass Cup",
            "", //"Glass Jar",
            "", //"Glasses",
            "", //"Hair Dryer",
            "", //"Hairbrush",
            "", //"Hanger",
            "", //"Juice Box",
            "", //"Juice Pouch",
            "", //"Lamp",
            "", //"Laptop",
            "", //"Lightbulb",
            "", //"Medicine",
            "", //"Mug",
            "", //"Paper",
            "", //"Paper Cup",
            "", //"Paper Plate",
            "", //"Paper Towel",
            "", //"Pen",
            "", //"Pillow",
            "", //"Pizza Box",
            "", //"Plastic Bag",
            "", //"Plastic Cup",
            "", //"Plastic Drink Bottle",
            "", //"Plastic Jug",
            "", //"Plastic Liquid Bottle",
            "", //"Plastic Sauce Bottle",
            "", //"Plastic Utensil",
            "", //"Pot",
            "", //"Refrigerator",
            "", //"Rug",
            "", //"Scissors",
            "", //"Shoes",
            "", //"Silverware",
            "", //"Steel Can",
            "", //"Straw",
            "", //"Styrofoam",
            "", //"Styrofoam Box",
            "", //"Surge Protector",
            "", //"Textile",
            "", //"Tissue",
            "", //"To Go Box",
            "", //"Toothbrush",
            "", //"Toothpaste",
            "", //"Towel",
            "", //"Trash Bag",
            "", //"TV",
            "", //"Umbrella",
            "", //"USB Flash Drive"
    };
    //endregion oic_description_array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav_search_details);

        TextView title = (TextView) findViewById(R.id.title_name);
        TextView background = (TextView) findViewById(R.id.background);
        Button close_button = (Button) findViewById(R.id.close_button);
        TextView text_recycle = (TextView) findViewById(R.id.text_recycle);
        TextView text_donate = (TextView) findViewById(R.id.text_donate);
        TextView title_description2 = (TextView) findViewById(R.id.title_description2);
        TextView ic_recycle_big = (TextView) findViewById(R.id.ic_recycle_big);
        TextView ic_donate_big = (TextView) findViewById(R.id.ic_donate_big);

        TextView photo_text = (TextView) findViewById(R.id.photo_text);
        TextView photo = (TextView) findViewById(R.id.photo);

        Intent intent = getIntent();
        String label = intent.getStringExtra("label"); //e.g., "Furniture"
        int position = 0;

        //grab the position for buttons
        for (int i = 0; i < oic_array.length; i++)
        {
            if ((oic_array[i]).equals(label))
            {
                position = i;
            }
        }

        //set title
        title.setText(oic_array[position]);
        //set background image
        background.setBackgroundResource(oic_image_array[position]);
        background.setAlpha((float) 0.15);
        //set photo
        StringBuilder s = new StringBuilder("photo_");
        position = position + 1;
        s.append(position);
        String s2 = s.toString();
        int id = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + s2, null, null);
        photo.setBackgroundResource(id); //FIXME I do not have the full list of photos
        //set photo_text
        position = position - 1;
        if (id != 0)
        {
            photo_text.setText(oic_array[position]);
        }
        //close button
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
        //description
        String unrecyclable = oic_array[position];

        //FIXME: updates labels
        //NOT recyclable & NOT donatable
        if (unrecyclable == "Styrofoam"|| unrecyclable == "Styrofoam Box" || unrecyclable == "Tissue" || unrecyclable == "To Go Box" || unrecyclable == "Toothbrush" || unrecyclable == "Toothpaste" || unrecyclable == "Straw" || unrecyclable == "Pizza Box") {
            text_recycle.setText("Do NOT Recycle");
            text_donate.setText("Do NOT Donate");
            ic_recycle_big.setBackgroundResource(R.drawable.ic_not_recycle_green);
            ic_donate_big.setBackgroundResource(R.drawable.ic_not_donate_green);
            if (oic_description_array[position] != "")
            {
                title_description2.setText("Please place the item in a trashcan. " + oic_description_array[position]);
            }
            else
            {
                title_description2.setText("Please place the item in a trashcan.");
            }
        }
        //NOT recyclable & donatable
        else if (unrecyclable == "Furniture") {
            text_recycle.setText("Do NOT Recycle");
            text_donate.setText("Donate");
            ic_recycle_big.setBackgroundResource(R.drawable.ic_not_recycle_green);
            ic_donate_big.setBackgroundResource(R.drawable.ic_donate_green);
            if (oic_description_array[position] != "")
            {
                title_description2.setText(oic_description_array[position]);
            }
            else
            {
                title_description2.setText("The item is not recyclable in Denton. If it is in a good condition, please donate the item to a local charity. Otherwise, please dispose it in a trashcan.");
            }
        }
        //recyclable & NOT donatable
        else if (unrecyclable == "Aluminum Can" || unrecyclable == "Cardboard") {
            text_recycle.setText("Recycle");
            text_donate.setText("Do NOT Donate");
            ic_recycle_big.setBackgroundResource(R.drawable.ic_recycle_green);
            ic_donate_big.setBackgroundResource(R.drawable.ic_not_donate_green);
            if (oic_description_array[position] != "")
            {
                title_description2.setText(oic_description_array[position]);
            }
            else
            {
                title_description2.setText("Please rinse and dry the item completely before placing it in a recycling bin.");
            }

        }
        //recyclable & donatable
        else {
            text_recycle.setText("Recycle");
            text_donate.setText("Donate");
            ic_recycle_big.setBackgroundResource(R.drawable.ic_recycle_green);
            ic_donate_big.setBackgroundResource(R.drawable.ic_donate_green);
            if (oic_description_array[position] != "")
            {
                title_description2.setText(oic_description_array[position]);
            }
            else
            {
                title_description2.setText("Please either donate or recycle the item.");
            }
        }
    }
}