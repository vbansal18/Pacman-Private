package com.example.datencechatbotapp.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.datencechatbotapp.api.FileUploadViewModel
import com.example.datencechatbotapp.models.FeedbackModel
import com.example.datencechatbotapp.models.TagItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private var feedback by mutableStateOf<FeedbackModel>(FeedbackModel(0, listOf<String>(), ""))

@Composable
fun Feedback(navController: NavHostController, viewModel : FileUploadViewModel) {
    var bgEnabledRating1 by remember {
        mutableStateOf(Color(217, 217, 217, 255))
    }
    var bgEnabledRating2 by remember {
        mutableStateOf(Color(217, 217, 217, 255))
    }
    var bgEnabledRating3 by remember {
        mutableStateOf(Color(217, 217, 217, 255))
    }
    var bgEnabledRating4 by remember {
        mutableStateOf(Color(217, 217, 217, 255))
    }
    var bgEnabledRating5 by remember {
        mutableStateOf(Color(217, 217, 217, 255))
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.primary
                    )
                )
            ),
        contentAlignment = Alignment.BottomCenter

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.secondary,
                            MaterialTheme.colorScheme.secondary,
                            MaterialTheme.colorScheme.primary
                        )
                    )
                ),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(.5f)
                    .background(
                        Color(217, 251, 114, 255),
                        RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)
                    )

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.2f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .weight(.1f),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                    ) {
                        Text(
                            text = "<",
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontWeight = FontWeight(300),
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(.5f)
                                .scale(scaleY = 2f, scaleX = 1f)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple(
                                        color = Color.Black,
                                        bounded = true,
                                        radius = 20.dp
                                    ),
                                    onClick = {
                                        navController.popBackStack()
                                    }
                                )
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(.8f)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Feedback",
                            textAlign = TextAlign.Start,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
                Text(
                    text = "Share your feedback",
                    fontSize = 24.sp,
                    fontWeight = FontWeight(500),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 28.dp)
                )
                Text(
                    text = "Rate your experience", fontSize = 16.sp, modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 28.dp, top = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "😠", fontSize = 24.sp,
                        modifier = Modifier
                            .padding(10.dp)
                            .background(bgEnabledRating1, RoundedCornerShape(50.dp))
                            .padding(10.dp)
                            .clickable {
                                feedback.rating = 1
                                bgEnabledRating1 = Color(0xFF272323)
                                bgEnabledRating2 = Color(217, 217, 217, 255)
                                bgEnabledRating3 = Color(217, 217, 217, 255)
                                bgEnabledRating4 = Color(217, 217, 217, 255)
                                bgEnabledRating5 = Color(217, 217, 217, 255)
                            }
                    )
                    Text(
                        text = "😒", fontSize = 24.sp,
                        modifier = Modifier
                            .padding(10.dp)
                            .background(bgEnabledRating2, RoundedCornerShape(50.dp))
                            .padding(10.dp)
                            .clickable {
                                feedback.rating = 2
                                bgEnabledRating1 = Color(217, 217, 217, 255)
                                bgEnabledRating2 = Color(0xFF272323)
                                bgEnabledRating3 = Color(217, 217, 217, 255)
                                bgEnabledRating4 = Color(217, 217, 217, 255)
                                bgEnabledRating5 = Color(217, 217, 217, 255)
                            }

                    )
                    Text(
                        text = "😐", fontSize = 24.sp,
                        modifier = Modifier
                            .padding(10.dp)
                            .background(bgEnabledRating3, RoundedCornerShape(50.dp))
                            .padding(10.dp)
                            .clickable {
                                feedback.rating = 3
                                bgEnabledRating1 = Color(217, 217, 217, 255)
                                bgEnabledRating2 = Color(217, 217, 217, 255)
                                bgEnabledRating3 = Color(0xFF272323)
                                bgEnabledRating4 = Color(217, 217, 217, 255)
                                bgEnabledRating5 = Color(217, 217, 217, 255)
                            }

                    )
                    Text(
                        text = "😄", fontSize = 24.sp,
                        modifier = Modifier
                            .padding(10.dp)
                            .background(bgEnabledRating4, RoundedCornerShape(50.dp))
                            .padding(10.dp)
                            .clickable {
                                feedback.rating = 4
                                bgEnabledRating1 = Color(217, 217, 217, 255)
                                bgEnabledRating2 = Color(217, 217, 217, 255)
                                bgEnabledRating3 = Color(217, 217, 217, 255)
                                bgEnabledRating4 = Color(0xFF272323)
                                bgEnabledRating5 = Color(217, 217, 217, 255)
                            }

                    )
                    Text(
                        text = "😍", fontSize = 24.sp,
                        modifier = Modifier
                            .padding(10.dp)
                            .background(bgEnabledRating5, RoundedCornerShape(50.dp))
                            .padding(10.dp)
                            .clickable {
                                feedback.rating = 5
                                bgEnabledRating1 = Color(217, 217, 217, 255)
                                bgEnabledRating2 = Color(217, 217, 217, 255)
                                bgEnabledRating3 = Color(217, 217, 217, 255)
                                bgEnabledRating4 = Color(217, 217, 217, 255)
                                bgEnabledRating5 = Color(0xFF272323)
                            }
                    )
                }

            }
        }

        BottomSection(viewModel, navController)
    }


}

