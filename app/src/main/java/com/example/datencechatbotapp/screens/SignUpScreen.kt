package com.example.datencechatbotapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.datencechatbotapp.R
import com.example.datencechatbotapp.screens.components.EmailTextField
import com.example.datencechatbotapp.screens.components.PasswordTextField

@Composable
fun Signup(navController: NavHostController) {
    Column(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.primary
                    )
                )
            )
            .padding(8.dp)
            .paint(
                painterResource(id = R.drawable.bg_home_screen),
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.background),
            )
            .padding(horizontal = 8.dp),

        ) {


        Column(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .fillMaxHeight(.540f)
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.Center,

            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp, 22.dp, 28.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sign Up",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight(500),
                        color = MaterialTheme.colorScheme.surface,
                    ),
                )
                Image(
                    painter = painterResource(id = R.drawable.pacman_logo_get_started),
                    contentDescription = "Datence logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(86.dp)
                )

            }
            EmailTextField(
                hint = "Email Address",
                icn = R.drawable.baseline_mail_24,
                bgcolor = MaterialTheme.colorScheme.background,
            )
            EmailTextField(
                hint = "Username",
                icn = R.drawable.baseline_person_24,
                bgcolor = MaterialTheme.colorScheme.background
            )
            PasswordTextField(
                hint = "Password",
                icn = R.drawable.baseline_lock_24,
            )
            PasswordTextField(
                hint = "Confirm Password",
                icn = R.drawable.baseline_key_24,
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
                onClick = {
                    navController.navigate("dashboard")
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
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
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(500.dp)),

                        )
                    Image(
                        painter = painterResource(id = R.drawable.instagram),
                        contentDescription = "fb logo",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(8.dp, 0.dp, 8.dp, 0.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(500.dp)),

                        )
                    Image(
                        painter = painterResource(id = R.drawable.pinterest),
                        contentDescription = "fb logo",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(8.dp, 0.dp, 8.dp, 0.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(500.dp)),


                        )
                    Image(
                        painter = painterResource(id = R.drawable.linkedin),
                        contentDescription = "fb logo",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(8.dp, 0.dp, 8.dp, 0.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(500.dp)),


                        )

                }
            }

        }


    }

}
