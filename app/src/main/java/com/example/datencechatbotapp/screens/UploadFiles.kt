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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datencechatbotapp.R
import com.example.datencechatbotapp.screens.components.TxtField

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UploadFiles(

) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.65f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.95f)
                    .shadow(
                        8.dp,
                        RoundedCornerShape(0.dp, 0.dp, 40.dp, 40.dp)
                    )
                    .background(
                        Color(217, 251, 114, 255), RoundedCornerShape(
                            bottomEnd = 28.dp,
                            bottomStart = 28.dp
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cloud),
                    contentDescription = "cloud_img",
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .aspectRatio(1f)
                        .background(Color.White, CircleShape)
                        .padding(50.dp),
                )
                Image(
                    painter = painterResource(id = R.drawable.file_upload),
                    contentDescription = "file_img",
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .scale(3f)
                )
                Text(
                    text = "Upload your files",
                    fontSize = 25.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF1F1F1F),
                    modifier = Modifier.padding(top = 40.dp)
                )
                Text(
                    text = "Browse and choose the files you want to upload",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = "Max file size  : 20 MB",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF6B6B6B),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 40.dp, bottom = 20.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Black
                    ),
                    shape = CircleShape,
                    modifier = Modifier
                        .width(60.dp)
                        .aspectRatio(1f),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "+",
                        fontSize = 32.sp,
                        fontWeight = FontWeight(300)
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(top = 40.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(.5f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    thickness = 0.7.dp,
                    color = Color.Black,
                    modifier = Modifier
                        .weight(.3f)
                )
                Text(
                    text = "OR",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                Divider(
                    thickness = 0.7.dp,
                    color = Color.Black,
                    modifier = Modifier
                        .weight(.3f)
                )
            }
        }

        TxtField(
            tintColor = Color.White,
            hint = "Paste your link here....",
            icn = null,
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(
                    Color(234, 251, 180, 255),
                    RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 20.dp, vertical = 14.dp),
            hintColor = Color(0xFF656565)
        )
    }

}