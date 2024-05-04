package com.example.faculty_of_science;

public class AffineCipher {
    // Encryption Function
    public static String encrypt(String message, int a, int b) {
        StringBuilder cipherText = new StringBuilder();

        for (char character : message.toCharArray()) {
            if (Character.isLetter(character)) {
                char encryptedChar = (char) (((a * (character - 'A') + b) % 26) + 'A');
                cipherText.append(encryptedChar);
            } else {
                cipherText.append(character);
            }
        }

        return cipherText.toString();
    }

    // Decryption Function
    public static String decrypt(String cipherText, int a, int b) {
        StringBuilder decryptedMessage = new StringBuilder();
        int aInverse = 0;
        // Finding modular multiplicative inverse of 'a'
        for (int i = 0; i < 26; i++) {
            if ((a * i) % 26 == 1) {
                aInverse = i;
                break;
            }
        }

        for (char character : cipherText.toCharArray()) {
            if (Character.isLetter(character)) {
                char decryptedChar = (char) (((aInverse * (character - 'A' - b + 26)) % 26) + 'A');
                decryptedMessage.append(decryptedChar);
            } else {
                decryptedMessage.append(character);
            }
        }

        return decryptedMessage.toString();
    }
}
