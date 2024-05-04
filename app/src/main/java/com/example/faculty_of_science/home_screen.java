package com.example.faculty_of_science;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.card.MaterialCardView;

public class home_screen extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    MaterialCardView homeCard,newsCard,eventsCard, homeWorkCard,gpaCard,courseCard;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_screen);
        toolbar = findViewById(R.id.myAppBar);
        setSupportActionBar(toolbar);
        homeCard = findViewById(R.id.homeCard);
        newsCard=findViewById(R.id.newsCard);
        homeWorkCard=findViewById(R.id.homework);
        eventsCard=findViewById(R.id.eventsCard);
        gpaCard=findViewById(R.id.gpaCard);
        courseCard=findViewById(R.id.courseCard);
        t=findViewById(R.id.t);
         String homePageUrl="https://science.asu.edu.eg/ar/";
         String eventsUrl="https://science.asu.edu.eg/ar/news";
         String newsUrl="https://science.asu.edu.eg/ar/events";
        homeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigator = new Intent(home_screen.this, webView_screen.class);
                 navigator.putExtra("homeUrl",homePageUrl);
                startActivity(navigator);
            }
        });
        //news card
        Intent incomingId=getIntent();
        int id=incomingId.getExtras().getInt("id");
        newsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigator = new Intent(home_screen.this, webView_screen.class);
                navigator.putExtra("newsUrl",newsUrl);
                startActivity(navigator);
            }
        });
        //ecents card
        eventsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigator = new Intent(home_screen.this, webView_screen.class);
                navigator.putExtra("eventsUrl",eventsUrl);
                startActivity(navigator);
            }
        });
       homeWorkCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigator = new Intent(home_screen.this, HomeWork.class);
     navigator.putExtra("id",id);
                startActivity(navigator);
            }
        });

       gpaCard.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent navigator = new Intent(home_screen.this, gpamain.class);
               startActivity(navigator);
           }
       });


        courseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navigator = new Intent(home_screen.this, courses.class);
                startActivity(navigator);
            }
        });

    }


}
