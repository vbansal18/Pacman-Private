package com.example.datencechatbotapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.datencechatbotapp.screens.Dashboard
import com.example.datencechatbotapp.screens.GetStartedScreen
import com.example.datencechatbotapp.screens.Login
import com.example.datencechatbotapp.screens.Signup
import com.example.datencechatbotapp.screens.UploadFiles
import com.example.datencechatbotapp.screens.questionscreen.QuestionScreen
import com.example.datencechatbotapp.screens.questionscreen.country.QuestionCountry

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "country") {
        composable(route = "signup") {
            Signup()
        }
        composable(route = "login") {
            Login()
        }
        composable(route = "gettingStarted") {
            GetStartedScreen()
        }
        composable(route = "signup") {
            Signup()
        }
        composable(route = "question") {
            QuestionScreen(AllQuestions[0])
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


