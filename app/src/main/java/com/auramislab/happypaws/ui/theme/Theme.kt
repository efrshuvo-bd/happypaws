package com.auramislab.happypaws.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

/**
 * Default Material 3 theme baseline for HAP-14.
 * Design tokens / brand theme arrive in a later Task (HAP-23); do not invent Figma tokens here.
 */
@Composable
fun HappyPawsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) darkColorScheme() else lightColorScheme()
    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}
