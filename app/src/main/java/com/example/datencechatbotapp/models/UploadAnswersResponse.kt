package com.example.datencechatbotapp.models

data class UploadAnswersResponse(
    var baseCountry: String?,
    var industry: String?,
    var dataTypes: List<String>,
    var dataSubjects: List<String>,
    var collectingFromCountries: List<String>,
    var purposes: List<String>,
    var storageCountry: List<String>,
    var isChildrenDataCollected: Boolean = true,
    var haveHandlingMechanism: Boolean = false,
    var handlingMechanisms: List<String>,
    var haveStorageMechanism: Boolean = false,
    var storageMechanisms: List<String>,
    var haveRiskAssessmentMechanism: Boolean = false,
    var riskAssessmentMechanisms: List<String>,
)
