package com.example.datencechatbotapp.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SettingsDropDown(navController: NavHostController) {
    var expandedState by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier.background(Color.Transparent)
    ) {
        // vertical 3 dots icon
        IconButton(
            onClick = {
                expandedState = true
            }
        ) {
            Icon(Icons.Default.MoreVert, contentDescription = "Open Menu")
        }

        DropdownMenu(
            expanded = expandedState,
            onDismissRequest = { expandedState = false },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onBackground)
                .width(170.dp),
            offset = DpOffset(20.dp, 5.dp),
        ) {
            // menu items
            DropdownMenuItem(
                text = {
                    Text("Settings")
                },
                onClick = {
                    expandedState = false
                    navController.navigate("settings")
                },
                trailingIcon = {
                    Icon(
                        Icons.Filled.Settings,
                        contentDescription = null
                    )
                },
                colors = MenuDefaults.itemColors(
                    textColor = MaterialTheme.colorScheme.surface,
                    trailingIconColor = MaterialTheme.colorScheme.surface,

                    )
            )

            DropdownMenuItem(
                text = {
                    Text("Feedback")
                },
                onClick = {
                    expandedState = false
                    navController.navigate("feedback")
                },
                trailingIcon = {
                    Icon(
                        Icons.Filled.Feedback,
                        contentDescription = null
                    )
                },
                colors = MenuDefaults.itemColors(
                    textColor = MaterialTheme.colorScheme.surface,
                    trailingIconColor = MaterialTheme.colorScheme.surface,

                    )
            )

            DropdownMenuItem(
                text = {
                    Text("Contact Us")
                },
                onClick = {
                    expandedState = false
                },
                trailingIcon = {
                    Icon(
                        Icons.Filled.Phone,
                        contentDescription = null
                    )
                },
                colors = MenuDefaults.itemColors(
                    textColor = MaterialTheme.colorScheme.surface,
                    trailingIconColor = MaterialTheme.colorScheme.surface,

                    )
            )
        }
    }
}
