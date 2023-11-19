import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.datencechatbotapp.R
import com.example.datencechatbotapp.data.preferences.PreferencesDatastore
import com.example.datencechatbotapp.screens.leadgeneration.openWebsite
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun GoogleSignScreen(navController: NavHostController, datastore: PreferencesDatastore) {

    var isSignInStarted by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    if (isSignInStarted) {
        GoogleSignInDemo(navController, LocalContext.current, datastore)
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary,
                            MaterialTheme.colorScheme.primary
                        )
                    )
                )
                .padding(5.dp)
                .padding(vertical = 150.dp)
                .background(Color.White, RoundedCornerShape(30.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Image(
                painter = painterResource(id = R.drawable.pdflogo),
                contentDescription = "",
                Modifier.size(70.dp)
            )
            Text(
                text = "Sign In",
                fontWeight = FontWeight(900),
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF292929),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Lets get started with Pacman.",
                fontWeight = FontWeight(400),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF292929),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    isSignInStarted = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 50.dp)
                    .border(
                        border = BorderStroke(Dp.Hairline, Color.Black),
                        RoundedCornerShape(10.dp)
                    ),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ggl),
                    contentDescription = "",
                    Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = "Sign in with Google",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp)
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "By signing up to create an account, I accept Company's ",
                    fontWeight = FontWeight(400),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF292929),
                )
                Text(
                    text = "Terms of Use and Privacy Policy.",
                    fontWeight = FontWeight(600),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF2180E9),
                    modifier = Modifier.clickable {
                        openWebsite(context, "https://datence.tech/")
                    },
                )
            }

        }
    }
}

@Composable
fun GoogleSignInDemo(
    navController: NavHostController,
    context: Context,
    datastore: PreferencesDatastore
) {
    var islogin by remember {
        mutableStateOf<Boolean?>(null)
    }
    var isSignOutStarted by remember {
        mutableStateOf<Boolean?>(null)
    }
    println("out before In google sign in demo -> islogin : $islogin and isSignOutStarted : $isSignOutStarted")
    LaunchedEffect(Unit) {
        datastore.getIsLogin().collect {
            islogin = it.value
        }
        println("In google sign in demo -> islogin : $islogin\n isSignOutStarted : $isSignOutStarted")
    }
    LaunchedEffect(Unit) {
        datastore.getIsSignOutStarted().collect {
            isSignOutStarted = it.value
        }
        println("In google sign in demo -> islogin : $islogin\n isSignOutStarted : $isSignOutStarted")
    }

    println("out after google sign in demo -> islogin : $islogin and isSignOutStarted : $isSignOutStarted")
    if(isSignOutStarted!=null && islogin!=null){
        var account by remember { mutableStateOf<GoogleSignInAccount?>(null) }
        var signInError by remember { mutableStateOf<Exception?>(null) }
        val webClientId = context.getString(R.string.default_web_client_id)
        val googleSignInClient: GoogleSignInClient = remember {
            GoogleSignIn.getClient(
                context,
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestId()
                    .requestProfile()
                    .build()
            )
        }

        val launcher =
            rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val signedInAccount = task.getResult(ApiException::class.java)
                    account = signedInAccount
                } catch (e: ApiException) {
                    signInError = e
                }
            }

        DisposableEffect(Unit) {
            var lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(context)
            if (lastSignedInAccount != null) {
                if (isSignOutStarted == false) {
                    println("lastSignedInAccount true")
                    account = lastSignedInAccount
                } else {
                    googleSignInClient.signOut().addOnCompleteListener(OnCompleteListener<Void?> {
                        // Handle sign-out completion if needed
                        CoroutineScope(Dispatchers.IO).launch {
                            datastore.setIsSignOutStarted(false)
                        }
                        val signInIntent = googleSignInClient.signInIntent
                        launcher.launch(signInIntent)
                    })
                }
            } else {
                val signInIntent = googleSignInClient.signInIntent
                launcher.launch(signInIntent)
            }

            onDispose { /* cleanup */ }
        }

        // Display the sign-in result
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (account != null) {
                LaunchedEffect(Unit) {
                    launch(Dispatchers.IO) { datastore.setIsLogin(true) }
                    launch(Dispatchers.IO) {
                        datastore.setUser(
                            account?.displayName.toString(),
                            account?.photoUrl.toString()

                        )
                    }
                }
                Toast.makeText(context, "Sign In Successful", Toast.LENGTH_SHORT).show()
                Text("Account Name: ${account?.displayName}")
                Text("Email: ${account?.email}")
                navController.navigate("dashboard")
            } else if (signInError != null) {
                Text("Sign-in failed!")
                Text("Error: ${signInError?.message}")
            } else {
                // Loading indicator or placeholder
                CircularProgressIndicator()
            }
        }
    }
}

fun signOut(
    googleSignInClient: GoogleSignInClient,
    context: Context,
    navController: NavHostController
) {
    googleSignInClient.signOut().addOnCompleteListener(OnCompleteListener<Void?> {
        Toast.makeText(context, "Sign In Successful", Toast.LENGTH_SHORT).show()
        navController.navigate("dashboard")
    })
}
