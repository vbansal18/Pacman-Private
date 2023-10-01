package com.example.datencechatbotapp.screens.questionscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.max
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    tags: MutableMap<String, Boolean>,
) {
    BottomSheetScaffold(
        sheetDragHandle = { DragHandle() },
        sheetPeekHeight = 100.dp,
        sheetContent = { BottomSheetContent(tags = tags) },
        containerColor = Color.White,
        sheetContainerColor = Color(241, 241, 241, 255),
        sheetContentColor = Color(241, 241, 241, 255),
        modifier = Modifier
            .fillMaxWidth()
    ) {
    }
}


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(tags: MutableMap<String, Boolean>) {
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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (key in tags.keys) {
                tags[key]?.let {
                    var isActive by remember { mutableStateOf(it) }

                    FilterChip(
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 2.dp),
                        selected = isActive,
                        onClick = {
                            isActive = !isActive
                            tags[key] = isActive
                            println("${tags[key]}")
                        },
                        label = { Text(text = "$key") },
                        colors = FilterChipDefaults.filterChipColors(
                            disabledContainerColor = Color(217, 217, 217, 255),
                            selectedContainerColor = Color(217, 251, 114, 255),

                            )
                    )
                }
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
            thickness = 5.dp,
            color = Color(0xFFD9FB72),
            modifier = Modifier
                .weight(.3f)
        )
        Row(
            modifier = Modifier.weight(.3f)
        ) {

        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "View more tags", color = Color.Black, fontSize = 12.sp)
    }
}