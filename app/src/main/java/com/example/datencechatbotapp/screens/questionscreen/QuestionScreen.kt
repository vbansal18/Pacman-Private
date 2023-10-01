package com.example.datencechatbotapp.screens.questionscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datencechatbotapp.Question
import com.example.datencechatbotapp.R

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuestionScreen(
    question: Question,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(
//                brush = Brush.verticalGradient(
//                    colors = listOf(
//                        Color.White, Color(217, 251, 114, 255)
//                    )
//                )
//            )
//            .padding(20.dp)
            .shadow(4.dp, RoundedCornerShape(21.dp, 21.dp, 40.dp, 40.dp))
            .background(
                Color.White, RoundedCornerShape(40.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.16f)
        ) {
            Row(
                modifier = Modifier
                    .shadow(
                        8.dp,
                        RoundedCornerShape(21.dp),
                    )
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .background(
                        Color(217, 251, 114, 255), RoundedCornerShape(21.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,

                ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "KeyboardArrowLeft",
                    modifier = Modifier
                        .padding(12.dp)
                        .border(BorderStroke(1.5.dp, Color.Black), CircleShape)
                )
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = "${question.questionNumber}/9",
                    modifier = Modifier
                        .shadow(
                            6.dp,
                            CircleShape,
                        )
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(20.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.20f)
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${question.question}",
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.65f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(1f)
                        .background(Color.White),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pacman_watermark),
                        contentDescription = "pacman_watermark",
                        Modifier
                            .fillMaxWidth(0.6f)
                            .aspectRatio(1f)
                    )
                    BottomSheet(question.tags)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
                    .background(Color(241, 241, 241, 255)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(
                                0xFFD1F26E
                            )
                        ),
                        modifier = Modifier
                            .width(60.dp)
                            .aspectRatio(1f),
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "DoneBtn",
                            tint = Color.Gray,
                            modifier = Modifier.size(40.dp),
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    // Creating a variable to store the TextField value
                    var value by remember { mutableStateOf(TextFieldValue("")) }

                    // Creating a Basic TextField bu adding
                    // innerTextField that will display the Text hint
                    BasicTextField(
                        value = value,
                        textStyle = TextStyle(
                            color = Color.DarkGray,
                            fontSize = 16.sp,
                            fontWeight = FontWeight(300),
                        ),
                        onValueChange = { value = it },
                        decorationBox = { innerTextField ->
                            Row(
                                Modifier
                                    .fillMaxWidth(1f)
                                    .padding(16.dp)
                                    .background(Color.White, RoundedCornerShape(30.dp)),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically

                            ) {

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(.8f)
                                        .padding(horizontal = 8.dp),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically

                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.pacman_logo),
                                        contentDescription = "icn",
                                        modifier = Modifier
                                            .size(32.dp),
                                        colorFilter = ColorFilter.tint(Color(62, 62, 62, 255))

                                    )
                                    if (value.text.isEmpty()) {
                                        Text(
                                            "Add a tag...",
                                            Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp),
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight(300),
                                            color = Color(57, 57, 60, 125)
                                        )
                                    }
                                    innerTextField()
                                }
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier
                                        .width(45.dp)
                                        .aspectRatio(1f),
                                    contentPadding = PaddingValues(0.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(
                                            0xFFD1F26E
                                        )
                                    ),
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Done,
                                        contentDescription = "DoneBtn",
                                        tint = Color.Black
                                    )
                                }
                            }
                        },
                    )
                }
            }
        }

    }
}
