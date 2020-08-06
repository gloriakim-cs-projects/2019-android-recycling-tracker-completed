package com.codepath.wmgf8;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class bottom_nav_camera_yes_button extends AppCompatActivity {

    ImageButton more_info;
    TextView collected_number;

    //FIXME: working on the below three variables...
//    GridView gridView;
//    private File[] allFiles;
//    private String[] file_path;

    private int fileCount;
    int daily_counter = 0;
    String TAG="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav_camera_yes_button);

        more_info = (ImageButton) findViewById(R.id.info_bar);
        collected_number = (TextView) findViewById(R.id.collected_number);
        //gridView = findViewById(R.id.myGrid);

        collectedNumber();

        more_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move to detailed description
                Intent int1 = new Intent(bottom_nav_camera_yes_button.this, bottom_nav_search_details.class);
                startActivity(int1);
            }
        });
    }


    public void collectedNumber() {
        /*start of getting the number of captured images*/
        Intent intent = getIntent();
        String topLabels = intent.getStringExtra("topLabels");

        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        SharedPreferences.Editor editor = settings.edit();

        String folder_main = "Recycling Tracker";
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), folder_main);
        File f1 = new File(file.getAbsolutePath(), topLabels);
        for (File f2 : f1.listFiles()) {
            if (f2.isFile() && (f2.getName().endsWith(".jpg"))) {
                fileCount++;
            }
        }
        collected_number.setText(fileCount + " Items");
        //Toast.makeText(this, String.valueOf(fileCount), Toast.LENGTH_SHORT).show();
        /*end of getting the number of captured images*/

//        /*start of displaying files*/
//        //list file path (file --> string)
//        allFiles = f1.listFiles();
//        file_path = new String[allFiles.length];
//        for (int i = 0; i < allFiles.length; i++) {
//            file_path[i] = allFiles[i].getAbsolutePath();
//        }
//        /*FIXME: working on this...*/
    }
}
