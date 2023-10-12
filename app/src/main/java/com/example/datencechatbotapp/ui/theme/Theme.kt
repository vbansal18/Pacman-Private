package com.example.datencechatbotapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Green,
    secondary = Color.Black,
    tertiary = Color.Black,
    background = Gray,
    surface = Color(0xFFFFFFFF),

    //    Dashboard Profile Name bg color
    onBackground = DarkGray,

    //    TextFieldIconTintColor
    surfaceVariant = Color(0xFF8B8B8B),

    //    TextFieldText
    surfaceTint = Color(0xFFD1F26E),

    onTertiary = LightGray,
    onSecondary = Gray

    )

private val LightColorScheme = lightColorScheme(
    primary = Green,
    secondary = Color(0xFFF0F0F0),
    tertiary = Color.White,
    background = Color(0xFFFFFFFF),
    surface = Color(0xFF000000),

//    Dashboard Profile Name bg color
    onBackground = Color(0xFFF1F1F1),

//    TextFieldIconTintColor
    surfaceVariant = Color.Black,

//    TextFieldText
    surfaceTint = Color.Black,

    onTertiary = Color.White,

    onSecondary = Color(0xFFF1F1F1),

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun DatenceChatbotAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColorScheme
    } else {
        DarkColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}