package com.example.datencechatbotapp.screens.leadgeneration

import android.content.Intent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datencechatbotapp.R
import com.example.datencechatbotapp.screens.components.SettingsDropDown
import kotlin.text.Typography.bullet

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LeadGen() {
    Column(
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
            )
            .padding(20.dp)
            .background(MaterialTheme.colorScheme.background, RoundedCornerShape(20.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
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
                    fontSize = 24.sp,
                    fontWeight = FontWeight(300),
                    modifier = Modifier
                        .scale(scaleY = 2f, scaleX = 1f)
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
            Button(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
            ) {
                Text(
                    text = ">",
                    color = MaterialTheme.colorScheme.surface,
                    textAlign = TextAlign.End,
                    fontSize = 24.sp,
                    fontWeight = FontWeight(300),
                    modifier = Modifier
                        .scale(scaleY = 2f, scaleX = 1f)
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
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.leadgen), contentDescription = "leadgen",
                Modifier
                    .height(140.dp)
                    .width(170.dp))
            Spacer(Modifier.height(5.dp))
            Text(
                text = "SPICE ROUTE LEGAL",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.surface,
            )
            Spacer(Modifier.height(5.dp))
            Row {
                Text(
                    text = "Website : ",
                    color = MaterialTheme.colorScheme.surface,
                )
                Text(
                    text = "spiceroutelegal.com",
                    color = Color(153, 157, 255, 255),
                )
            }
        }
        Divider(
            thickness = Dp.Hairline,
            color = MaterialTheme.colorScheme.surface,
        )
        val bottomFade = Brush.verticalGradient(0.8f to Color.Black, 1f to Color.Transparent)

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.9f)
                .padding(20.dp)
                .fadingEdge(bottomFade),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            val messages = listOf(
                "Widely regarded as the best data protection and privacy group in India, our team combines the unique experience of having advised most of the market leaders across all sectors on data protection strategy, compliance, commercial arrangements involving data, global data transfers, artificial intelligence and machine learning, litigation and commercial negotiations",
                "Members of the team have over two decades of experience advising corporates on data issues and have been closely involved in the matters that have defined this area of law.",
                "We help clients tailor their approach towards data management based on their business needs and applicable laws. We stress-test business models against legal requirements to assess impact on business practices like cross-selling, monetizing data, underwriting customers through data. We also advise clients on embedding data protection principles into tech architecture, UI/UX and business processes. Advised a company that uses machine learning to collect and structure patient health records with a focus on school-going children. We structured its contracts with various stakeholders - including doctors, hospitals and educational institutions- with a key focus on protecting sensitive personal data. Advised Cutting Chai Content on its data handling practices and the applicability of global privacy laws.Undertook a clause-by-clause analysis of the various drafts of India’s upcoming personal data protection law for a global cloud service provider, and assessed their implications on the client’s India operations."
            )
            val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))

            items(1) {
                Text(
                    buildAnnotatedString {
                        messages.forEach {
                            withStyle(style = paragraphStyle) {
                                append(bullet)
                                append("\t\t")
                                append(it)
                                append("\n\n")
                            }
                        }

                    },
                    color = MaterialTheme.colorScheme.surface,
                )
            }
        }
        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(213, 245, 111, 255),
                contentColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth(.6f)
        ) {
            Text(
                text = "Connect",
                color = Color(75, 75, 75, 255),
                fontSize = 18.sp
            )
        }
    }
}
fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }