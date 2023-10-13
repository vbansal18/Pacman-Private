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
import com.example.datencechatbotapp.screens.questionscreen.QuestionScreen
import com.example.datencechatbotapp.screens.questionscreen.country.QuestionCountry
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
        mutableStateOf(true)
    }
    DatenceChatbotAppTheme(theme.value){
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "editProfile") {
            composable(route = "signup") {
                Signup()
            }
            composable(route = "editProfile") {
                EditProfileScreen()
            }
            composable(route = "feedback") {
                Feedback()
            }
            composable(route = "settings") {
                SettingsScreen(theme)
            }
            composable(route = "login") {
                Login()
            }
            composable(route = "gettingStarted") {
                GetStartedScreen()
            }
            composable(route = "question") {
                QuestionScreen(AllQuestionItems[0])
            }
            composable(route = "dashboard") {
                Dashboard()
            }
            composable(route = "upload") {
                UploadFiles()
            }
            composable(route = "country") {
                QuestionCountry()
            }
        }
    }
}