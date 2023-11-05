package com.example.datencechatbotapp.screens

import DraggableBtn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.datencechatbotapp.R
import com.example.datencechatbotapp.api.FileUploadViewModel
import com.example.datencechatbotapp.models.GetConsultancyResponse
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun GetStartedScreen(
    navController: NavHostController,
    source_screen: String?,
    viewModel: FileUploadViewModel
) {
    println(source_screen)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.onSecondary
            )
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(.16f),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "KeyboardArrowLeft",
                modifier = Modifier
                    .padding(top = 15.dp)
                    .size(30.dp)
                    .clickable { navController.popBackStack() },
                tint = MaterialTheme.colorScheme.surface
            )
        }
        Image(
            painter = rememberAsyncImagePainter(R.drawable.pdflogo),
            contentDescription = "pacman_logo",
            Modifier.size(164.dp),
        )
        if (source_screen == "dashboard") {
            WelcomeToPacman(navController)
        } else if (source_screen == "question") {
            Congrats(navController)
        } else if (source_screen == "congratulations") {
            Submitted(navController, viewModel)
        }
        else{
            WelcomeToPacman(navController)
        }
    }
}

@Composable
private fun Submitted(navController: NavHostController, viewModel: FileUploadViewModel) {
    val gson = Gson()
    var consultancy_data: GetConsultancyResponse? by remember { mutableStateOf(null) }
    var btnText by remember {
        mutableStateOf("Get Results")
    }
    Text(
        text = "Submitted!",
        fontSize = 28.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF8EA745),
        textAlign = TextAlign.Center
    )
    Text(
        text = "All your questions were answered and submitted successfully!",
        fontSize = 15.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(28.dp, 12.dp),
        color = MaterialTheme.colorScheme.surface

    )

    Row(
        modifier = Modifier
            .padding(top = 80.dp)
            .background(MaterialTheme.colorScheme.onTertiary, RoundedCornerShape(26.dp))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Button(
            onClick = {
                if (btnText == "Get Results") {
                    CoroutineScope(Dispatchers.IO).launch {
                        withContext(Dispatchers.Main){
                            btnText = "Loading Results"
                        }
                        withContext(Dispatchers.IO){
                            val consultancy = viewModel.getResponse()
                            if (consultancy.isSuccessful) {
                                consultancy_data = consultancy.body()!!
                                delay(2000L)
                                btnText = "See Results"
                            }
                        }
                    }
                } else if (btnText == "See Results") {
                    CoroutineScope(Dispatchers.IO).launch {
                        val argsConsultancy = async { gson.toJson(consultancy_data) }
                        println("GettingStarted consultancy : ${argsConsultancy.await()}")
                        withContext(Dispatchers.Main) {
                            if(argsConsultancy.await()!=null){
                                navController.navigate("consultancy/${argsConsultancy.await()}")
                            }
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(213, 245, 111, 255),
                contentColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(50.dp)
        ) {
            Text(
                text = btnText,
                color = Color(75, 75, 75, 255),
                fontSize = 18.sp
            )
        }
    }
}

@Composable
private fun Congrats(navController: NavHostController) {
    Text(
        text = "Congratulations!",
        fontSize = 28.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF8EA745),
        textAlign = TextAlign.Center
    )
    Text(
        text = "All your questions were answered successfully!",
        fontSize = 15.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(28.dp, 12.dp),
        color = MaterialTheme.colorScheme.surface

    )
    Row(
        modifier = Modifier
            .padding(top = 80.dp)
            .background(MaterialTheme.colorScheme.onTertiary, RoundedCornerShape(26.dp))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Button(
            onClick = {
                navController.navigate("gettingStarted/${"congratulations"}")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(213, 245, 111, 255),
                contentColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(50.dp)
        ) {
            Text(
                text = "Submit",
                color = Color(75, 75, 75, 255),
                fontSize = 18.sp
            )
        }

    }
}

@Composable
private fun WelcomeToPacman(navController: NavHostController) {
    Text(
        text = "Welcome to PacMan",
        fontSize = 28.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF8EA745),
        textAlign = TextAlign.Center
    )
    Text(
        text = "PacMan: Your AI legal guide, swiftly tackling hurdles for your dream projects",
        fontSize = 15.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(28.dp, 12.dp),
        color = MaterialTheme.colorScheme.surface

    )
    Row(
        modifier = Modifier
            .padding(top = 80.dp)
            .background(MaterialTheme.colorScheme.onTertiary, RoundedCornerShape(26.dp))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        DraggableBtn(navController, "question", "Get Started", null)
    }
}
