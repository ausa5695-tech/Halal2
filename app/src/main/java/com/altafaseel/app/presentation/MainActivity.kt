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
        }

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // حقن كود جافاسكريبت لإسكات أو كتم الوسائط الموسيقية أو التعامل مع العناصر الصوتية
                injectAudioFilteringScript(view)
            }
        }

        // فتح موقع تيك توك كمثال أولي للتجربة داخل التطبيق
        webView.loadUrl("https://www.tiktok.com")
    }

    private fun injectAudioFilteringScript(webView: WebView?) {
        // كود جافاسكريبت للتحكم في عناصر الصوت والفيديو داخل الصفحة المفتوحة
        val script = """
            (function() {
                // مراقبة عناصر الفيديو والصوت لكتم عناصر الموسيقى أو ضبطها حسب الحاجة
                setInterval(() => {
                    const videos = document.querySelectorAll('video');
                    videos.forEach(video => {
                        // هنا يمكننا إضافة فحص لعناصر الفيديو أو التعامل مع مستوى الصوت
                    });
                }, 1000);
            })();
        """.trimIndent()
        webView?.evaluateJavascript(script, null)
    }
}
