package com.example.datencechatbotapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datencechatbotapp.R

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Dashboard(

) {
    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(Color(217, 251, 114, 255))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.1f),
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
                    text = "Dashboard",
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
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
                    text = "...",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 26.sp,
                    fontWeight = FontWeight(800),
                    modifier = Modifier
                        .fillMaxWidth()
                        .rotate(90f)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 200.dp)
                .fillMaxHeight(.34f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = "userimage",
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(Color.Cyan)
            )
            Text(
                text = "Conor McGregor",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.White, RoundedCornerShape(28.dp))
                    .padding(vertical = 10.dp, horizontal = 40.dp),
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(
                    Color.White,
                    RoundedCornerShape(
                        topEnd = 28.dp,
                        topStart = 28.dp
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "CONSULTANCY",
                fontSize = 24.sp,
                color = Color(152, 175, 78, 255),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(Color.White, RoundedCornerShape(28.dp))
                    .padding(vertical = 10.dp, horizontal = 40.dp),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(.15f),
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
                        .weight(.7f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.consultancy_image),
                        contentDescription = "consultancy_image",
                        modifier = Modifier
                            .size(150.dp)
                    )
                    Text(
                        text = "PacMan: Your AI legal guide, swiftly tackling hurdles for your dream projects",
                        fontSize = 17.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(.15f),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                ) {

                    Text(
                        text = ">",
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontSize = 26.sp,
                        fontWeight = FontWeight(300),
                        modifier = Modifier
                            .fillMaxWidth()
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
            }
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(213, 245, 111, 255),
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .padding(32.dp, 32.dp, 32.dp, 50.dp)
                    .fillMaxWidth(1f)
            ) {
                Text(
                    text = "Add Case +",
                    color = Color(75, 75, 75, 255),
                    fontSize = 18.sp
                )
            }
        }

    }
}