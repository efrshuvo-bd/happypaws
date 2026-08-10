package com.auramislab.happypaws

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.auramislab.happypaws.ui.navigation.HappyPawsNavHost
import com.auramislab.happypaws.ui.theme.HappyPawsTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Single activity shell. Navigation host + Hilt entry (HAP-15).
 * Design-system tokens remain deferred to HAP-23; Figma gated by HAP-19.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HappyPawsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    HappyPawsNavHost()
                }
            }
        }
    }
}
