package com.example.faculty_of_science;

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

public class ShiftActivity extends AppCompatActivity {
    EditText messageInput, keyInput;
    TextView resultText;
    Button encryptButton, decryptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shift);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        messageInput = findViewById(R.id.message_input);
        keyInput = findViewById(R.id.key_input);
        resultText = findViewById(R.id.textView9);
        encryptButton = findViewById(R.id.encrypt_button);
        decryptButton = findViewById(R.id.decrypt_button);

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageInput.getText().toString();
                String key = keyInput.getText().toString();
                String encryptedMessage = encrypt(message, Integer.parseInt(key));
                resultText.setText("Encrypted Message: "+encryptedMessage);
            }
        });

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageInput.getText().toString();
                String key = keyInput.getText().toString();
                String decryptedMessage = decrypt(message, Integer.parseInt(key));
                resultText.setText("Decrypted Message: "+decryptedMessage);
            }
        });
    }

    private String encrypt(String message, int key) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char shifted = (char) (c + key);
                if (Character.isUpperCase(c) && shifted > 'Z') {
                    shifted = (char) (shifted - 26);
                } else if (Character.isLowerCase(c) && shifted > 'z') {
                    shifted = (char) (shifted - 26);
                }
                encrypted.append(shifted);
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    private String decrypt(String message, int key) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char shifted = (char) (c - key);
                if (Character.isUpperCase(c) && shifted < 'A') {
                    shifted = (char) (shifted + 26);
                } else if (Character.isLowerCase(c) && shifted < 'a') {
                    shifted = (char) (shifted + 26);
                }
                decrypted.append(shifted);
            } else {
                decrypted.append(c);
            }
        }
        return decrypted.toString();
    }
}
