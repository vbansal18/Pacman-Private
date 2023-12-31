package com.example.datencechatbotapp.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.datencechatbotapp.R
import com.example.datencechatbotapp.api.FileUploadViewModel
import com.example.datencechatbotapp.screens.components.EmailTextField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun UploadFiles(viewModel: FileUploadViewModel, navController: NavHostController) {
    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }
    var link by remember {
        mutableStateOf<String?>(null)
    }
    val context = LocalContext.current
    var isFileUploading by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.60f)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.95f)
                    .shadow(
                        8.dp,
                        RoundedCornerShape(0.dp, 0.dp, 40.dp, 40.dp)
                    )
                    .background(
                        Color(217, 251, 114, 255), RoundedCornerShape(
                            bottomEnd = 28.dp,
                            bottomStart = 28.dp
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cloud),
                    contentDescription = "cloud_img",
                    modifier = Modifier
                        .fillMaxWidth(.4f)
                        .aspectRatio(1f)
                        .background(Color.White, CircleShape)
                        .padding(50.dp),
                )
                Image(
                    painter = painterResource(id = R.drawable.file_upload),
                    contentDescription = "file_img",
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .scale(3f)
                )
                Text(
                    text = "Upload your files",
                    fontSize = 25.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF1F1F1F),
                    modifier = Modifier.padding(top = 40.dp)
                )
                Text(
                    text = "Browse and choose the files you want to upload",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = "Max file size  : 20 MB",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF6B6B6B),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 40.dp, bottom = 20.dp)
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PdfFilePickerAndUploader(
                    changeUri = {
                        selectedFileUri = it
                    }
                )

            }
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedFileUri.toString(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(.5f),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.surface
            )
            if (selectedFileUri != null) {
                Spacer(modifier = Modifier.width(5.dp))
                Image(
                    imageVector = Icons.Outlined.AddCircle,
                    contentDescription = "Cross",
                    Modifier
                        .clickable {
                            selectedFileUri = null
                        }
                        .rotate(45f)
                        .size(15.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(.5f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    thickness = 0.7.dp,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .weight(.3f)
                )
                Text(
                    text = "OR",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.padding(horizontal = 10.dp),
                    color = MaterialTheme.colorScheme.surface
                )
                Divider(
                    thickness = 0.7.dp,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .weight(.3f)
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            EmailTextField(
                hint = "Paste Your Link...",
                icn = R.drawable.baseline_add_link_24,
                bgcolor = MaterialTheme.colorScheme.background,
                onValueChange = { value ->
                    if (value != null) {
                        link = value
                    }
                }
            )
        }
        if (!isFileUploading) {
            Button(
                onClick = {
                    upload(
                        viewModel,
                        navController,
                        selectedFileUri,
                        context,
                        link,
                        isFileUploading = {
                            isFileUploading = it
                        }
                    )
                },
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .padding(20.dp),
                enabled = !isFileUploading
            ) {
                Text(text = "Submit", color = Color.Black)
            }
        } else {
            IndeterminateCircularIndicator()
        }
    }

}


@Composable
fun PdfFilePickerAndUploader(
    changeUri: (Uri?) -> Unit
) {
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                data?.data?.let {
                    changeUri(it)
                }
            }
        }
    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "application/pdf" // Filter for PDF files

            // Start the file picker activity
            launcher.launch(intent)

        },
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.background,
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = CircleShape,
        modifier = Modifier
            .width(60.dp)
            .aspectRatio(1f),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = "+",
            fontSize = 32.sp,
            fontWeight = FontWeight(300),
        )
    }
}

fun upload(
    viewModel: FileUploadViewModel,
    navController: NavHostController,
    selectedFileUri: Uri?,
    context: Context,
    link: String?,
    isFileUploading: ((Boolean) -> Unit),
) {

    val scope = CoroutineScope(Dispatchers.IO)
    if (selectedFileUri != null) {
        selectedFileUri.let { uri ->
            // Handle the selected PDF file here
            Log.d("UPLOAD", "Selected File: ${uri.path}")
            scope.launch {
                try {
                    isFileUploading(true)
                    val response = viewModel.uploadPdfFile(fileUri = uri, context)
                    if (response.isSuccessful) {
                        // Handle a successful upload
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT)
                                .show()
                            Log.d("Success", response.body().toString())
                            navController.navigate("gettingStarted/${"dashboard"}")
                        }
                    } else {
                        // Handle upload failure
                        isFileUploading(false)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context, "Upload Failed", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Log.d("ERROR", "Error: ${e.message}}")
                    }
                }
            }
        }
    } else if (link != null) {
        scope.launch {
            try {
                isFileUploading(true)
                val response = viewModel.uploadPdfLink(link)
                if (response.isSuccessful) {
                    // Handle a successful upload
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Upload Success", Toast.LENGTH_SHORT)
                            .show()
                        Log.d("Success", response.body().toString())
                        navController.navigate("gettingStarted/${"dashboard"}")
                    }
                } else {
                    // Handle upload failure
                    isFileUploading(false)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Upload Failed", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.d("ERROR", "Error: ${e.message}}")
                }

            }
        }
    } else {
        Toast.makeText(context, "Select the file to upload.", Toast.LENGTH_SHORT).show()
    }
}
