package com.example.faculty_of_science;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CompilerActivity extends AppCompatActivity {

    Button score;
    TextView tv;
    RadioGroup rg1,rg2,rg3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compiler);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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
                if(r2==R.id.a_c)
                {
                    total_score=total_score+5;
                }

                if(r3==R.id.a_b)
                {
                    total_score+=5;
                }
                tv.setText("Your Score Is "+total_score +"\n Every Question have 5 Points");
            }
        });
    }
}