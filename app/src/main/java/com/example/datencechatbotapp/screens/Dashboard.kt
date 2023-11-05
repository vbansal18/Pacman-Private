@file:OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)

package com.example.datencechatbotapp.screens

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.datencechatbotapp.R
import com.example.datencechatbotapp.api.FileUploadViewModel
import com.example.datencechatbotapp.data.preferences.PreferencesDatastore
import com.example.datencechatbotapp.models.GetAllCasesModel
import com.example.datencechatbotapp.models.GetConsultancyResponse
import com.example.datencechatbotapp.screens.components.SettingsDropDown
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun Dashboard(
    navController: NavHostController,
    datastore: PreferencesDatastore,
    viewModel: FileUploadViewModel
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
                onClick = { },
                modifier = Modifier.weight(.1f),
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
                        .clickable(interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(
                                color = Color.Black, bounded = true, radius = 20.dp
                            ),
                            onClick = {
                                navController.popBackStack()
                            })
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
            SettingsDropDown(navController, Color.Black)
        }
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
        val launcher =
            rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let {
                    selectedImageUri = it
                    scope.launch {
                        try {
                            val response =
                                viewModel.changeProfilePicture(selectedImageUri!!, context)
                            if (response.isSuccessful) {
                                Log.d("SUCCESSFULLVAJHHDui", response.body().toString())
                            }
                        } catch (e: Exception) {
                            Log.d("ERRORERDHJHT", "Error: ${e.message}}")

                        }
                    }

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
            RenderNameAndImage(viewModel, datastore)
        }
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(
                    MaterialTheme.colorScheme.background, RoundedCornerShape(
                        topEnd = 36.dp, topStart = 36.dp
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            if (firstTime.value) {
                val pagerState = rememberPagerState(
                    initialPage = 0, initialPageOffsetFraction = 0f
                ) {
                    3
                    // provide pageCount
                }

                HorizontalPager(state = pagerState) { page ->
                    when (page) {

                        0 -> {
                            Consultancy(pagerState)
                        }

                        1 -> {
                            Suggestions()
                        }

                        2 -> {
                            LeadGeneration(navController)
                        }

                    }
                }

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
                RenderAllCases(viewModel, navController)

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RenderAllCases(viewModel: FileUploadViewModel, navController: NavHostController) {

    var cases by remember { mutableStateOf<GetAllCasesModel?>(null) }
    LaunchedEffect(Unit) {
        try {
            val parsedcases = async { viewModel.getAllCases().body() }
            cases = parsedcases.await()
        } catch (e: Exception) {
            Log.d("CasesError", "Error : $e")
        }
    }
    var no_of_cases by remember {
        mutableStateOf(0)
    }
    if (cases != null) {
        no_of_cases = cases!!.allCases.size
    }
    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(.8f)
                .verticalScroll(rememberScrollState())
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            FlowRow(
                modifier = Modifier
                    .fillMaxSize(),
                maxItemsInEachRow = 2,
            ) {
                cases?.let {
                    for (i in 0 until no_of_cases) {
                        Column(modifier = Modifier
                            .fillMaxWidth(.5f)
                            .padding(15.dp, 0.dp, 15.dp, 15.dp)
                            .aspectRatio(1f)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(248, 130, 87, 255), Color(152, 56, 22, 255)

                                    )
                                ), RoundedCornerShape(20.dp)
                            )
                            .clickable {
                                navController.navigate("allLeadsScreen/${i}")
                            }
                            .padding(20.dp)) {
                            Text(
//                            text = cases!!.allCases[i].timestamp,
                                text = "12/10",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight(700)
                            )
                            Text(
                                text = "Case : ${i + 1}",
                                color = Color.White,
                                fontWeight = FontWeight(500)
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable {
                                        // on below line we are checking permission
                                        if (checkPermissions(context)) {
                                            // if permission is granted we are displaying a toast message.
                                            Toast
                                                .makeText(
                                                    context, "Permissions Granted..", Toast.LENGTH_SHORT
                                                )
                                                .show()
                                        } else {
                                            // if the permission is not granted
                                            // we are calling request permission method.
                                            requestPermission(activity!!)
                                        }

                                        generatePDF(context, cases!!.allCases[i].consultancy)
                                    }, verticalArrangement = Arrangement.Bottom
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Download",
                                        color = Color.White,
                                        fontWeight = FontWeight(500),
                                        fontSize = 12.sp
                                    )
                                    Image(
                                        imageVector = Icons.Filled.Download,
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(
                                            Color.White
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        Button(
            onClick = {navController.navigate("upload")},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(213, 245, 111, 255),
                contentColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(70.dp)
                .padding(vertical = 10.dp)
        ) {
            Text(
                text = "Create New Case",
                color = Color(75, 75, 75, 255),
                fontSize = 16.sp
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("CoroutineCreationDuringComposition", "UnrememberedMutableState")
@Composable
fun RenderNameAndImage(
    viewModel: FileUploadViewModel, datastore: PreferencesDatastore
) {
    var name by remember {
        mutableStateOf("Conor McGregor")
    }
    LaunchedEffect(Unit) {
        val parsedName = async { viewModel.getUserName().body() }
        name = parsedName.await()?.get("username").toString().trim('"')
        datastore.setUsername(name)
        Log.d("TAG", "In launchedEffect name : $name")
    }

//    var imageBitmap: Bitmap? by remember { mutableStateOf(null) }
//
//    LaunchedEffect(viewModel.imageService) {
//        val response = viewModel.imageService.getProfilePicture().execute()
//        if (response.isSuccessful) {
//            val imageBytes = response.body()?.bytes()
//            if (imageBytes != null) {
//                imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
//            }
//        }
//    }
//
//    imageBitmap?.let { bitmap ->
//        Image(
//            bitmap = bitmap.asImageBitmap(),
//            contentDescription = "Image",
//            modifier = Modifier
//                .size(140.dp)
//                .clip(CircleShape)
//                .background(Color.Cyan)
//        )
//    }
    Image(
        painter = painterResource(id = R.drawable.baseline_person_24),
        contentDescription = "Image",
        modifier = Modifier
            .size(140.dp)
            .clip(CircleShape)
            .background(Color.Cyan)
    )

    Text(
        text = name,
        fontSize = 16.sp,
        modifier = Modifier
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(28.dp))
            .padding(vertical = 10.dp, horizontal = 40.dp),
        color = MaterialTheme.colorScheme.surface,
    )
}

@Composable
private fun Consultancy(pagerState: PagerState) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

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
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(.15f),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
            ) {

                Text(
                    text = "",
                    color = MaterialTheme.colorScheme.surface,
                    textAlign = TextAlign.Center,
                    fontSize = 26.sp,
                    fontWeight = FontWeight(300),
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(scaleY = 2f, scaleX = 1f)
                        .clickable(interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(
                                color = Color.Black, bounded = true, radius = 20.dp
                            ),
                            onClick = {

                            })
                )
            }
            Column(
                modifier = Modifier.weight(.7f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Image(
                    painter = painterResource(id = R.drawable.consultancy_image),
                    contentDescription = "consultancy_image",
                    modifier = Modifier.size(150.dp)
                )
                Text(
                    text = "PacMan: Your AI legal guide, swiftly tackling hurdles for your dream projects",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.fillMaxWidth(.9f)
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(.15f),
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
                        .clickable(interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(
                                color = Color.Black, bounded = true, radius = 20.dp
                            ),
                            onClick = {})
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Image(
                imageVector = Icons.Filled.Circle,
                contentDescription = "Circle",
                Modifier.scale(.4f),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface)
            )
            Image(
                imageVector = Icons.Outlined.Circle,
                contentDescription = "Circle",
                Modifier.scale(.4f),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface)
            )
            Image(
                imageVector = Icons.Outlined.Circle,
                contentDescription = "Circle",
                Modifier.scale(.4f),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface)
            )
        }

    }
}

@Composable
private fun Suggestions() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "SUGGESTIONS",
            fontSize = 24.sp,
            color = Color(0xFF98AF4E),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .background(Color.Transparent, RoundedCornerShape(28.dp))
                .padding(vertical = 10.dp, horizontal = 40.dp),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(.15f),
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
                        .clickable(interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(
                                color = Color.Black, bounded = true, radius = 20.dp
                            ),
                            onClick = {

                            })
                )
            }
            Column(
                modifier = Modifier.weight(.7f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Image(
                    painter = painterResource(id = R.drawable.consultancy_image),
                    contentDescription = "consultancy_image",
                    modifier = Modifier.size(150.dp)
                )
                Text(
                    text = "PacMan: Your AI legal guide, swiftly tackling hurdles for your dream projects",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.fillMaxWidth(.9f)
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(.15f),
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
                        .clickable(interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(
                                color = Color.Black, bounded = true, radius = 20.dp
                            ),
                            onClick = {

                            })
                )

            }

        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Image(
                imageVector = Icons.Outlined.Circle,
                contentDescription = "Circle",
                Modifier.scale(.4f),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface)
            )
            Image(
                imageVector = Icons.Filled.Circle,
                contentDescription = "Circle",
                Modifier.scale(.4f),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface)
            )
            Image(
                imageVector = Icons.Outlined.Circle,
                contentDescription = "Circle",
                Modifier.scale(.4f),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface)
            )
        }
    }
}

