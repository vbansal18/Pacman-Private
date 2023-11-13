package com.example.datencechatbotapp.models

data class GetConsultancyResponse(
    val message : String,
    val userId : String,
    val seniorAssociateSays : ConsultancyResponse_,
)
data class ConsultancyResponse_(
    val consultancyResponses : consultancyResponses,
    val lawViolations : String,
    val previousRulings : String,
)

data class consultancyResponses(
    val handlingData : keyValueStringPair,
    val storingData : keyValueStringPair,
    val riskAssessment : keyValueStringPair,
)

data class keyValueStringPair(
    val legalRegulations : String,
    val industryBestPractices : String,
    val evaluateCurrentMechanisms : String? = null,
    val suggestions : String,
)


