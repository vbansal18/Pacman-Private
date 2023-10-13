package com.example.datencechatbotapp.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun EmailTextField(
    hint: String,
    icn: Int?,
    bgcolor : Color
    ) {
    var email by rememberSaveable { mutableStateOf("") }

    TextField(
        value = email,
        onValueChange = { email = it },
        label = { Text(hint) },
        singleLine = true,
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color(181, 181, 181, 255),
                shape = RoundedCornerShape(size = 10.dp)
            ),
        leadingIcon = {
            if (icn!=null)
                Image(painter = painterResource(id = icn), contentDescription = "", colorFilter = ColorFilter.tint(Color(181, 181, 181, 255)))
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = bgcolor,
            unfocusedContainerColor = bgcolor,
            focusedLabelColor = Color(179, 202, 103, 255),
            unfocusedLabelColor = Color(181, 181, 181, 255),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}