@Composable
private fun LeadGeneration(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "LEAD GENERATION",
            fontSize = 24.sp,
            color = Color(0xFF98AF4E),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .background(Color.Transparent, RoundedCornerShape(28.dp))
                .padding(vertical = 10.dp, horizontal = 40.dp),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(.15f),
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
                        .clickable(interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(
                                color = Color.Black, bounded = true, radius = 20.dp
                            ),
                            onClick = {

                            })
                )
            }
            Column(
                modifier = Modifier.weight(.7f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Image(
                    painter = painterResource(id = R.drawable.consultancy_image),
                    contentDescription = "consultancy_image",
                    modifier = Modifier.size(150.dp)
                )
                Text(
                    text = "PacMan: Your AI legal guide, swiftly tackling hurdles for your dream projects",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.fillMaxWidth(.9f)
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(.15f),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
            ) {

                Text(
                    text = "",
                    color = MaterialTheme.colorScheme.surface,
                    textAlign = TextAlign.Center,
                    fontSize = 26.sp,
                    fontWeight = FontWeight(300),
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(scaleY = 2f, scaleX = 1f)
                        .clickable(interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(
                                color = Color.Black, bounded = true, radius = 20.dp
                            ),
                            onClick = {

                            })
                )
            }

        }
        Button(
            onClick = {
                navController.navigate("gettingStarted/${"dashboard"}")
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color(213, 245, 111, 255), contentColor = Color.Black
            ), modifier = Modifier
                .padding(32.dp, 32.dp, 32.dp, 50.dp)
                .fillMaxWidth(1f)
        ) {
            Text(
                text = "Continue", color = Color(75, 75, 75, 255), fontSize = 18.sp
            )
        }
    }
}

