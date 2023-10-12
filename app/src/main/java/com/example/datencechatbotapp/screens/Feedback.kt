package com.example.datencechatbotapp.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.datencechatbotapp.models.TagItem
import com.example.datencechatbotapp.ui.theme.Green


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Feedback() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.primary
                    )
                )
            ),
        contentAlignment = Alignment.BottomCenter

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.secondary,
                            MaterialTheme.colorScheme.secondary,
                            MaterialTheme.colorScheme.primary
                        )
                    )
                ),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(.5f)
                    .background(
                        Color(217, 251, 114, 255),
                        RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)
                    )

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.2f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .weight(.1f),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                    ) {
                        Text(
                            text = "<",
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            fontSize = 26.sp,
                            fontWeight = FontWeight(300),
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(.5f)
                                .scale(scaleY = 2f, scaleX = 1f)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple(
                                        color = Color.Black,
                                        bounded = true,
                                        radius = 20.dp
                                    ),
                                    onClick = {

                                    }
                                )
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(.8f)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Feedback",
                            textAlign = TextAlign.Start,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
                Text(
                    text = "Share your feedback",
                    fontSize = 26.sp,
                    fontWeight = FontWeight(500),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 28.dp)
                )
                Text(
                    text = "Rate your experience", fontSize = 18.sp, modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 28.dp, top = 15.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 25.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "😍", fontSize = 24.sp,
                        modifier = Modifier
                            .padding(10.dp)
                            .background(Color(217, 217, 217, 255), RoundedCornerShape(50.dp))
                            .padding(10.dp)
                    )
                    Text(
                        text = "😍", fontSize = 24.sp,
                        modifier = Modifier
                            .padding(10.dp)
                            .background(Color(217, 217, 217, 255), RoundedCornerShape(50.dp))
                            .padding(10.dp)
                    )
                    Text(
                        text = "😍", fontSize = 24.sp,
                        modifier = Modifier
                            .padding(10.dp)
                            .background(Color(217, 217, 217, 255), RoundedCornerShape(50.dp))
                            .padding(10.dp)
                    )
                    Text(
                        text = "😍", fontSize = 24.sp,
                        modifier = Modifier
                            .padding(10.dp)
                            .background(Color(217, 217, 217, 255), RoundedCornerShape(50.dp))
                            .padding(10.dp)
                    )
                    Text(
                        text = "😍", fontSize = 24.sp,
                        modifier = Modifier
                            .padding(10.dp)
                            .background(Color(217, 217, 217, 255), RoundedCornerShape(50.dp))
                            .padding(10.dp)
                    )
                }

            }
        }

        BottomSection()
    }


}

@Composable
private fun BottomSection() {
    val items =  mutableListOf(
            TagItem("Delivery speed" , false),
            TagItem("Product quality" , false),
            TagItem("Customer support" , false),
            TagItem("Overall service" , false),
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.65f)
            .padding(horizontal = 20.dp)
            .background(
                MaterialTheme.colorScheme.background,
                RoundedCornerShape(topEnd = 40.dp, topStart = 40.dp)
            )
            .padding(30.dp)

    ) {
        Text(
            text = "What did you like?",
            fontSize = 18.sp,
            fontWeight = FontWeight(400),
            color = MaterialTheme.colorScheme.surface
        )
        OptionItems(items)
        Text(
            text = "Your comment (optional)",
            fontSize = 18.sp,
            fontWeight = FontWeight(400),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.padding(top = 40.dp)
        )
        var comment by remember { mutableStateOf(TextFieldValue("")) }

        TextField(
            label = {
                Text(text = "Describe your experience ")
            },
            value = comment,
            onValueChange = {comment = it},
            colors = TextFieldDefaults.colors(
                focusedSupportingTextColor = Color.Gray,
                unfocusedSupportingTextColor = Color.Gray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Color(234, 251, 180, 255),
                unfocusedContainerColor = Color(234, 251, 180, 255)
            ),
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .height(100.dp)
        )
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(213, 245, 111, 255),
                contentColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(top = 30.dp)
        ) {
            Text(
                text = "Send",
                color = Color(75, 75, 75, 255),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun OptionItems(
    names: MutableList<TagItem>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        for (item in names) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = item.name,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(300),
                        modifier = Modifier
                            .weight(.8f),
                        color = MaterialTheme.colorScheme.surface,
                    )
                    var isActive by remember { mutableStateOf(item.isChecked) }

                    CircleCheckbox(isActive, true) { isActive = !isActive }
                }
                Spacer(Modifier.height(5.dp))
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = Dp.Hairline,
                )
            }
        }
    }
}

@Composable
fun CircleCheckbox(selected: Boolean, enabled: Boolean = true, onChecked: () -> Unit) {

    val imageVector = if (selected) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle
    val tint = if (selected) Color(105, 204, 5, 255) else Color(217, 217, 217, 255)
    val background = if (selected) Color.White else Color(217, 217, 217, 255)

    IconButton(onClick = { onChecked() },
        modifier = Modifier.offset(x = 4.dp, y = 4.dp),
        enabled = enabled) {

        Icon(imageVector = imageVector, tint = tint,
            modifier = Modifier.background(background, shape = CircleShape),
            contentDescription = "checkbox")
    }
}
