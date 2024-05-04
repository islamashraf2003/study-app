package com.example.faculty_of_science;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DatabaseActivity extends AppCompatActivity {

    Button score;
    TextView tv;
    RadioGroup rg1,rg2,rg3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        score=findViewById(R.id.score);
        rg1=findViewById(R.id.radiogroup1);
        rg2=findViewById(R.id.radiogroup2);
        rg3=findViewById(R.id.radiogroup3);
        tv=findViewById(R.id.tv);
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int r1,r2,r3,total_score=0;
                r1=rg1.getCheckedRadioButtonId();
                r2=rg2.getCheckedRadioButtonId();
                r3=rg3.getCheckedRadioButtonId();
                if(r1==R.id.all)
                {
                    total_score=total_score+5;
                }
                if(r2==R.id.maadi)
                {
                    total_score=total_score+5;
                }

                if(r3==R.id.insert)
                {
                    total_score+=5;
                }
                tv.setText("Your Score Is "+total_score +"\n Every Question have 5 Points");
            }
        });
    }
}