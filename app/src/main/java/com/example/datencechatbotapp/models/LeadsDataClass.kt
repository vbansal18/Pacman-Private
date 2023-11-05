package com.example.datencechatbotapp.models

data class LeadsDataClass(
    val lawFirmNames : List<String>
)

data class Firm(
    val name : String,
    val description : List<String>,
    val email : String,
    val website : String,
    val imageLogo : Int,
)