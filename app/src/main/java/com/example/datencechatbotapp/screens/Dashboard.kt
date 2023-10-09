package com.example.datencechatbotapp.screens

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.datencechatbotapp.R
import com.example.datencechatbotapp.api.FileUploadViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Dashboard(

) {
    val firstTime = remember { mutableStateOf(false) }
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
            val viewModel = viewModel<FileUploadViewModel>()
            RenderNameAndImage(viewModel)
        }
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(
                    MaterialTheme.colorScheme.background,
                    RoundedCornerShape(
                        topEnd = 36.dp,
                        topStart = 36.dp
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            if (firstTime.value) {
                Text(
                    text = "CONSULTANCY",
                    fontSize = 24.sp,
                    color = Color(0xFF98AF4E),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .background(Color.Transparent, RoundedCornerShape(28.dp))
                        .padding(vertical = 10.dp, horizontal = 40.dp),
                )
            } else {
                Text(
                    text = "Case Files",
                    fontSize = 22.sp,
                    color = Color(0xFF98AF4E),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                        .background(Color.Transparent, RoundedCornerShape(28.dp))
                        .padding(vertical = 10.dp, horizontal = 40.dp),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (firstTime.value) {
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
                            color = MaterialTheme.colorScheme.surface,
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
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.surface,
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
                            color = MaterialTheme.colorScheme.surface,
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
                } else {
                    LazyColumn {
                        items(6){ case->

                        }
                    }

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
                    text = "Create New Case +",
                    color = Color(75, 75, 75, 255),
                    fontSize = 18.sp
                )
            }
        }

    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(DelicateCoroutinesApi::class)
@Composable
fun RenderNameAndImage(
    viewModel: FileUploadViewModel
) {
    val context = LocalContext.current
    val scope = GlobalScope
    val name  = remember {
        mutableStateOf("Conor McGregor")
    }
    val image_url  = remember {
        mutableStateOf("https://i0.wp.com/www.howtomob.com/wp-content/uploads/2022/07/whatsapp-dp-for-boys-.jpg?ssl=1&resize=512%2C512")
    }

    scope.launch {
        try {
            val userName = viewModel.getUserName()
            val profilePic = viewModel.getProfilePicture()
            name.value = userName.body()?.get("username").toString().trim('"')
            image_url.value = profilePic.body()?.get("url").toString()
            Log.d("NAME", name.value)
            Log.d("IMAGE", image_url.value)
        }
        catch (e : Exception){
             Log.d("ERROR_", e.toString())
        }
    }

    Image(
        painter = rememberAsyncImagePainter(model = image_url.value),
        contentDescription = "userimage",
        modifier = Modifier
            .size(140.dp)
            .clip(CircleShape)
            .background(Color.Cyan)
    )
    Text(
        text = name.value ,
        fontSize = 16.sp,
        modifier = Modifier
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(28.dp))
            .padding(vertical = 10.dp, horizontal = 40.dp),
        color = MaterialTheme.colorScheme.surface,
    )
}
