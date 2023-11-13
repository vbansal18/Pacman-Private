package com.example.datencechatbotapp

import SettingsScreen
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.example.datencechatbotapp.screens.leadgeneration.LeadGen
import com.example.datencechatbotapp.screens.questionscreen.QuestionScreen
import com.example.datencechatbotapp.ui.theme.DatenceChatbotAppTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val datastore = PreferencesDatastore(this)
            val viewModel = viewModel<FileUploadViewModel>()
            App(datastore, viewModel)
        }
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
fun App(datastore: PreferencesDatastore, viewModel: FileUploadViewModel) {
    val theme = remember {
        mutableStateOf(false)
    }
    DatenceChatbotAppTheme(theme.value){
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "upload") {

            composable(route = "signup") {
                Signup(navController)
            }
            composable(route = "demo_video") {
                DemoVideoPlayer(navController)
            }

            composable(route = "login") {
                Login(navController)
            }

            composable(route = "dashboard") {
                Dashboard(navController,datastore, viewModel)
            }

            composable(route = "gettingStarted/{source_screen}", arguments = listOf(
                navArgument("source_screen"){
                    type = NavType.StringType
                }
            )) {
                val source_screen = it.arguments?.getString("source_screen")
                GetStartedScreen(navController, source_screen, viewModel)
            }


            composable(route = "upload") {
                UploadFiles(viewModel, navController)
            }

            composable(route = "question") {
                QuestionScreen(navController)
            }
            composable(route = "consultancy/{consultancy_data}", arguments = listOf(
                navArgument("consultancy_data"){
                    type = NavType.StringType
                }
            )) {
                val consultancy_data = it.arguments?.getString("consultancy_data")
                ConsultancyScreen(navController, consultancy_data)
            }

            composable(route = "allLeadsScreen/{session_number}", arguments = listOf(
                navArgument("session_number"){
                    type = NavType.IntType
                }
            )) {
                val sessionNumber = it.arguments?.getInt("session_number")
                AllLeadsScreen(navController, sessionNumber, viewModel)
            }

            composable(route = "lead/{current_lead_index}/{all_leads}", arguments = listOf(
                navArgument("current_lead_index"){
                    type = NavType.IntType
                },
                navArgument("all_leads"){
                    type = NavType.StringType
                },
            )) {
                val currentLeadIndex = it.arguments?.getInt("current_lead_index")
                val allLeads = it.arguments?.getString("all_leads")
                LeadGen(currentLeadIndex, allLeads, navController)
            }

            composable(route = "settings") {
                SettingsScreen(theme, navController)
            }

            composable(route = "feedback") {
                Feedback(navController)
            }

            composable(route = "editProfile") {
                EditProfileScreen(navController, datastore)
            }

        }
    }
}