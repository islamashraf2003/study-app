package com.example.faculty_of_science;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class webView_screen extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_web_view_screen);
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        Intent intent = getIntent();

        // Get the URL based on the key passed from home_screen activity
        String url = null;
        if (intent.hasExtra("homeUrl")) {
            url = intent.getStringExtra("homeUrl");
        } else if (intent.hasExtra("newsUrl")) {
            url = intent.getStringExtra("newsUrl");
        } else if (intent.hasExtra("eventsUrl")) {
            url = intent.getStringExtra("eventsUrl");
        }

        // Load the URL in the WebView
        if (url != null) {
            webView.loadUrl(url);
        } else {
            // Handle the case where no URL is provided
            // For example, show an error message or load a default URL
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
