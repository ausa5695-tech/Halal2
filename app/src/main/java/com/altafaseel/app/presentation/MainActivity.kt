package com.altafaseel.app.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import com.altafaseel.app.R

class MainActivity : ComponentActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            mediaPlaybackRequiresUserGesture = false
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            // تم تفعيل التخزين المؤقت لمنع ظهور خطأ الكاش
            cacheMode = WebSettings.LOAD_DEFAULT
            databaseEnabled = true
        }

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                injectAudioFilteringScript(view)
            }
        }

        // فتح موقع تيك توك بشكل مباشر
        webView.loadUrl("https://www.tiktok.com")
    }

    private fun injectAudioFilteringScript(webView: WebView?) {
        val script = """
            (function() {
                setInterval(() => {
                    const videos = document.querySelectorAll('video');
                    videos.forEach(video => {
                        // مكان التحكم في الوسائط والصوت داخل الصفحة
                    });
                }, 1000);
            })();
        """.trimIndent()
        webView?.evaluateJavascript(script, null)
    }
}
