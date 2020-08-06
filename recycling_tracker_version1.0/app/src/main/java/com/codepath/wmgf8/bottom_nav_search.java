package com.codepath.wmgf8;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class bottom_nav_search extends AppCompatActivity {
    private bottom_nav_search_adapter adapter;
    //search function
    private List<ExampleItem> exampleList;

    //FIXME: Please changes all the below oic_array in: (1) bottom_nav_record.java, (2) bottom_nav_search.java, (3) bottom_nav_search_details.java (4) bottom_nav_camera.java, (5) top_nav_goal.java
    String[] oic_array = {"Aluminum Can", "Backpack", "Ball", "Bicycle", "Binder", "Candy Wrapper", "Cardboard", "Carton", "Cellphone", "Charger", "Chips", "Clothing", "Coffee Cup", "Coffee Machine", "Comb", "Computer", "Detergent", "Earphone", "Eraser", "Fan", "Food Scrap", "Frying Pan", "Furniture", "Glass Bottle", "Glass Cup", "Glass Jar", "Glasses", "Hair Dryer", "Hairbrush", "Hanger", "Juice Box", "Juice Pouch", "Lamp", "Laptop", "Lightbulb", "Medicine", "Mug", "Paper", "Paper Cup", "Paper Plate", "Paper Towel", "Pen", "Pillow", "Pizza Box", "Plastic Bag", "Plastic Cup", "Plastic Drink Bottle", "Plastic Jug", "Plastic Liquid Bottle", "Plastic Suace Bottle", "Plastic Utensil", "Pot", "Refrigerator", "Rug", "Scissors", "Shoes", "Silverware", "Steel Can", "Straw", "Styrofoam", "Styrofoam Box", "Surge Protector", "Textile", "Tissue", "To Go Box", "Toothbrush", "Toothpaste", "Towel", "Trash Bag", "TV", "Umbrella", "USB Flash Drive"};
    int[] oic_image_array = new int[]{R.drawable.oic_1, R.drawable.oic_2, R.drawable.oic_3, R.drawable.oic_4, R.drawable.oic_5, R.drawable.oic_6, R.drawable.oic_7, R.drawable.oic_8, R.drawable.oic_9, R.drawable.oic_10, R.drawable.oic_11, R.drawable.oic_12, R.drawable.oic_13, R.drawable.oic_14, R.drawable.oic_15, R.drawable.oic_16, R.drawable.oic_17, R.drawable.oic_18, R.drawable.oic_19, R.drawable.oic_20, R.drawable.oic_21, R.drawable.oic_22, R.drawable.oic_23, R.drawable.oic_24, R.drawable.oic_25, R.drawable.oic_26, R.drawable.oic_27, R.drawable.oic_28, R.drawable.oic_29, R.drawable.oic_30, R.drawable.oic_31, R.drawable.oic_32, R.drawable.oic_33, R.drawable.oic_34, R.drawable.oic_35, R.drawable.oic_36, R.drawable.oic_37, R.drawable.oic_38, R.drawable.oic_39, R.drawable.oic_40, R.drawable.oic_41, R.drawable.oic_42, R.drawable.oic_43, R.drawable.oic_44, R.drawable.oic_45, R.drawable.oic_46, R.drawable.oic_47, R.drawable.oic_48, R.drawable.oic_49, R.drawable.oic_50, R.drawable.oic_51, R.drawable.oic_52, R.drawable.oic_53, R.drawable.oic_54, R.drawable.oic_55, R.drawable.oic_56, R.drawable.oic_57, R.drawable.oic_58, R.drawable.oic_59, R.drawable.oic_60, R.drawable.oic_61, R.drawable.oic_62, R.drawable.oic_63, R.drawable.oic_64, R.drawable.oic_65, R.drawable.oic_66, R.drawable.oic_67, R.drawable.oic_68, R.drawable.oic_69, R.drawable.oic_70, R.drawable.oic_71, R.drawable.oic_72};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav_search);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            Drawable d=getResources().getDrawable(R.drawable.search_bar);
            actionBar.setBackgroundDrawable(d);
        }

        ////////////////
        /* collection */
        ////////////////
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        exampleList = new ArrayList<>();
        for (int i = 0; i < oic_array.length; i++) {
            exampleList.add(new ExampleItem(oic_image_array[i], oic_array[i]));
        }
        adapter = new bottom_nav_search_adapter(this, exampleList);
        gridView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}

