import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.datencechatbotapp.models.TagItem
import com.example.datencechatbotapp.ui.theme.Gray

@Composable
fun SettingsScreen(theme: MutableState<Boolean>, navController: NavHostController) {
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
                    .fillMaxHeight(.4f)
                    .background(
                        Color(217, 251, 114, 255),
                        RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)
                    )

            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(20.dp)
                        .padding(top = 20.dp),
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
                        Image(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "settingsbtn",
                            Modifier.scale(1.5f)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(.8f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Settings",
                            textAlign = TextAlign.Start,
                            fontSize = 30.sp,
                            modifier = Modifier.padding(start = 8.dp),
                            fontWeight = FontWeight(600)
                        )
                    }
                }


            }
        }

        BottomSection(theme, navController)
    }


}

@Composable
private fun BottomSection(theme: MutableState<Boolean>, navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.83f)
            .padding(horizontal = 20.dp)
            .background(
                MaterialTheme.colorScheme.background,
                RoundedCornerShape(topEnd = 30.dp, topStart = 25.dp)
            )

    ) {
        items(1) {
            UserDetailsSection()
            AccountSettingsSection(theme, navController)
            MoreSection(navController)
        }
    }
}


@Composable
private fun UserDetailsSection() {
    Row(
        modifier = Modifier.padding(30.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            imageVector = Icons.Filled.Person,
            contentDescription = "Person",
            modifier = Modifier
                .background(Color.LightGray, RoundedCornerShape(50.dp))
                .padding(10.dp),
        )
        Text(
            text = "Conor Mcgregor",
            fontSize = 18.sp,
            fontWeight = FontWeight(500),
            modifier = Modifier.padding(start = 20.dp),
            color = MaterialTheme.colorScheme.surface
        )
    }
    Spacer(modifier = Modifier.height(3.dp))
    Divider(thickness = 1.dp)
}

@Composable
private fun AccountSettingsSection(theme: MutableState<Boolean>, navController: NavHostController) {
    val items = mutableListOf(
        TagItem("Edit profile", false, id = "editProfile"),
        TagItem("Change password", false),
        TagItem("Feedback", false, id = "feedback"),
    )
    val items2 = mutableListOf(TagItem("Video demo", false),)
    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = "Account Settings",
            color = Color(173, 173, 173, 255),
            fontSize = 14.sp,
            fontWeight = FontWeight(500)
        )
        Spacer(modifier = Modifier.height(30.dp))
        OptionItems(items, navController)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if(theme.value==true) {
                Text(
                    text = "Dark Mode",
                    fontSize = 15.sp,
                    fontWeight = FontWeight(400),
                    modifier = Modifier
                        .weight(.8f),
                    color = MaterialTheme.colorScheme.surface,
                )
            }
            else{
                Text(
                    text = "Light Mode",
                    fontSize = 15.sp,
                    fontWeight = FontWeight(400),
                    modifier = Modifier
                        .weight(.8f),
                    color = MaterialTheme.colorScheme.surface,
                )
            }
            Switch(
                checked = theme.value,
                onCheckedChange = { theme.value = !theme.value },
                modifier = Modifier
                    .weight(.2f),
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Gray,
                    uncheckedThumbColor = Color.White,
                    checkedTrackColor = Color(217, 251, 114, 255),
                    uncheckedTrackColor = Color.LightGray,
                    uncheckedBorderColor = Color.Transparent
                )
            )
        }
        OptionItems(items2, navController)
        Spacer(modifier = Modifier.height(3.dp))
    }
    Divider(thickness = 1.dp)

}

@Composable
private fun MoreSection(navController: NavHostController) {
    val items = mutableListOf(
        TagItem("Coupons / FAQs", false),
        TagItem("Referral program", false),
    )
    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = "More",
            color = Color(173, 173, 173, 255),
            fontSize = 14.sp,
            fontWeight = FontWeight(500)
        )
        Spacer(modifier = Modifier.height(30.dp))
//        Uncomment below to get coupons and referral in your settings
//        OptionItems(items, navController)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clickable { navController.navigate("login")},
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Logout",
                fontSize = 15.sp,
                fontWeight = FontWeight(500),
                color = MaterialTheme.colorScheme.surface,
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
    }
    Divider(thickness = 1.dp)
}


@Composable
private fun OptionItems(
    names: MutableList<TagItem>,
    navController: NavHostController,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        for (item in names) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clickable { if(item.id!=null) navController.navigate(item.id!!) },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = item.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight(400),
                    modifier = Modifier
                        .weight(.8f),
                    color = MaterialTheme.colorScheme.surface,
                )
                Image(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "move",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface)
                )
            }
        }
    }
}