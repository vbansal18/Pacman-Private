package com.example.datencechatbotapp.models

data class FeedbackModel(
    var rating : Int,
    var servicesLiked : List<String>,
    var message : String
)