@Composable
private fun BottomSection(viewModel: FileUploadViewModel, navController: NavHostController) {
    val items = mutableListOf(
        TagItem("Quality of Evaluation", false),
        TagItem("Quality of Suggestions", false),
        TagItem("Customer service", false),
        TagItem("Overall service", false),
    )
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.7f)
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp)
            .background(
                MaterialTheme.colorScheme.background,
                RoundedCornerShape(topEnd = 40.dp, topStart = 40.dp)
            )
            .padding(30.dp)

    ) {
        items(1){
            Text(
                text = "What did you like?",
                fontSize = 18.sp,
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.surface
            )
            OptionItems(items)
            Text(
                text = "Your comment (optional)",
                fontSize = 18.sp,
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.padding(top = 40.dp)
            )
            var comment by remember { mutableStateOf(TextFieldValue("")) }

            TextField(
                label = {
                    Text(text = "Describe your experience ")
                },
                value = comment,
                onValueChange = {
                    comment = it
                    feedback.message = comment.text
                },
                colors = TextFieldDefaults.colors(
                    focusedSupportingTextColor = Color.Gray,
                    unfocusedSupportingTextColor = Color.Gray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color(234, 251, 180, 255),
                    unfocusedContainerColor = Color(234, 251, 180, 255)
                ),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Button(
                onClick = {
                          CoroutineScope(Dispatchers.IO).launch {
                              try {
                                  val feedbackResponse = viewModel.feedback(feedback)
                                  if(feedbackResponse.isSuccessful){
                                      Log.d("responseFeedback", feedbackResponse.toString())
                                      withContext(Dispatchers.Main){
                                          Toast.makeText(context, "Feedback Sent Successfully", Toast.LENGTH_SHORT).show()
                                          navController.popBackStack()
                                      }
                                  }
                              }
                              catch (e:Exception){
                                  Log.d("responseFeedbackError", e.message.toString())

                              }
                          }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(213, 245, 111, 255),
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 30.dp)
            ) {
                Text(
                    text = "Send",
                    color = Color(75, 75, 75, 255),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
private fun OptionItems(
    names: MutableList<TagItem>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        for (item in names) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = item.name,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(300),
                        modifier = Modifier
                            .weight(.8f),
                        color = MaterialTheme.colorScheme.surface,
                    )

                    CircleCheckbox(item)
                }
                Spacer(Modifier.height(5.dp))
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = Dp.Hairline,
                )
            }
        }
    }
}

@Composable
fun CircleCheckbox(item: TagItem,) {

    var isActive by remember { mutableStateOf(item.isChecked) }
    val imageVector = if (isActive) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle
    val tint = if (isActive) Color(105, 204, 5, 255) else Color(217, 217, 217, 255)
    val background = if (isActive) Color.White else Color(217, 217, 217, 255)
    if(isActive){
        if(item.name in feedback.servicesLiked){
//            Do nothing
        }
        else {
            feedback.servicesLiked += item.name
        }
    }
    else{
        if(item.name in feedback.servicesLiked){
            feedback.servicesLiked-=item.name
        }
        else {
//            Do nothing
        }
    }

    IconButton(
        onClick = { isActive = !isActive},
        modifier = Modifier.offset(x = 4.dp, y = 4.dp),
        enabled = true
    ) {

        Icon(
            imageVector = imageVector, tint = tint,
            modifier = Modifier.background(background, shape = CircleShape),
            contentDescription = "checkbox"
        )
    }
}
