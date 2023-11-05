package com.example.datencechatbotapp.screens.leadgeneration

import DraggableBtn
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.datencechatbotapp.api.FileUploadViewModel
import com.example.datencechatbotapp.models.GetAllCasesModel
import com.example.datencechatbotapp.screens.components.SettingsDropDown
import com.example.datencechatbotapp.screens.generatePDF
import kotlinx.coroutines.async

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AllLeadsScreen(
    navController: NavHostController,
    sessionNumber: Int?,
    viewModel: FileUploadViewModel
) {
    val context = LocalContext.current
    var cases by remember { mutableStateOf<GetAllCasesModel?>(null) }
    LaunchedEffect(Unit) {
        try {
            val parsedcases = async { viewModel.getAllCases().body() }
            cases = parsedcases.await()
        } catch (e: Exception) {
            Log.d("CasesError", "Error : $e")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.onSecondary
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
            ) {
                Text(
                    text = "<",
                    color = MaterialTheme.colorScheme.surface,
                    textAlign = TextAlign.Start,
                    fontSize = 22.sp,
                    fontWeight = FontWeight(300),
                    modifier = Modifier
                        .scale(scaleY = 1.5f, scaleX = 0.8f)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(
                                color = Color.Black,
                                bounded = true,
                                radius = 20.dp
                            ),
                            onClick = {

                            }
                        )
                )
            }
            SettingsDropDown(navController, MaterialTheme.colorScheme.surface)
        }
        Text(
            text = "Further Assistance",
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.surface
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.85f)
                .padding(top = 50.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            if(cases!=null){
                items(cases!!.allCases[sessionNumber!!].lawFirmNames.size){
                    val leads = "{\"lawFirmNames\": [\n" +
                            "                \"${cases!!.allCases[sessionNumber].lawFirmNames[0]}\",\n" +
                            "                \"${cases!!.allCases[sessionNumber].lawFirmNames[1]}\",\n" +
                            "                \"${cases!!.allCases[sessionNumber].lawFirmNames[2]}\"\n" +
                            "     ],\n" +
                            "}"
                    println(leads)
                    LeadItem(cases!!.allCases[sessionNumber].lawFirmNames[it], navController, leads, it)
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(.9f)
                .background(MaterialTheme.colorScheme.onTertiary, RoundedCornerShape(26.dp)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(cases!=null){
                DraggableBtn(navController, "lead", "Swipe to download PDF"
                ) { generatePDF(context, cases!!.allCases[sessionNumber!!].consultancy) }
            }
        }
    }
}

@Composable
private fun LeadItem(name: String, navController: NavHostController, leads: String, i: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .padding(horizontal = 20.dp)
            .clickable(
                onClick = {
                    navController.navigate("lead/${i}/${leads}")
                }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    )  {
        Image(
            painter = painterResource(id = setCurrentLogo(name)),
            contentDescription = "leadlogo",
            modifier = Modifier
                .weight(.2f)
                .size(50.dp),
            contentScale = ContentScale.FillWidth,
        )
        Text(
            text = name,
            fontSize = 15.sp,
            modifier = Modifier
                .weight(.7f)
                .padding(start = 20.dp),
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.surface
        )
        Text(
            text = ">",
            color = MaterialTheme.colorScheme.surface,
            textAlign = TextAlign.End,
            fontSize = 22.sp,
            fontWeight = FontWeight(300),
            modifier = Modifier
                .scale(scaleY = 1.5f, scaleX = 0.8f)
        )
    }
}
