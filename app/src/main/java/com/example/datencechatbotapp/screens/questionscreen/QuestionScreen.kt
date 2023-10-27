package com.example.datencechatbotapp.screens.questionscreen

import CountryPicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.datencechatbotapp.AllQuestionItems
import com.example.datencechatbotapp.R

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuestionScreen(navController: NavHostController) {
    RenderQuestions(navController)
}

@Composable
private fun RenderQuestions(
    navController: NavHostController
) {
    val allQuestions by remember {
        mutableStateOf(AllQuestionItems)
    }
    val i = remember {
        mutableStateOf(0)
    }
    var questionItem = allQuestions[i.value]
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.onTertiary, RoundedCornerShape(40.dp),
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
                        RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp),
                    )
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .background(
                        Color(217, 251, 114, 255)
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
                        .clickable(
                            onClick = {
                                if (i.value == 0) {
                                    navController.popBackStack()
                                } else {
                                    questionItem = allQuestions[i.value--]
                                }
                            }
                        )
                )
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = "${questionItem.questionNumber}/${allQuestions.size}",
                    modifier = Modifier
                        .shadow(
                            6.dp,
                            CircleShape,
                        )
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.tertiary)
                        .padding(20.dp),
                    color = MaterialTheme.colorScheme.surface
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
                text = questionItem.question,
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.surface
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
                if(questionItem.questionNumber==1){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(1f)
                            .background(
                                MaterialTheme.colorScheme.onBackground,
                                RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                            ),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        CountryPicker()
                    }
                }
                else if(questionItem.questionNumber==5 || questionItem.questionNumber==7){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(1f)
                            .background(
                                MaterialTheme.colorScheme.onBackground,
                                RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                            ),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        CountryPicker()
                    }
                }
                else if(questionItem.questionNumber==8){

                }
                else if (questionItem.tags != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(1f)
                            .background(MaterialTheme.colorScheme.onTertiary),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pacman_watermark),
                            contentDescription = "pacman_watermark",
                            Modifier
                                .fillMaxWidth(0.6f)
                                .aspectRatio(1f),
                            colorFilter = ColorFilter.tint(Color(240, 251, 205, 255))
                        )
                        BottomSheet(
                            tags = questionItem.tags!!,
                            onTagsChanged = { updatedTags ->
                                // Update the tags of the current questionItem
                                allQuestions[i.value].tags = updatedTags

                            }
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
                    .background(MaterialTheme.colorScheme.onBackground),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                        },
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
                            tint = Color.Black,
                            modifier = Modifier
                                .size(40.dp)
                                .clickable(
                                    onClick = {
                                        if (i.value == allQuestions.size - 1) {
                                            navController.navigate("gettingStarted")
                                        } else {
                                            questionItem = allQuestions[i.value++]
                                        }
                                    }
                                )
                        )
                    }
                }
            }
        }

    }
}