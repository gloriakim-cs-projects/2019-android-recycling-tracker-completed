package com.gloriakim.recycling_tracker;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class progress_bar_activity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView progressText;
    Button credits_button;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressAnimation();
    }

    public void progressAnimation() {
        progressBar = (ProgressBar) findViewById(R.id.progress_line);
        progressText = (TextView) findViewById(R.id.percent);
        progress_bar_animation anim = new progress_bar_animation(this, progressBar, progressText, 0f, 100f);
        anim.setDuration(3000);
        progressBar.setAnimation(anim);

    }
}