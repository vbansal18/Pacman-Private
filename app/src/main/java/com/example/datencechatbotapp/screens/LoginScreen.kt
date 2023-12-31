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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.datencechatbotapp.R
import com.example.datencechatbotapp.screens.components.EmailTextField
import com.example.datencechatbotapp.screens.components.PasswordTextField

@Composable
fun Login(navController: NavHostController) {
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
                    .padding(0.dp, 22.dp, 0.dp, 28.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Welcome",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight(500),
                        color = MaterialTheme.colorScheme.surface,
                    ),
                )
                Image(
                    painter = rememberAsyncImagePainter(R.drawable.pdflogo),
                    contentDescription = "Datence logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(86.dp),
                )
            }
            EmailTextField(
                hint = "Username",
                icn = R.drawable.baseline_person_24,
                bgcolor = MaterialTheme.colorScheme.background,
                null
            )
            PasswordTextField(
                hint = "Password",
                icn = R.drawable.baseline_lock_24,
            )
            Row(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(.4f)
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    var isChecked by remember {
                        mutableStateOf(false)
                    }
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { isChecked = !isChecked },
                        modifier = Modifier
                            .fillMaxWidth(.2f)
                            .scale(0.8f),
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.primary,
                            uncheckedColor = MaterialTheme.colorScheme.surface,
                            checkmarkColor = Color.Black
                        )
                    )
                    Text(
                        text = "Remember",
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = MaterialTheme.colorScheme.surface,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(start = 5.dp)

                    )

                }
                Row(
                    modifier = Modifier.fillMaxSize(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End

                ) {
                    Text(
                        text = "Forgot your password?",
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600),
                        textDecoration = TextDecoration.Underline,
                        color = MaterialTheme.colorScheme.surfaceTint,
                        textAlign = TextAlign.End
                    )
                }
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
                onClick = { navController.navigate("dashboard")},
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
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
