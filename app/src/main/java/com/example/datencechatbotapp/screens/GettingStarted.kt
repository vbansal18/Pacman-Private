package com.example.datencechatbotapp.screens

import DraggableBtn
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.datencechatbotapp.R
import com.example.datencechatbotapp.api.FileUploadViewModel
import com.example.datencechatbotapp.models.GetConsultancyResponse
import com.example.datencechatbotapp.models.UploadAnswersResponse
import com.example.datencechatbotapp.uploadAnswersResponse
import com.google.gson.Gson
import com.google.gson.JsonObject
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
    viewModel: FileUploadViewModel,
) {
    val gson = Gson()
    var responses_ by remember { mutableStateOf<String?>(null) }
    var responses by remember { mutableStateOf<JsonObject?>(null) }

    LaunchedEffect(Unit) {
        launch {
            if (source_screen == "question") {
                val parsedResponse = async {
                    gson.toJson(uploadAnswersResponse, UploadAnswersResponse::class.java)
                }
                responses_ = parsedResponse.await()
            }
            if (responses_ != null) {
                val parsedResponse = async {
                    gson.fromJson(
                        responses_,
                        JsonObject::class.java
                    )
                }
                responses = parsedResponse.await()
            }
        }
    }

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
            println("Getting started screen : $responses")
            if (responses != null) {
                Congrats(navController, viewModel, responses!!)
            }
        } else if (source_screen == "congratulations") {
            Submitted(navController, viewModel)
        } else {
            WelcomeToPacman(navController)
        }
    }
}
var consultancy_data: GetConsultancyResponse? by mutableStateOf(null)

@Composable
private fun Submitted(navController: NavHostController, viewModel: FileUploadViewModel) {
    val gson = Gson()
    var btnText by remember {
        mutableStateOf("Get Results")
    }
    val context = LocalContext.current
    if (btnText == "Loading Results") {
        Text(
            text = "Generating Results!",
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF8EA745),
            textAlign = TextAlign.Center
        )
        Text(
            text = "\nPacman AI is generating results.\n Please be patient and do not close the app as it might take upto 2 minutes",
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(28.dp, 12.dp),
            color = MaterialTheme.colorScheme.surface

        )
        Spacer(modifier = Modifier.height(40.dp))
        IndeterminateCircularIndicator()
    } else {
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
                            withContext(Dispatchers.Main) {
                                btnText = "Loading Results"
                            }
                            withContext(Dispatchers.IO) {
                                try {
                                    println("Getting consultancy from backend")
                                    val consultancy = viewModel.getResponse()
                                    if (consultancy.isSuccessful) {
                                        consultancy_data = consultancy.body()!!
                                        withContext(Dispatchers.Main) {
                                            btnText = "See Results"
                                            Log.d("Success", consultancy_data.toString())
                                        }
                                        Log.d("Success", consultancy.toString())
                                    } else {
                                        withContext(Dispatchers.Main) {
                                            btnText = "Failed"
                                            Log.d("Success", consultancy.toString())
                                            Log.d("Success", consultancy.errorBody().toString())
                                            Log.d("Success", consultancy.body().toString())
                                        }
                                    }
                                } catch (e: Exception) {
                                    Log.d("Error", e.message.toString())
                                }
                            }
                        }
                    } else if (btnText == "See Results") {
                        CoroutineScope(Dispatchers.IO).launch {
                            val argsConsultancy = async { gson.toJson(consultancy_data) }
                            println("GettingStarted consultancy : ${argsConsultancy.await()}")
                            withContext(Dispatchers.Main) {
                                if (argsConsultancy.await() != null) {
                                    navController.navigate("consultancy")
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
}

@Composable
fun TimeElapsed() {
    var time by remember {
        mutableStateOf(0)
    }
    var secondsDisplayed by remember {
        mutableStateOf(0)
    }
    var minutesText by remember {
        mutableStateOf("minute")
    }
    var secondsText by remember {
        mutableStateOf("second")
    }
    if (time > 1) {
        minutesText = "minutes"
    }
    if (secondsDisplayed > 1) {
        secondsText = "seconds"
    }
    if (secondsDisplayed < 2) {
        secondsText = "second"
    }
    LaunchedEffect(secondsDisplayed) {
        delay(1000L)
        secondsDisplayed++
        if (secondsDisplayed % 60 == 0) {
            time++
            secondsDisplayed = 0
        }
    }
    if (time == 0) {
        Text(
            text = "Time Elapsed : $secondsDisplayed $secondsText",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.surface
        )
    } else {
        Text(
            text = "Time Elapsed : $time $minutesText $secondsDisplayed $secondsText",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.surface
        )
    }
}

@Composable
private fun Congrats(
    navController: NavHostController,
    viewModel: FileUploadViewModel,
    responses: JsonObject
) {
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
                CoroutineScope(Dispatchers.IO).launch {
                    println("responsesJsonObject : $responses")
                    val uploadAnswersResponse = viewModel.uploadAnswers(responses)
                    if (uploadAnswersResponse.isSuccessful) {
                        withContext(Dispatchers.Main) {
                            Log.d("Success", uploadAnswersResponse.body().toString())
                            navController.navigate("gettingStarted/${"congratulations"}")
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
        text = "Before the run, kindly answer these set of questions to help us gain momentum for your sprint.",
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

@Composable
fun IndeterminateCircularIndicator() {
    Column(
        Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary,
        )
        Spacer(modifier = Modifier.height(60.dp))
        TimeElapsed()
    }

}
