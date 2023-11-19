package com.example.datencechatbotapp.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.datencechatbotapp.R
import com.example.datencechatbotapp.models.ConsultancyResponse_
import com.example.datencechatbotapp.models.GetConsultancyResponse
import com.example.datencechatbotapp.screens.components.SettingsDropDown
import com.example.datencechatbotapp.screens.leadgeneration.fadingEdge
import com.google.gson.Gson
import kotlinx.coroutines.async


@Composable
fun ConsultancyScreen(navController: NavHostController) {
    var isEvaluateCurrentMechanismsNull by remember {
        mutableStateOf(false)
    }

    var consultancy by remember { mutableStateOf<ConsultancyResponse_?>(null) }
    val screens = mutableListOf<String>(
        "legalRegulations",
        "industryBestPractices",
        "evaluateCurrentMechanisms",
        "previousRulings",
        "suggestions"
    )
    if(consultancy_data!=null){
        consultancy = consultancy_data!!.seniorAssociateSays
    }
    if(isEvaluateCurrentMechanismsNull){
        screens.removeAt(2)
    }
    var selectedScreen by remember { mutableStateOf(screens.first()) }
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                ) {
                    Text(
                        text = "<",
                        color = MaterialTheme.colorScheme.surface,
                        textAlign = TextAlign.Start,
                        fontSize = 24.sp,
                        fontWeight = FontWeight(300),
                        modifier = Modifier
                            .scale(scaleY = 1.5f, scaleX = 0.8f)
                            .clickable {
                                navController.popBackStack()
                            }
                    )
                }
                SettingsDropDown(navController, MaterialTheme.colorScheme.surface)
            }
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color(213, 245, 111, 255),
                modifier = Modifier
                    .padding(12.dp)
                    .height(70.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                screens.forEach { screen ->
                    BottomNavigationItem(
                        modifier = Modifier
                            .padding(10.dp),
                        icon = { Image(
                            painter = getIconForScreen(screen),
                            contentDescription = screen,
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface),
                            modifier = Modifier
                                .aspectRatio(1f)
                                .background(
                                    MaterialTheme.colorScheme.onSecondary,
                                    RoundedCornerShape(10.dp)
                                )
                                .padding(10.dp)
                        ) },
                        selected = screen == selectedScreen,
                        onClick = {
                            selectedScreen = screen
                        },
                    )
                }
            }
        },
        content = {
            Log.d("PaddingValue","$it")

            LaunchedEffect(consultancy){

            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(20.dp))
                    .padding(20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(consultancy!=null){
                    if(selectedScreen=="legalRegulations"){
                        ConsultancyPage(
                            icon = R.drawable.legal_regulations,
                            title = "Legal Regulations",
                            handlingData = consultancy?.consultancyResponses?.handlingData!!.legalRegulations,
                            storingData = consultancy?.consultancyResponses?.storingData!!.legalRegulations,
                            riskAssessment = consultancy?.consultancyResponses?.riskAssessment!!.legalRegulations
                        )
                    }
                    if(selectedScreen=="industryBestPractices"){
                        ConsultancyPage(
                            icon = R.drawable.industry_best_practices,
                            title = "Industry Best Practices",
                            handlingData = consultancy?.consultancyResponses?.handlingData!!.industryBestPractices,
                            storingData = consultancy?.consultancyResponses?.storingData!!.industryBestPractices,
                            riskAssessment = consultancy?.consultancyResponses?.riskAssessment!!.industryBestPractices
                        )
                    }
                    if(selectedScreen=="evaluateCurrentMechanisms"){
                        ConsultancyPage(
                            icon = R.drawable.evaluate_current_mechanisms,
                            title = "Evaluation of mechanism",
                            handlingData = consultancy?.consultancyResponses?.handlingData!!.evaluateCurrentMechanisms,
                            storingData = consultancy?.consultancyResponses?.storingData!!.evaluateCurrentMechanisms,
                            riskAssessment = consultancy?.consultancyResponses?.riskAssessment!!.evaluateCurrentMechanisms
                        )
                    }
                    if(selectedScreen=="previousRulings"){
                        ConsultancyPage(
                            icon = R.drawable.previous_rulings,
                            title = "Page out of precedent",
                            handlingData = consultancy?.previousRulings,
                            storingData = consultancy?.lawViolations,
                            riskAssessment = null
                        )
                    }
                    if(selectedScreen=="suggestions"){
                        ConsultancyPage(
                            icon = R.drawable.suggestions,
                            title = "Suggestions for Improvement",
                            handlingData = consultancy?.consultancyResponses?.handlingData!!.suggestions,
                            storingData = consultancy?.consultancyResponses?.storingData!!.suggestions,
                            riskAssessment = consultancy?.consultancyResponses?.riskAssessment!!.suggestions
                        )
                    }
                }
                else{
                    CircularProgressIndicator()
                }
            }

        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("allLeadsFromResponseScreen") },
                backgroundColor = Color(244, 255, 210, 255),
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.floatingaction),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(30.dp)
                    )
                }
            )
        },
        )
}

@Composable
private fun ConsultancyPage(
    icon: Int,
    title: String,
    handlingData: String?,
    storingData: String?,
    riskAssessment: String?,
) {
    val bottomFade = Brush.verticalGradient(0.8f to Color.Black, 1f to Color.Transparent)

    Column(
        Modifier.padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "$icon",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface),
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = title,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.surface,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.85f)
                .padding(horizontal = 30.dp)
                .padding(top = 50.dp)
                .fadingEdge(bottomFade),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            val messages = mutableListOf(
                "Handling Data", handlingData, "Storing Data", storingData, "Risk Assessment", riskAssessment
            )
            if(riskAssessment==null){
                messages.removeAt(5)
                messages.removeAt(4)
                messages.removeAt(3)
                messages.removeAt(2)
                messages.removeAt(1)
                messages.removeAt(0)
                messages.add(0, "Previous Rulings")
                messages.add(1, handlingData)
                messages.add(2, "Law Violations")
                messages.add(3, storingData)
            }
            val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))

            items(1) {
                Text(
                    buildAnnotatedString {
                        messages.forEach {
                            withStyle(style = paragraphStyle) {
                                append(it)
                                append("\n")
                                append("\n")
                            }
                        }

                    },
                    color = MaterialTheme.colorScheme.surface,
                )
            }
        }
    }
}


@Composable
fun getIconForScreen(screen: String): Painter {
    return when (screen) {
        "legalRegulations" -> painterResource(id = R.drawable.legal_regulations)
        "industryBestPractices" -> painterResource(id = R.drawable.industry_best_practices)
        "evaluateCurrentMechanisms" -> painterResource(id = R.drawable.evaluate_current_mechanisms)
        "previousRulings" -> painterResource(id = R.drawable.previous_rulings)
        "suggestions" -> painterResource(id = R.drawable.suggestions)
        else -> painterResource(id = R.drawable.legal_regulations)
    }
}

fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }