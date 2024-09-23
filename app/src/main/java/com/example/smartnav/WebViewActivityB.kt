package com.example.smartnav

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class WebViewActivityB : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_viewb)

        val webView: WebView = findViewById(R.id.web_viewb)
        webView.webViewClient = WebViewClient()  // Opens the URL within the app
        webView.settings.javaScriptEnabled = true  // Enable JavaScript if needed

        // Load the URL for the 3D map
        webView.loadUrl("https://smart-nav.netlify.app/3d")  // Replace with your actual 3D map URL
    }
}