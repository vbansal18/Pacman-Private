package com.example.datencechatbotapp.screens

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.analytics.AnalyticsListener
import androidx.navigation.NavHostController
import com.example.datencechatbotapp.R
import io.sanghun.compose.video.RepeatMode
import io.sanghun.compose.video.VideoPlayer
import io.sanghun.compose.video.controller.VideoPlayerControllerConfig
import io.sanghun.compose.video.uri.VideoPlayerMediaItem

@Composable
fun DemoVideoPlayer(navController: NavHostController) {
    val mContext = LocalContext.current
    Exoplayer(mContext, navController)
}

@Composable
fun Exoplayer(mContext: Context, navController: NavHostController) {
    var orientation by remember {
        mutableStateOf(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    }
    if(orientation==ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
        LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    }
    if(orientation==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
        LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }

    Box(
        Modifier.background(Color.Transparent).fillMaxSize(),
        contentAlignment = TopStart
    ) {

        VideoPlayer(
            mediaItems = listOf(
                VideoPlayerMediaItem.RawResourceMediaItem(
                    resourceId = R.raw.pacman_demo_video,
                )
            ),
            handleLifecycle = true,
            autoPlay = true,
            usePlayerController = true,
            handleAudioFocus = true,
            controllerConfig = VideoPlayerControllerConfig(
                showSpeedAndPitchOverlay = false,
                showSubtitleButton = false,
                showCurrentTimeAndTotalTime = true,
                showBufferingProgress = false,
                showForwardIncrementButton = true,
                showBackwardIncrementButton = true,
                showBackTrackButton = false,
                showNextTrackButton = false,
                showRepeatModeButton = true,
                controllerShowTimeMilliSeconds = 5_000,
                controllerAutoShow = true,
                showFullScreenButton = false
            ),
            volume = 0.5f,  // volume 0.0f to 1.0f
            repeatMode = RepeatMode.NONE,       // or RepeatMode.ALL, RepeatMode.ONE
            onCurrentTimeChanged = { // long type, current player time (millisec)
                Log.e("CurrentTime", it.toString())
            },
            playerInstance = { // ExoPlayer instance (Experimental)
                addAnalyticsListener(
                    object : AnalyticsListener {
                        // player logger
                    }
                )
            },
            modifier = Modifier
                .fillMaxSize()
                .align(Center),
        )
        Text(
            text = "Skip Video",
            Modifier
                .padding(top = 60.dp)
                .rotate(270f)
                .background(MaterialTheme.colorScheme.surface)
                .clickable {
                    orientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    navController.navigate("upload")
                },
            color = MaterialTheme.colorScheme.background
        )
    }
}

@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(orientation) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            // restore original orientation when view disappears
            activity.requestedOrientation = originalOrientation
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
