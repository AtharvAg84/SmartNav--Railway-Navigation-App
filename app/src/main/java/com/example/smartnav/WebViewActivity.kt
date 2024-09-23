package com.example.smartnav

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class WebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        // Find the WebView in the layout
        webView = findViewById(R.id.web_view)

        // Enable JavaScript and set WebViewClient to prevent opening in a browser
        webView.settings.javaScriptEnabled = true

        // Handle navigation within WebView
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }
        }

        // Load the required URL in the WebView
        webView.loadUrl("https://smart-nav.netlify.app/2d")
    }

    // Override the back button to navigate in WebView history
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack() // Go back in the WebView history
        } else {
            super.onBackPressed() // Default back button behavior
        }
    }
}
