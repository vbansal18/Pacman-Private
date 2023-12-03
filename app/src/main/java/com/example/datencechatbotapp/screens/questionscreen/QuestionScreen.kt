package com.example.datencechatbotapp.screens.questionscreen

import AllCountries
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.datencechatbotapp.AllQuestionItems
import com.example.datencechatbotapp.R
import com.example.datencechatbotapp.models.UploadAnswersResponse
import com.example.datencechatbotapp.uploadAnswersResponse
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val allQuestions by mutableStateOf(AllQuestionItems)

@Composable
fun QuestionScreen(navController: NavHostController) {
    RenderQuestions(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RenderQuestions(
    navController: NavHostController
) {
    val i = remember {
        mutableStateOf(0)
    }
    var questionItem = allQuestions[i.value]
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.onTertiary, RoundedCornerShape(40.dp),
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.16f)

        ) {
            Row(
                modifier = Modifier
                    .shadow(
                        8.dp,
                        RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp),
                    )
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .background(
                        Color(217, 251, 114, 255)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "KeyboardArrowLeft",
                    modifier = Modifier
                        .padding(12.dp)
                        .border(BorderStroke(1.5.dp, Color.Black), CircleShape)
                        .clickable(onClick = {
                            if (i.value == 0) {
                                navController.popBackStack()
                            } else {
                                updateUploadAnswersResponse(i.value)
                                questionItem = allQuestions[i.value--]
                            }
                        })
                )
                if (i.value != allQuestions.size - 1) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "KeyboardArrowRight",
                        modifier = Modifier
                            .padding(12.dp)
                            .border(BorderStroke(1.5.dp, Color.Black), CircleShape)
                            .clickable(onClick = {
                                updateUploadAnswersResponse(i.value)
                                questionItem = allQuestions[i.value++]
                            })
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = "${questionItem.questionNumber}/${allQuestions.size}",
                    modifier = Modifier
                        .shadow(
                            6.dp,
                            CircleShape,
                        )
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.tertiary)
                        .padding(20.dp),
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.20f)
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = questionItem.question,
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.surface
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.7f)
            ) {
                if (questionItem.tags == null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(1f)
                            .background(
                                MaterialTheme.colorScheme.onBackground,
                                RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                            ), contentAlignment = Alignment.BottomCenter

                    ) {
                        var countryOrCountriesName by remember {
                            mutableStateOf("")
                        }
                        if (questionItem.questionNumber == 1) {
                            countryOrCountriesName = "Country Selected : "
                        } else {
                            countryOrCountriesName = "Countries Selected : "
                        }

                        CountryPicker(
                            questionNumber = questionItem.questionNumber,
                            isOneCountrySelected = {
                                questionItem = allQuestions[i.value++]
                            },
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "${countryOrCountriesName}${questionItem.countriesList}",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 60.dp)
                                .fillMaxWidth(),
                            color = MaterialTheme.colorScheme.surface,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                } else if (questionItem.questionNumber == 8) {
                    var isYesActive by remember {
                        mutableStateOf(allQuestions[i.value].tags!![0].isChecked)
                    }
                    var isNoActive by remember {
                        mutableStateOf(allQuestions[i.value].tags!![1].isChecked)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.7f)
                            .background(MaterialTheme.colorScheme.onTertiary),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(40.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            FilterChip(modifier = Modifier
                                .padding(
                                    horizontal = 12.dp, vertical = 2.dp
                                )
                                .weight(.4f),

                                selected = isYesActive, onClick = {
                                    if (!isYesActive) {
                                        isYesActive = !isYesActive
                                        isNoActive = !isNoActive
                                        allQuestions[i.value].tags!![0].isChecked = isYesActive
                                        allQuestions[i.value].tags!![1].isChecked = isNoActive
                                    } else {

                                    }

                                }, label = {
                                    Text(
                                        text = "Yes",
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }, colors = FilterChipDefaults.filterChipColors(
                                    disabledContainerColor = MaterialTheme.colorScheme.onBackground,
                                    selectedContainerColor = Color(0xFFD4F56F),
                                    labelColor = MaterialTheme.colorScheme.surface,
                                    selectedLabelColor = Color.Black,

                                    )
                            )

                            FilterChip(modifier = Modifier
                                .padding(
                                    horizontal = 12.dp, vertical = 2.dp
                                )
                                .weight(.4f),

                                selected = isNoActive, onClick = {
                                    if (!isNoActive) {
                                        isYesActive = !isYesActive
                                        isNoActive = !isNoActive
                                        allQuestions[i.value].tags!![0].isChecked = isYesActive
                                        allQuestions[i.value].tags!![1].isChecked = isNoActive
                                    } else {

                                    }

                                }, label = {
                                    Text(
                                        text = "No",
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }, colors = FilterChipDefaults.filterChipColors(
                                    disabledContainerColor = MaterialTheme.colorScheme.onBackground,
                                    selectedContainerColor = Color(0xFFD4F56F),
                                    labelColor = MaterialTheme.colorScheme.surface,
                                    selectedLabelColor = Color.Black,

                                    )
                            )

                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(1f)
                            .background(MaterialTheme.colorScheme.onTertiary),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pacman_watermark),
                            contentDescription = "pacman_watermark",
                            Modifier
                                .fillMaxWidth(0.6f)
                                .aspectRatio(1f),
                            colorFilter = ColorFilter.tint(Color(240, 251, 205, 255))
                        )
                        BottomSheet(questionNumber = questionItem.questionNumber)
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
                    .background(MaterialTheme.colorScheme.onBackground),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {},
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(
                                0xFFD1F26E
                            )
                        ),
                        modifier = Modifier
                            .width(60.dp)
                            .aspectRatio(1f),
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "DoneBtn",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(40.dp)
                                .clickable(onClick = {
                                    if (i.value == allQuestions.size - 1) {
                                        CoroutineScope(Dispatchers.IO).launch {
                                            val gson = Gson()
                                            var responses by mutableStateOf<String?>(null)
                                            val parsedResponse = async {
                                                gson.toJson(
                                                    uploadAnswersResponse,
                                                    UploadAnswersResponse::class.java
                                                )
                                            }
                                            responses = parsedResponse.await()
                                            if (responses != null) {
                                                withContext(Dispatchers.Main) {
                                                    navController.navigate("gettingStarted/${"question"}")
                                                }
                                            }
                                        }
                                    } else {
                                        updateUploadAnswersResponse(i.value)
                                        questionItem = allQuestions[i.value++]
                                    }
                                })
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun CountryPicker(
    isOneCountrySelected: (Boolean) -> Unit,
    questionNumber: Int,
) {
    val parentOptions = AllCountries
    Row(
        Modifier
            .fillMaxSize()
            .padding(35.dp, 50.dp, 35.dp, 20.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center

    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .shadow(4.dp, RoundedCornerShape(30.dp))
                .background(Color(0xFFDAF683), RoundedCornerShape(30.dp))
                .padding(vertical = 50.dp)
                .background(Color(0x8CE4E5E7)),
        ) {
            items(parentOptions) { country ->
                CountryRow(
                    country.country,
                    country.flag_url,
                    questionNumber,
                    isOneCountrySelected,
                )
            }
        }

    }
}

@Composable
private fun CountryRow(
    name: String,
    flag_url: String,
    questionNumber: Int,
    isOneCountrySelected: (Boolean) -> Unit,
) {
    val color = remember {
        mutableStateOf(Color(0x8CE4E5E7))
    }
    var isActive by remember {
        mutableStateOf(false)
    }
    if (allQuestions[questionNumber - 1].countriesList?.contains(name) == true) {
        isActive = true
    }
    if (isActive) {
        color.value = Color(239, 255, 188, 255)
    }
    if (!isActive) {
        color.value = Color(0x8CE4E5E7)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(color.value)
            .clickable {
                isActive = !isActive
                if (questionNumber == 1) {
                    allQuestions[0].countriesList = listOf(name)
                    uploadAnswersResponse.baseCountry = allQuestions[0].countriesList!![0]
                    isOneCountrySelected(true)
                } else {
                    if (isActive) {
                        if (allQuestions[questionNumber - 1].countriesList == null) {
                            allQuestions[questionNumber - 1].countriesList = listOf(name)
                        } else {
                            allQuestions[questionNumber - 1].countriesList =
                                allQuestions[questionNumber - 1].countriesList!! + (name)
                        }
                    } else {
                        if (allQuestions[questionNumber - 1].countriesList!!.size == 1) {
                            allQuestions[questionNumber - 1].countriesList = null
                        } else {
                            allQuestions[questionNumber - 1].countriesList =
                                allQuestions[questionNumber - 1].countriesList!! - (name)
                        }

                    }
                }
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight(300),
            modifier = Modifier
                .padding(start = 30.dp)
                .weight(.8f)
        )
        Image(
            painter = rememberAsyncImagePainter(flag_url),
            contentDescription = "gfg image",
            modifier = Modifier
                .weight(.2f)
                .padding(end = 30.dp)
                .aspectRatio(1f)
                .background(Color.White, RoundedCornerShape(50.dp))
                .padding(1.dp)
        )
    }
    Divider(modifier = Modifier.fillMaxWidth(), thickness = Dp.Hairline)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(questionNumber: Int) {
    BottomSheetScaffold(sheetDragHandle = { DragHandle() },
        sheetPeekHeight = 110.dp,
        sheetContent = { BottomSheetContent(questionNumber) },
        containerColor = MaterialTheme.colorScheme.onBackground,
        sheetContainerColor = MaterialTheme.colorScheme.onBackground,
        sheetContentColor = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.fillMaxWidth(),
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
    ) {}
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(questionNumber: Int) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        FlowRow(
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.SpaceEvenly,
            maxItemsInEachRow = 2
        ) {
            var isActive by remember { mutableStateOf(false) }

            for (tag in allQuestions[questionNumber - 1].tags!!) {
                isActive = tag.isChecked

                FilterChip(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 2.dp)
                        .weight(1f),
                    selected = isActive,
                    onClick = {
                        tag.isChecked = !tag.isChecked
                        isActive = !isActive
                    },
                    label = {
                        Text(
                            text = tag.name,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        disabledContainerColor = MaterialTheme.colorScheme.onBackground,
                        selectedContainerColor = Color(0xFFD4F56F),
                        labelColor = MaterialTheme.colorScheme.surface,
                        selectedLabelColor = Color.Black,
                    )
                )
            }
        }
    }
}

@Composable
fun DragHandle(): Unit {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.weight(.3f)
        ) {

        }
        Divider(
            thickness = 5.dp, color = Color(0xFFD9FB72), modifier = Modifier.weight(.3f)
        )
        Row(
            modifier = Modifier.weight(.3f)
        ) {

        }
    }
}

