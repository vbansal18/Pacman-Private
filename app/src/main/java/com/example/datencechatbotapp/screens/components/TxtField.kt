package com.example.datencechatbotapp.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TxtField(
    hint: String?,
    icn: Int?,
    modifier: Modifier = Modifier,
    hintColor : Color,
    tintColor : Color,
) {
    // Creating a variable to store the TextField value
    var value by remember { mutableStateOf(TextFieldValue("")) }

    // Creating a Basic TextField bu adding
    // innerTextField that will display the Text hint
    BasicTextField(
        value = value,
        onValueChange = { value = it },
        decorationBox = { innerTextField ->
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically

            ) {
                if(icn!=null) {
                    Image(
                        painter = painterResource(icn),
                        contentDescription = "icn",
                        modifier = Modifier
                            .size(24.dp),
                        colorFilter = ColorFilter.tint(tintColor)

                    )
                }
                if (value.text.isEmpty()) {
                    Text(
                        "$hint",
                        Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp),
                        fontSize = 14.sp,
                        fontWeight = FontWeight(300),
                        color = hintColor,
                        textAlign = TextAlign.Start
                    )
                }
                // <-- Add this
                innerTextField()
            }
        },
    )

}
