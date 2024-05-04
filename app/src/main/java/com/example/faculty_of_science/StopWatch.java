package com.example.faculty_of_science;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

public class StopWatch extends AppCompatActivity {
    Button btnstart;
    Animation rounding;
    ImageView icon;
    Chronometer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        btnstart = findViewById(R.id.start);
        rounding = AnimationUtils.loadAnimation(this, R.anim.alpha);
        timer = findViewById(R.id.timer);
        icon=findViewById(R.id.chor);
    }

    public void start(View view) {
        icon.startAnimation(rounding);
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();
    }
    public void stop(View view) {
        icon.clearAnimation(); // Stop the animation
        timer.stop(); // Stop the chronometer
    }
}