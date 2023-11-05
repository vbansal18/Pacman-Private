package com.example.datencechatbotapp.models

data class GetConsultancyResponse(
    val lawViolations : String,
    val previousRulings : String,
    val consultancyResponses : consultancyResponses,

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




//POST REQUEST
//
//{
//    "baseCountry": "Country",
//    "industry": "Industry",
//    "dataTypes": [
//    "Data Type 1",
//    "Data Type 2",
//    "Data Type 3",
//    "Data Type 4"
//    ],
//    "dataSubjects": [
//    "Data Subject 1",
//    "Data Subject 2",
//    "Data Subject 3",
//    "Data Subject 4"
//    ],
//    "collectingFromCountries": [
//    "Country 1",
//    "Country 2",
//    "Country 3",
//    "Country 4"
//    ],
//    "purposes": [
//    "Purpose 1",
//    "Purpose 2",
//    "Purpose 3",
//    "Purpose 4"
//    ],
//    "storageCountry": [
//    "Storage Country 1",
//    "Storage Country 2"
//    ],
//    "isChildrenDataCollected": true,
//    "haveHandlingMechanism": true,
//    "handlingMechanisms": [
//    "mechanism1",
//    "mechanism2",
//    "mechanism3",
//    "mechanism4"
//    ],
//    "haveStorageMechanism": true,
//    "storageMechanisms": [
//    "store1",
//    "store2",
//    "store3",
//    "store4"
//    ],
//    "haveRiskAssessmentMechanism": false,
//    "riskAssessmentMechanisms": []
//}
//