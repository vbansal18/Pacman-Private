package com.example.datencechatbotapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datencechatbotapp.R
import com.example.datencechatbotapp.screens.components.TxtField

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignupLight() {

    Column(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF0F0F0), Color(0xFFDCFE73)
                    )
                )
            )
            .padding(15.dp)
            .paint(
                painterResource(id = R.drawable.bg_home_screen, ),
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.tint(Color.White),
            )
            .padding(20.dp),

        ) {


        Column(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .fillMaxHeight(.580f),
            verticalArrangement = Arrangement.Top,

            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(28.dp, 22.dp, 28.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sign Up",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight(500),
                        color = Color.Black,
                    ),
                )
                Image(
                    painter = painterResource(id = R.drawable.pacman_logo_get_started),
                    contentDescription = "Datence logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(86.dp)
                )

            }
            TxtField(
                tintColor = Color.Black,
                hint = "Email Address",
                icn = R.drawable.baseline_mail_24,
                hintColor = Color.Black,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(10.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .padding(horizontal = 20.dp, vertical = 10.dp),
            )
            TxtField(
                tintColor = Color.Black,
                hint = "Username",
                icn = R.drawable.baseline_person_24,
                hintColor = Color.Black,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(10.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .padding(horizontal = 20.dp, vertical = 10.dp),

                )
            TxtField(
                tintColor = Color.Black,
                hint = "Password",
                icn = R.drawable.baseline_lock_24,
                hintColor = Color.Black,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(10.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .padding(horizontal = 20.dp, vertical = 10.dp),

                )
            TxtField(
                tintColor = Color.Black,
                hint = "Confirm Password",
                icn = R.drawable.baseline_key_24,
                hintColor = Color.Black,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(10.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                )
        }
        Column(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .fillMaxHeight(0.450f)
                .padding(0.dp, 0.dp, 12.dp, 12.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End

        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD1F26E)),
                contentPadding = PaddingValues(
                    start = 34.dp,
                    top = 12.dp,
                    end = 34.dp,
                    bottom = 12.dp,
                ),
            ) {
                Text(text = "Sign Up", color = Color.Black)
                Image(
                    painter = painterResource(id = R.drawable.btn_enter_icn),
                    contentDescription = "btn_enter_icn",
                    colorFilter = ColorFilter.tint(Color.Black),
                    modifier = Modifier
                        .padding(2.dp, 2.dp, 0.dp, 0.dp)
                        .size(20.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxSize(1f)
                .align(Alignment.CenterHorizontally)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 24.dp, 0.dp, 0.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Or Connect With", color = Color(0xFFFFFFFF)
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = "fb logo",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(8.dp, 0.dp, 8.dp, 0.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.instagram),
                        contentDescription = "fb logo",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(8.dp, 0.dp, 8.dp, 0.dp)

                    )
                    Image(
                        painter = painterResource(id = R.drawable.pinterest),
                        contentDescription = "fb logo",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(8.dp, 0.dp, 8.dp, 0.dp)

                    )
                    Image(
                        painter = painterResource(id = R.drawable.linkedin),
                        contentDescription = "fb logo",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(8.dp, 0.dp, 8.dp, 0.dp)

                    )

                }
            }

        }


    }

}
