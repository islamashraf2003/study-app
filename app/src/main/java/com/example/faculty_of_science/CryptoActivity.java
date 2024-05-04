package com.example.faculty_of_science;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CryptoActivity extends AppCompatActivity {

    int score = 0;

    TextView t1,tv;
    Button b;
    RadioGroup rg1, rg2, rg3;
    RadioButton r1, r2, r3;
    Button cipherButton, affineButton, shiftButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crypto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        b = findViewById(R.id.button2);
        rg1 = findViewById(R.id.radioGroup);
        rg2 = findViewById(R.id.radioGroup2);
        r1 = findViewById(R.id.radioButton4);
        r2 = findViewById(R.id.radioButton9);
        r3 = findViewById(R.id.radioButton12);
        rg3 = findViewById(R.id.radioGroup3);
        tv=findViewById(R.id.textView6);
        cipherButton = findViewById(R.id.cipher);
        affineButton = findViewById(R.id.affine);
        shiftButton = findViewById(R.id.shift);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (r1.isChecked()) {
                        score+=5;
                    }
                    if (r2.isChecked()) {
                        score+=5;
                    }
                    if (r3.isChecked()) {
                        score+=5;
                    }
                    tv.setText("Your Score Is "+score +"\nEvery Question have 5 Points");
                } catch (Exception e) {
                    // Handle any exceptions here
                    showToast("An error occurred: " + e.getMessage());
                }
            }
        });

        try {
            cipherButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start CipherActivity
                    Intent intent = new Intent(CryptoActivity.this, CipherActivity.class);
                    startActivity(intent);
                }
            });

            affineButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start AffineActivity
                    Intent intent = new Intent(CryptoActivity.this, AffineActivity.class);
                    startActivity(intent);
                }
            });

            shiftButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start ShiftActivity
                    Intent intent = new Intent(CryptoActivity.this, ShiftActivity.class);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            // Handle any exceptions here
            showToast("An error occurred: " + e.getMessage());
        }
    }


    public void showToast(String msg) {
        Toast.makeText(CryptoActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
    }
