package com.example.datencechatbotapp

import SettingsScreen
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.datencechatbotapp.screens.Dashboard
import com.example.datencechatbotapp.screens.EditProfileScreen
import com.example.datencechatbotapp.screens.Feedback
import com.example.datencechatbotapp.screens.GetStartedScreen
import com.example.datencechatbotapp.screens.Login
import com.example.datencechatbotapp.screens.Signup
import com.example.datencechatbotapp.screens.UploadFiles
import com.example.datencechatbotapp.screens.leadgeneration.LeadGen
import com.example.datencechatbotapp.screens.questionscreen.QuestionScreen
import com.example.datencechatbotapp.ui.theme.DatenceChatbotAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                App()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun App() {
    val theme = remember {
        mutableStateOf(false)
    }
    DatenceChatbotAppTheme(theme.value){
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "question") {
            composable(route = "signup") {
                Signup(navController)
            }
            composable(route = "login") {
                Login(navController)
            }
            composable(route = "dashboard") {
                Dashboard(navController)
            }
            composable(route = "gettingStarted") {
                GetStartedScreen(navController)
            }
            composable(route = "upload") {
                UploadFiles()
            }
            composable(route = "question") {
                QuestionScreen(navController)
            }
            composable(route = "leads") {
                LeadGen()
            }
            composable(route = "settings") {
                SettingsScreen(theme, navController)
            }
            composable(route = "feedback") {
                Feedback(navController)
            }
            composable(route = "editProfile") {
                EditProfileScreen(navController)
            }

        }
    }
}