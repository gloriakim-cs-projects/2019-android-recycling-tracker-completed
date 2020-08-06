package com.codepath.wmgf8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class bottom_nav_record extends AppCompatActivity {

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
                Intent int2 = new Intent(bottom_nav_record.this, bottom_nav_recycle_trash.class);
                startActivity(int2);
            }
        });
    }
}
