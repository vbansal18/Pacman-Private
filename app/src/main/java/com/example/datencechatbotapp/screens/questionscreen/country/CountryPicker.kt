import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    var selectedCountry : String = ""
    val country by remember {
        mutableStateOf(selectedCountry)
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
                .padding(vertical = 50.dp)
                .background(Color(0x8CE4E5E7)),
        ) {
            items(parentOptions) { country ->
                CountryRow(country.country, country.flag_url, selectedCountry)
            }
        }
    }
}

@Composable
fun CountryRow(name: String, flag_url: String, selectedCountry: String, ) {
    val color = remember {
        mutableStateOf(Color(0x8CE4E5E7))
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(color.value)
            .clickable {
                color.value = Color(218, 251, 114, 10)
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