fun checkPermissions(context: Context): Boolean {
    // on below line we are creating a variable for both of our permissions.

    // on below line we are creating a variable for writing to external storage permission
    val writeStoragePermission = ContextCompat.checkSelfPermission(
        context, Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    // on below line we are creating a variable for
    // reading external storage permission
    val readStoragePermission = ContextCompat.checkSelfPermission(
        context, Manifest.permission.READ_EXTERNAL_STORAGE
    )

    // on below line we are returning true if both the
    // permissions are granted and returning false if permissions are not granted.
    return writeStoragePermission == PackageManager.PERMISSION_GRANTED && readStoragePermission == PackageManager.PERMISSION_GRANTED
}

// on below line we are creating a function to request permission.
fun requestPermission(activity: Activity) {

    // on below line we are requesting read and write to
    // storage permission for our application.
    ActivityCompat.requestPermissions(
        activity, arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
        ), 101
    )
}


@RequiresApi(Build.VERSION_CODES.O)
fun generatePDF(context: Context, consultancy: GetConsultancyResponse) {
    val fileName = "Pacman Report.pdf"
    val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    val file = File(directory, fileName)

    if (!directory.exists()) {
        directory.mkdirs()
    }

    val pdfDocument = PdfDocument()
    var pagesData by mutableStateOf(
        listOf<PageData>()
    )
    if (consultancy.consultancyResponses.storingData.evaluateCurrentMechanisms != null && consultancy.consultancyResponses.handlingData.evaluateCurrentMechanisms != null && consultancy.consultancyResponses.riskAssessment.evaluateCurrentMechanisms != null) {
        pagesData = listOf(
            PageData(
                "Legal Regulations",
                listOf(
                    consultancy.consultancyResponses.handlingData.legalRegulations,
                    consultancy.consultancyResponses.storingData.legalRegulations,
                    consultancy.consultancyResponses.riskAssessment.legalRegulations,
                )
            ),
            PageData(
                "Industry Best Practices",
                listOf(
                    consultancy.consultancyResponses.handlingData.industryBestPractices,
                    consultancy.consultancyResponses.storingData.industryBestPractices,
                    consultancy.consultancyResponses.riskAssessment.industryBestPractices,
                )
            ),
            PageData(
                "Evaluation of mechanism",
                listOf(
                    consultancy.consultancyResponses.handlingData.evaluateCurrentMechanisms,
                    consultancy.consultancyResponses.storingData.evaluateCurrentMechanisms,
                    consultancy.consultancyResponses.riskAssessment.evaluateCurrentMechanisms,
                )
            ),
            PageData(
                "Page out of precedent",
                listOf(
                    consultancy.previousRulings,
                    consultancy.lawViolations,
                )
            ),
            PageData(
                "Suggestions for Improvement",
                listOf(
                    consultancy.consultancyResponses.handlingData.suggestions,
                    consultancy.consultancyResponses.storingData.suggestions,
                    consultancy.consultancyResponses.riskAssessment.suggestions,
                )
            )
        )
    } else {
        pagesData = listOf(
            PageData(
                "Legal Regulations",
                listOf(
                    consultancy.consultancyResponses.handlingData.legalRegulations,
                    consultancy.consultancyResponses.storingData.legalRegulations,
                    consultancy.consultancyResponses.riskAssessment.legalRegulations
                )
            ),
            PageData(
                "Industry Best Practices",
                listOf(
                    consultancy.consultancyResponses.handlingData.industryBestPractices,
                    consultancy.consultancyResponses.storingData.industryBestPractices,
                    consultancy.consultancyResponses.riskAssessment.industryBestPractices
                )
            ),
            PageData(
                "Page out of precedent",
                listOf(
                    consultancy.previousRulings,
                    consultancy.lawViolations,
                )
            ),
            PageData(
                "Suggestions for Improvement",
                listOf(
                    consultancy.consultancyResponses.handlingData.suggestions,
                    consultancy.consultancyResponses.storingData.suggestions,
                    consultancy.consultancyResponses.riskAssessment.suggestions
                )
            )
        )
    }

    val paint: Paint = Paint()
    paint.color = 0xFF8EA745.toInt()
    paint.textAlign = Paint.Align.CENTER

    val title: Paint = Paint()
    title.textSize = 35F
    title.isFakeBoldText = true
    title.textAlign = Paint.Align.CENTER

    val subtitle: Paint = Paint()
    subtitle.textSize = 14f
    subtitle.isFakeBoldText = true
    subtitle.textAlign = Paint.Align.CENTER

//    Page 1
    val bmp: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.pdflogo)
    val scaledbmp: Bitmap = Bitmap.createScaledBitmap(bmp, 200, 200, false)
    val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
    val page = pdfDocument.startPage(pageInfo)
    val canvas = page.canvas

    val centerX = (pageInfo.pageWidth / 2).toFloat()
    val availableHeight = (pageInfo.pageHeight - 40F)  // Adjust as needed

