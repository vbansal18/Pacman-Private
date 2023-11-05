package com.example.datencechatbotapp.models


data class GetAllCasesModel(
    val allCases : List<Session>
)
data class Session(
    val consultancy: GetConsultancyResponse,
    val lawFirmNames: List<String>,
    val timestamp: String
)
