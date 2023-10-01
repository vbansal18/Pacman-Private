package com.example.datencechatbotapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
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
fun LoginLight() {

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
                .fillMaxHeight(.540f),
            verticalArrangement = Arrangement.Center,

            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(28.dp, 22.dp, 28.dp, 28.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Welcome",
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
                hint = "Email Address",
                tintColor = Color.Black,
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
                hint = "Password",
                tintColor = Color.Black,
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
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var isChecked by remember {
                        mutableStateOf(false)
                    }
                    Checkbox(
                        checked = isChecked, onCheckedChange = {isChecked=!isChecked},
                        modifier = Modifier
                            .scale(0.8f),
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFFD1F26E),
                            uncheckedColor = Color.Black,
                            checkmarkColor = Color.Black
                        )
                    )
                    Text(
                        text = "Remember Password",
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = Color.Black,
                    )
                }
                Text(
                    text = "Forgot your password?",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(600),
                    textDecoration = TextDecoration.Underline,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

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
                Text(text = "Login", color = Color.Black)
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