// Calculate the vertical space between the title and subtitle
    val spaceBetween = (availableHeight - 320F) / 2

    canvas.drawBitmap(
        scaledbmp,
        (centerX - scaledbmp.width / 2),
        (170f - scaledbmp.height / 2),
        paint
    )
    canvas.drawText("PacMan - A guide in need.", centerX, 320F, title)
    canvas.drawText(
        "Encryption are some of the steps that are the hymns of this entire process",
        centerX,
        350F,
        subtitle
    )
    title.color = 0xFF8EA745.toInt()
    title.textSize = 40F
    canvas.drawText("PacMan’s Report", centerX, 320f + spaceBetween, title)
    addPageNumber(canvas, pdfDocument.pages.size + 1)  // Add page number to the new page
    pdfDocument.finishPage(page)

//  Rest Pages

    title.textSize = 35F
    title.color = 0xFF000000.toInt()
    title.textAlign = Paint.Align.LEFT
    subtitle.textAlign = Paint.Align.LEFT

    // Function to create a new page
    fun createPage(heading: String, subheadings: List<String>) {
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, pdfDocument.pages.size + 1).create()
        var page = pdfDocument.startPage(pageInfo)
        var canvas = page.canvas

        // Add heading
        canvas.drawText(heading, 50f, 100f, title)

        // Add bulleted subheadings
        var yPos = 140f
        for (subheading in subheadings) {
            // Check if there's enough space for the next subheading
            if (yPos + 20f > pageInfo.pageHeight) {
                addPageNumber(canvas, pdfDocument.pages.size + 1)  // Add page number to the new page
                pdfDocument.finishPage(page)  // Finish the current page
                val newPageInfo = PdfDocument.PageInfo.Builder(595, 842, pdfDocument.pages.size + 1).create()
                page = pdfDocument.startPage(newPageInfo)  // Start a new page
                canvas = page.canvas
                yPos = 140f  // Reset the Y position for the new page
                addPageNumber(canvas, pdfDocument.pages.size + 1)  // Add page number to the new page

            }

            canvas.drawText("", 0f, yPos, subtitle)
            yPos += 20f

            val subHeadingLines = splitTextIntoLines(subheading, subtitle, 495f)
            for (line in subHeadingLines) {
                // Check if there's enough space for the next line
                if (yPos + 150f > pageInfo.pageHeight) {
                    addPageNumber(canvas, pdfDocument.pages.size + 1)  // Add page number to the new page
                    pdfDocument.finishPage(page)  // Finish the current page
                    val newPageInfo = PdfDocument.PageInfo.Builder(595, 842, pdfDocument.pages.size + 1).create()
                    page = pdfDocument.startPage(newPageInfo)  // Start a new page
                    canvas = page.canvas
                    yPos = 140f  // Reset the Y position for the new page
                    addPageNumber(canvas, pdfDocument.pages.size + 1)  // Add page number to the new page

                }
                canvas.drawText(line, 50f, yPos, subtitle)
                yPos += 20f
            }
        }
        addPageNumber(canvas, pdfDocument.pages.size + 1)  // Add page number to the new page
        pdfDocument.finishPage(page)
    }

    // Loop through the provided data to create pages
    for (pageData in pagesData) {
        createPage(pageData.heading, pageData.subheadings)
    }

    // Save the PDF to the file
    try {
        val fos = FileOutputStream(file)
        pdfDocument.writeTo(fos)
        fos.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }

    pdfDocument.close()

    // Notify the MediaScanner to detect the new file
    val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
    mediaScanIntent.data = Uri.fromFile(file)
    context.sendBroadcast(mediaScanIntent)

    // Show a toast message
    Toast.makeText(
        context, "PDF downloaded to Downloads directory", Toast.LENGTH_SHORT
    ).show()

    // Create a notification to inform the user that the PDF has been downloaded
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // Notification channel is required for Android Oreo and later
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "pdf_download_channel"
        val channelName = "PDF Download"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, channelName, importance)
        notificationManager.createNotificationChannel(channel)
    }

    // Create an intent to open the PDF file or an app to view it
    val pdfFileUri = getDownloadedPDFUri(context, fileName)/* The URI of the downloaded PDF file */
    val openIntent = Intent(Intent.ACTION_VIEW)
    openIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
    openIntent.data = pdfFileUri
    val pendingIntent = PendingIntent.getActivity(context, 0, openIntent, PendingIntent.FLAG_UPDATE_CURRENT)

    // Build the notification
    val notification = Notification.Builder(context, "pdf_download_channel")
        .setSmallIcon(R.drawable.pdflogo)
        .setContentTitle("PDF Downloaded")
        .setContentText("Your PDF has been downloaded.")
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .build()

    // Show the notification
    notificationManager.notify(0, notification)

}

