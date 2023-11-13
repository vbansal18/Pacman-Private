package com.example.datencechatbotapp.models


data class GetAllCasesModel(
    val allCases : List<Session>
)
data class Session(
    val consultancy: ConsultancyResponse_,
    val lawFirmNames: List<String>,
    val timestamp: String
)
