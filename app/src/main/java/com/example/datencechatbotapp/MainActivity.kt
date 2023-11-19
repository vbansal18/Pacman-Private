package com.example.datencechatbotapp

import GoogleSignScreen
import SettingsScreen
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.datencechatbotapp.api.FileUploadViewModel
import com.example.datencechatbotapp.data.preferences.PreferencesDatastore
import com.example.datencechatbotapp.models.UploadAnswersResponse
import com.example.datencechatbotapp.screens.ConsultancyScreen
import com.example.datencechatbotapp.screens.Dashboard
import com.example.datencechatbotapp.screens.DemoVideoPlayer
import com.example.datencechatbotapp.screens.EditProfileScreen
import com.example.datencechatbotapp.screens.Feedback
import com.example.datencechatbotapp.screens.GetStartedScreen
import com.example.datencechatbotapp.screens.Login
import com.example.datencechatbotapp.screens.Signup
import com.example.datencechatbotapp.screens.UploadFiles
import com.example.datencechatbotapp.screens.leadgeneration.AllLeadsScreen
import com.example.datencechatbotapp.screens.leadgeneration.AllLeadsScreenFromResponse
import com.example.datencechatbotapp.screens.leadgeneration.LeadGen
import com.example.datencechatbotapp.screens.leadgeneration.LeadGenFromResponse
import com.example.datencechatbotapp.screens.questionscreen.QuestionScreen
import com.example.datencechatbotapp.ui.theme.DatenceChatbotAppTheme
import com.example.datencechatbotapp.ui.theme.Green
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var appOpeningtimer by remember {
                mutableStateOf<Boolean>(false)
            }
            val datastore by remember {
                mutableStateOf(PreferencesDatastore(this))
            }
            val viewModel = viewModel<FileUploadViewModel>()
            if (!appOpeningtimer) {
                FirstAnimation()
                LaunchedEffect(appOpeningtimer) {
                    delay(2000L)
                    appOpeningtimer = true
                }
            } else {
                App(datastore, viewModel)
            }
        }
    }
}

@Composable
fun FirstAnimation() {
    Column(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        Color.White,
                        Green
                    )
                )
            )
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.pacman_logo),
            contentDescription = "",
            Modifier.size(150.dp)
        )
        Text(
            text = "PACMAN",
            fontWeight = FontWeight(900),
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF0E5E0F),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
        )
        Text(
            text = "A Datence Product",
            fontWeight = FontWeight(600),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF064B07),
            modifier = Modifier.fillMaxWidth()
        )

    }
}


var uploadAnswersResponse by mutableStateOf(
    UploadAnswersResponse(
        baseCountry = null,
        industry = null,
        dataTypes = listOf(),
        dataSubjects = listOf(),
        collectingFromCountries = listOf(),
        purposes = listOf(),
        storageCountry = listOf(),
        isChildrenDataCollected = true,
        haveHandlingMechanism = false,
        handlingMechanisms = listOf(),
        haveStorageMechanism = false,
        storageMechanisms = listOf(),
        haveRiskAssessmentMechanism = false,
        riskAssessmentMechanisms = listOf(),
    )
)

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun App(
    datastore: PreferencesDatastore,
    viewModel: FileUploadViewModel,
) {
    var theme = remember {
        mutableStateOf(false)
    }
    var islogin by remember {
        mutableStateOf<Boolean?>(null)
    }
    LaunchedEffect(islogin) {
        if (islogin==null){
            datastore.getIsLogin().collect {
                islogin = it.value
            }
        }
    }
    var startDestination by remember {
        mutableStateOf("")
    }
    if (islogin == true) {
        startDestination = "dashboard"
    } else {
        startDestination = "googleSignIn"
    }
    println("islogin in app(): $islogin")
    if (islogin != null) {
        DatenceChatbotAppTheme(theme.value) {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = startDestination) {

                composable(route = "signup") {
                    Signup(navController)
                }
                composable(route = "login") {
                    Login(navController)
                }
                composable(route = "googleSignIn") {
                    GoogleSignScreen(navController, datastore)
                }
                composable(route = "demo_video") {
                    BackHandler(true) {
                        // Or do nothing
                        while (navController.popBackStack()) {
                            // Keep popping until the back stack is empty
                        }
                        navController.navigate("dashboard")
                        Log.i("LOG_TAG", "Clicked back")
                    }

                    DemoVideoPlayer(navController)
                }


                composable(route = "dashboard") {
                    BackHandler(true) {
                        // Or do nothing
                        while (navController.popBackStack()) {
                            // Keep popping until the back stack is empty
                        }
                        Log.i("LOG_TAG", "Clicked back")
                    }

                    Dashboard(navController, datastore, viewModel)
                }

                composable(route = "gettingStarted/{source_screen}", arguments = listOf(
                    navArgument("source_screen") {
                        type = NavType.StringType
                    }
                )) {
                    val source_screen = it.arguments?.getString("source_screen")
                    GetStartedScreen(navController, source_screen, viewModel)
                }

                composable(route = "upload") {
                    BackHandler(true) {
                        // Or do nothing
                        navController.popBackStack()
                        navController.navigate("dashboard")
                        Log.i("LOG_TAG", "Clicked back")
                    }

                    UploadFiles(viewModel, navController)
                }

                composable(route = "question") {
                    BackHandler(true) {
                        // Or do nothing
                        navController.popBackStack()
                        navController.navigate("dashboard")
                        Log.i("LOG_TAG", "Clicked back")
                    }

                    QuestionScreen(navController)
                }
                composable(route = "consultancy") {
                    ConsultancyScreen(navController)
                }

                composable(route = "allLeadsScreen/{session_number}", arguments = listOf(
                    navArgument("session_number") {
                        type = NavType.IntType
                    }
                )) {
                    val sessionNumber = it.arguments?.getInt("session_number")
                    AllLeadsScreen(navController, sessionNumber, viewModel)
                }

                composable(route = "allLeadsFromResponseScreen") {
                    AllLeadsScreenFromResponse(navController, viewModel)
                }

                composable(
                    route = "leadGenFromResponseScreen/{current_lead_index}", arguments = listOf(
                        navArgument("current_lead_index") {
                            type = NavType.IntType
                        },
                    )
                ) {
                    val currentLeadIndex = it.arguments?.getInt("current_lead_index")

                    LeadGenFromResponse(currentLeadIndex, navController)
                }

                composable(
                    route = "lead/{current_lead_index}/{all_leads}/{session_number}",
                    arguments = listOf(
                        navArgument("current_lead_index") {
                            type = NavType.IntType
                        },
                        navArgument("session_number") {
                            type = NavType.IntType
                        },
                        navArgument("all_leads") {
                            type = NavType.StringType
                        },
                    )
                ) {
                    val currentLeadIndex = it.arguments?.getInt("current_lead_index")
                    val session_number = it.arguments?.getInt("session_number")
                    val allLeads = it.arguments?.getString("all_leads")
                    LeadGen(currentLeadIndex, allLeads, navController, session_number)
                }

                composable(route = "settings") {
                    SettingsScreen(theme, navController, datastore)
                }

                composable(route = "feedback") {
                    Feedback(navController, viewModel)
                }

                composable(route = "editProfile") {
                    EditProfileScreen(navController, datastore)
                }
            }
        }
    }
}