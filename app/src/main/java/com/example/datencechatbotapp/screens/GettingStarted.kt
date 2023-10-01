package com.example.datencechatbotapp.screens

import DraggableTextLowLevel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datencechatbotapp.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GetStartedScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        Color(205, 237, 107, 255)
                    )
                )
            )
            .padding(20.dp)
            .background(
                Color(241, 241, 241, 255), RoundedCornerShape(40.dp)
            )
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(.16f),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "KeyboardArrowLeft",
                modifier = Modifier
                    .padding(top = 15.dp)
                    .size(30.dp)
            )

        }
        Image(
            painter = painterResource(id = R.drawable.pacman_logo_get_started),
            contentDescription = "pacman_logo",
            Modifier.size(164.dp),
        )
        Text(
            text = "Welcome to PacMan",
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF8EA745),
            textAlign = TextAlign.Center
        )
        Text(
            text = "PacMan: Your AI legal guide, swiftly tackling hurdles for your dream projects",
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(28.dp, 8.dp)

        )
        Row(
            modifier = Modifier
                .padding(top = 80.dp)
                .background(Color.White, RoundedCornerShape(24.dp))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            DraggableTextLowLevel()
        }

    }
}