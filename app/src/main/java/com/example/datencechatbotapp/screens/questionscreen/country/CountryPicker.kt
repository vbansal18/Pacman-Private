import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun CountryPicker(
) {
    val parentOptions = AllCountries
    val expandedState by remember {
        mutableStateOf(true)
    }
    var selectedOption by remember {
        mutableStateOf(parentOptions[0])
    }

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
                .padding(vertical = 60.dp)
                .background(Color(0x8CE4E5E7)),
        ) {
            items(parentOptions) { country ->
                CountryRow(country.country, country.flag_url)
            }
        }
    }
}

@Composable
fun CountryRow(name: String, flag_url: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(0x8CE4E5E7))
            .clickable(enabled = true, onClick = { Modifier.background(Color(0x8CE4E5E7)) }),
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
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape)
        )
    }
    Divider(modifier = Modifier.fillMaxWidth(), thickness = Dp.Hairline)
}