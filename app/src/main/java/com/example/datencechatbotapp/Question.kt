package com.example.datencechatbotapp

data class Question(
    val questionNumber : Int,
    val question : String,
    var tags : MutableMap<String, Boolean>,
)
