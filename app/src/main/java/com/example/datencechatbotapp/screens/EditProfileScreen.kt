package com.example.datencechatbotapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.datencechatbotapp.R
import com.example.datencechatbotapp.screens.components.EmailTextField
import com.example.datencechatbotapp.screens.components.PasswordTextField

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditProfileScreen(navController: NavHostController) {
    Column(
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
            )
            .padding(vertical = 20.dp)
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.background, RoundedCornerShape(20.dp)),


        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(.125f)
                .padding(28.dp, 22.dp, 28.dp, 28.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "Cancel",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = MaterialTheme.colorScheme.surface,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(.2f).clickable {  }
            )
            Row(
                modifier = Modifier
                    .weight(.6f)
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top,


                ) {
                Text(
                    text = "Edit Profile",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        color = MaterialTheme.colorScheme.surface,
                    ),
                    textAlign = TextAlign.Center,
                )
            }
            Text(
                text = "Save",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = MaterialTheme.colorScheme.surface,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(.2f).clickable {  }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.TopEnd,
            ) {
                Image(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Image",
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .background(Color.Cyan)
                        .clickable {  }
                )
                Image(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = "Add",
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.background)
                        .clickable {  },
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface)
                )
            }
            NameField()

        }
        Spacer(modifier = Modifier.height(20.dp))
        Divider(thickness = 1.dp)
        LazyColumn(
            modifier = Modifier
                .padding(25.dp)
                .fillMaxSize()
        ) {
            items(1) {
                EmailTextField("Your Email", R.drawable.baseline_mail_24, bgcolor = MaterialTheme.colorScheme.background)
                PasswordTextField("Password", R.drawable.baseline_lock_24)
                BioField()
            }
        }
    }
}

@Composable
fun NameField() {
    var name by rememberSaveable { mutableStateOf("Conor McGregor") }

    TextField(
        value = name,
        textStyle = TextStyle(fontSize = 16.sp, color = Color.Black, textAlign = TextAlign.Center),
        onValueChange = { name = it },
        singleLine = true,
        modifier = Modifier
            .padding(top = 16.dp)
            .padding(horizontal = 70.dp)
            .background(Color(213, 245, 111, 255), RoundedCornerShape(50.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        ),

    )
}

@Composable
fun BioField() {
    var bio by rememberSaveable { mutableStateOf("") }

    TextField(
        value = bio,
        onValueChange = { bio = it },
        label = { Text("Your Bio") },
        singleLine = false,
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth()
            .height(150.dp)
            .background(
                MaterialTheme.colorScheme.background,
                RoundedCornerShape(10.dp)
            )
            .border(
                width = 1.dp,
                color = Color(181, 181, 181, 255),
                shape = RoundedCornerShape(size = 10.dp)
            ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedLabelColor = Color(179, 202, 103, 255),
            unfocusedLabelColor = Color(181, 181, 181, 255),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}