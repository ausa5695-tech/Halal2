package com.altafaseel.app.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var isProtected by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("تطبيق التفاصيل - الحماية الذكية") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (isProtected) "الحماية مفعلة الآن 🛡️" else "الحماية متوقفة ⏸️",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { isProtected = !isProtected },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isProtected) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                )
            ) {
                Text(text = if (isProtected) "إيقاف الحماية" else "تشغيل الحماية")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "يتم منع الموسيقى وفلترة النساء أوتوماتيكياً عند تصفح تطبيقات التواصل الاجتماعي.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

