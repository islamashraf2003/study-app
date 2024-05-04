package com.example.faculty_of_science;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CipherActivity extends AppCompatActivity {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String SUBSTITUTION = "zyxwvutsrqponmlkjihgfedcba"; // Example substitution


    private EditText messageInput;
    private TextView encryptedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cipher);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Initialize views
        messageInput = findViewById(R.id.message_input);
        encryptedTextView = findViewById(R.id.textView9);

        // Initialize buttons
        Button encryptButton = findViewById(R.id.encrypt_button);
        Button decryptButton = findViewById(R.id.decrypt_button);

        // Set click listeners
        encryptButton.setOnClickListener(v -> encryptMessage());
        decryptButton.setOnClickListener(v -> decryptMessage());
    }

    private void encryptMessage() {
        String originalMessage = messageInput.getText().toString();
        String encryptedMessage = substitutionEncrypt(originalMessage);
        encryptedTextView.setText("Encrypted Message: "+encryptedMessage);
    }

    private void decryptMessage() {
        String encryptedMessage = messageInput.getText().toString();
        String decryptedMessage = substitutionDecrypt(encryptedMessage);
        encryptedTextView.setText("Decrypted Message: "+decryptedMessage);
    }

    private String substitutionEncrypt(String message) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (char character : message.toLowerCase().toCharArray()) {
            int index = ALPHABET.indexOf(character);
            if (index != -1) {
                encryptedMessage.append(SUBSTITUTION.charAt(index));
            } else {
                encryptedMessage.append(character);
            }
        }
        return encryptedMessage.toString();
    }

    private String substitutionDecrypt(String encryptedMessage) {
        StringBuilder decryptedMessage = new StringBuilder();
        for (char character : encryptedMessage.toLowerCase().toCharArray()) {
            int index = SUBSTITUTION.indexOf(character);
            if (index != -1) {
                decryptedMessage.append(ALPHABET.charAt(index));
            } else {
                decryptedMessage.append(character);
            }
        }
        return decryptedMessage.toString();
    }
}