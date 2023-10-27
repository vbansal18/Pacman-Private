package com.example.datencechatbotapp.models

data class QuestionItem(
    val questionNumber: Int,
    val question: String,
    var tags: List<TagItem>?,
)
