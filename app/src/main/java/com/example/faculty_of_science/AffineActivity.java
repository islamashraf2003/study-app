package com.example.faculty_of_science;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AffineActivity extends AppCompatActivity {

    EditText messageInput, keyInput ,key2;
    Button encryptButton, decryptButton;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_affine);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        messageInput = findViewById(R.id.message_input);
        keyInput = findViewById(R.id.key_input);
        key2 =findViewById(R.id.editTextText2);
        encryptButton = findViewById(R.id.encrypt_button);
        decryptButton = findViewById(R.id.decrypt_button);
        resultText = findViewById(R.id.result_text);

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                try {
                    String message = messageInput.getText().toString().toUpperCase();
                    String keyString = keyInput.getText().toString();
                    String b = key2.getText().toString();
                    int key = Integer.parseInt(keyString);
                    int key_b = Integer.parseInt(b);

                    // Check if 'a' and 'b' are coprime
                    if (gcd(key, 26) == 1) {
                        String encryptedMessage = AffineCipher.encrypt(message, key, key_b);
                        resultText.setVisibility(View.VISIBLE);
                        resultText.setText("Encrypted Message: " + encryptedMessage);
                    } else {
                        resultText.setVisibility(View.VISIBLE);
                        resultText.setText("a and 26 must be coprime (gcd(a, 26) = 1)");
                    }
                } catch (Exception e) {
                    resultText.setText("Error: " + e.getMessage());
                }
            }
        });

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String message = messageInput.getText().toString().toUpperCase();
                    String keyString = keyInput.getText().toString();
                    String b = key2.getText().toString();
                    int key = Integer.parseInt(keyString);
                    int key_b = Integer.parseInt(b);

                    // Check if 'a' and 'b' are coprime
                    if (gcd(key, 26) == 1) {
                        String decryptedMessage = AffineCipher.decrypt(message, key, key_b);
                        resultText.setVisibility(View.VISIBLE);
                        resultText.setText("Decrypted Message: " + decryptedMessage);
                    } else {
                        resultText.setVisibility(View.VISIBLE);
                        resultText.setText("a and 26 must be (gcd(a,26) = 1)");
                    }
                } catch (Exception e) {
                    resultText.setText("Error: " + e.getMessage());
                }
            }
        });

// Method to calculate the greatest common divisor (gcd)

    }
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }
}