data class PageData(var heading: String, val subheadings: List<String>)

fun splitTextIntoLines(text: String, paint: Paint, maxWidth: Float): List<String> {
    val lines = mutableListOf<String>()
    val words = text.split(" ")

    var currentLine = StringBuilder()
    for (word in words) {
        if (paint.measureText("$currentLine $word") <= maxWidth) {
            if (currentLine.isNotEmpty()) {
                currentLine.append(" ")
            }
            currentLine.append(word)
        } else {
            lines.add(currentLine.toString())
            currentLine = StringBuilder(word)
        }
    }

    if (currentLine.isNotEmpty()) {
        lines.add(currentLine.toString())
    }

    return lines
}
fun addPageNumber(canvas: Canvas, pageNumber: Int) {
    val paint = Paint()
    paint.textSize = 14f
    paint.isFakeBoldText = true
    paint.textAlign = Paint.Align.CENTER

    val pageNumberText = "Page $pageNumber"
    val pageWidth = ((canvas.width)/2).toFloat()
    val pageHeight = canvas.height.toFloat()

    canvas.drawText(pageNumberText, pageWidth, pageHeight - 50f, paint)
}
fun getDownloadedPDFUri(context: Context, pdfFileName: String): Uri? {
    val downloadsDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    val downloadsPath = downloadsDirectory.absolutePath

    // Query the MediaStore to find the PDF file in the "Downloads" directory
    val uri = MediaStore.Files.getContentUri("external")
    val projection = arrayOf(
        MediaStore.Files.FileColumns._ID,
        MediaStore.Files.FileColumns.DATA
    )

    val selection = "${MediaStore.Files.FileColumns.DATA} like ?"
    val selectionArgs = arrayOf("%$downloadsPath%/$pdfFileName")

    context.contentResolver.query(uri, projection, selection, selectionArgs, null)?.use { cursor ->
        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(MediaStore.Files.FileColumns._ID)
            val fileId = cursor.getLong(columnIndex)
            return Uri.withAppendedPath(uri, fileId.toString())
        }
    }

    return null
}