private fun updateUploadAnswersResponse(i: Int) {
    when (i) {
        0 -> {
            if (allQuestions[0].countriesList != null) uploadAnswersResponse.baseCountry =
                allQuestions[0].countriesList!![0]
        }

        1 -> {
            if (allQuestions[1].tags != null) for (tag in allQuestions[1].tags!!) {
                if (tag.isChecked) {
                    uploadAnswersResponse.industry = tag.name
                }
            }
        }

        2 -> {
            if (allQuestions[2].tags != null) {
                uploadAnswersResponse.dataTypes = listOf()
                for (tag in allQuestions[2].tags!!) {
                    if (tag.isChecked) {
                        uploadAnswersResponse.dataTypes += tag.name
                    }
                }
            }
        }

        3 -> {
            if (allQuestions[3].tags != null) {
                uploadAnswersResponse.dataSubjects = listOf()
                for (tag in allQuestions[3].tags!!) {
                    if (tag.isChecked) {
                        uploadAnswersResponse.dataSubjects += tag.name
                    }
                }
            }
        }

        4 -> {
            if (allQuestions[4].countriesList != null) {
                uploadAnswersResponse.collectingFromCountries = listOf()
                for (country in allQuestions[4].countriesList!!) {
                    uploadAnswersResponse.collectingFromCountries += country
                }
            }
        }

        5 -> {
            if (allQuestions[5].tags != null) {
                uploadAnswersResponse.purposes = listOf()
                for (tag in allQuestions[5].tags!!) {
                    if (tag.isChecked) {
                        uploadAnswersResponse.purposes += tag.name
                    }
                }
            }
        }

        6 -> {
            if (allQuestions[6].countriesList != null) {
                uploadAnswersResponse.storageCountry = listOf()
                for (country in allQuestions[6].countriesList!!) {
                    uploadAnswersResponse.storageCountry += country
                }
            }
        }

        7 -> {
            if (allQuestions[7].tags != null) for (tag in allQuestions[7].tags!!) {
                if (tag.isChecked && tag.name == "Yes") {
                    uploadAnswersResponse.isChildrenDataCollected = true
                }
                if (tag.isChecked && tag.name == "No") {
                    uploadAnswersResponse.isChildrenDataCollected = false
                }
            }
        }

        8 -> {
            if (allQuestions[8].tags != null) {
                uploadAnswersResponse.handlingMechanisms = listOf()
                uploadAnswersResponse.haveHandlingMechanism = false
                for (tag in allQuestions[8].tags!!) {
                    if (tag.isChecked) {
                        uploadAnswersResponse.haveHandlingMechanism = true
                        uploadAnswersResponse.handlingMechanisms += tag.name
                    }
                }
            }
        }

        9 -> {
            if (allQuestions[9].tags != null) {
                uploadAnswersResponse.storageMechanisms = listOf()
                uploadAnswersResponse.haveStorageMechanism = false
                for (tag in allQuestions[9].tags!!) {
                    if (tag.isChecked) {
                        uploadAnswersResponse.haveStorageMechanism = true
                        uploadAnswersResponse.storageMechanisms += tag.name
                    }
                }
            }
        }

        10 -> {
            if (allQuestions[10].tags != null) {
                uploadAnswersResponse.riskAssessmentMechanisms = listOf()
                uploadAnswersResponse.haveRiskAssessmentMechanism = false
                for (tag in allQuestions[10].tags!!) {
                    if (tag.isChecked) {
                        uploadAnswersResponse.haveRiskAssessmentMechanism = true
                        uploadAnswersResponse.riskAssessmentMechanisms += tag.name
                    }
                }
            }
        }
    }
//    println("uploadAnswersResponse: $uploadAnswersResponse")
}