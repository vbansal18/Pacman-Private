package com.example.datencechatbotapp.models

import androidx.compose.runtime.mutableStateOf

data class TagItem(
    val name:String,
    var isChecked : Boolean,
    var id : String? = null,
)
