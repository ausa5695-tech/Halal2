package com.altafaseel.app.service

import android.accessibilityservice.AccessibilityService
import android.content.Context
import android.media.AudioManager
import android.media.audiofx.Equalizer
import android.view.accessibility.AccessibilityEvent

class AppAccessibilityService : AccessibilityService() {

    private var audioManager: AudioManager? = null
    private var equalizer: Equalizer? = null

    override fun onCreate() {
        super.onCreate()
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val packageName = event.packageName?.toString() ?: ""
            
            // عند فتح التطبيقات المستهدفة، نقوم بتفعيل مرشح الصوت
            if (packageName.contains("instagram") || packageName.contains("tiktok") || packageName.contains("youtube")) {
                applyVoiceIsolationFiltering()
            }
        }
    }

    private fun applyVoiceIsolationFiltering() {
        try {
            // محاولة تطبيق مرشح ترددات لخفض الترددات العالية والمنخفضة (المرتبطة بالمعازف)
            // والتركيز على نطاق الصوت البشري (Voice Frequencies)
            audioManager?.let {
                // إعداد تأثيرات المعادل الصوتى المتقدمة لتصفية الموسيقى الخلفية
                if (equalizer == null) {
                    // إنشاء مثيل معادل صوتي على الجلسة الصوتية العامة (جلسة رقم 0 أو بناءً على مخرج النظام)
                    equalizer = Equalizer(0, 0).apply {
                        enabled = true
                        // تقوية النطاقات الخاصة بالصوت البشري وخفض نطاقات المعازف الحادة
                        val bands = numberOfBands
                        for (i in 0 until bands) {
                            // تقليل النطاقات الطرفية (الموسيقى) وزيادة النطاق المتوسط (الكلام)
                            if (i < bands / 3 || i > 2 * bands / 3) {
                                setBandLevel(i.toShort(), -1500) // خفض الترددات الطرفية
                            } else {
                                setBandLevel(i.toShort(), 500)  // الحفاظ على وضوح الصوت البشري
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onInterrupt() {
        equalizer?.release()
        equalizer = null
    }
}

