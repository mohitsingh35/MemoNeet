package com.ncs.memoneet.Screens.Main

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ncs.memoneet.R
import com.ncs.memoneet.Screens.Capsule.TimerViewModel
import com.ncs.memoneet.Screens.Main.MainActivity
import com.ncs.memoneet.ui.theme.main
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Timer(isRunning: Boolean, timerFinished: () -> Unit) {
    val timerViewModel = viewModel<TimerViewModel>()

    Column(
        modifier = Modifier
            .height(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(
                2.dp, color = main,
                RoundedCornerShape(10.dp)
            )
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Filled.Refresh, contentDescription = "", tint = main)
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "${(timerViewModel.time / 60).toString().padStart(2, '0')}:${
                    (timerViewModel.time % 60).toString().padStart(2, '0')
                }",
                fontSize = 20.sp,
                color = main, fontWeight = FontWeight.ExtraBold
            )
        }
        LaunchedEffect(isRunning) {
            if (isRunning) {
                timerViewModel.startTimer()
            }
        }
    }

    if (timerViewModel.time == 0) {
        timerFinished()
    }
}

@Composable
fun timer() {
    var isPlaying by remember { mutableStateOf(true) }
    var speed by remember { mutableStateOf(1f) }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.timer)
    )

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying,
        speed = speed,
        restartOnPlay = false
    )


    Column(
        Modifier
            .background(Color.Transparent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition,
            progress,
            modifier = Modifier.size(250.dp)
        )
    }
}

@Composable
fun timerReached() {
    val context= LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    AlertDialog(
        onDismissRequest = {
            showDialog = false
        },
        title = { },
        text = {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                androidx.compose.material.Text(
                    "Opps! Timer Finished",
                    color = Color.Black,
                    fontWeight = FontWeight.Light,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                timer()
                Spacer(modifier = Modifier.height(10.dp))
                androidx.compose.material.Text(
                    "Time Ran Out ",
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                Box(Modifier
                    .fillMaxWidth()
                    .height(85.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        context.startActivity(Intent(context, MainActivity::class.java))
                    }
                    .background(main), contentAlignment = Alignment.Center) {
                    Row {
                        Text(
                            text = "Start Again",
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        },
        confirmButton = {

        }
    )

}