package com.dailyflow.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import com.dailyflow.app.ui.DailyFlowScreen

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<DailyFlowViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                DailyFlowScreen(viewModel)
            }
        }
    }
}
