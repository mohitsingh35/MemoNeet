package com.ncs.memoneet.Screens.Splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.ncs.memoneet.R
import com.ncs.memoneet.Screens.Main.MainActivity
import com.ncs.memoneet.ui.theme.primary
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            primary {
                splashScreen()
            }
        }
    }
}

@Composable
fun splashScreen(){

    var scale= remember {
        Animatable(0f)
    }
    val context= LocalContext.current
    LaunchedEffect(key1 = true){
        scale.animateTo(targetValue = 1f, animationSpec = tween(1500, easing ={
            OvershootInterpolator(5f).getInterpolation(it)
        } ))
        delay(3000L)
        val intent=Intent(context,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }
    Box (modifier = Modifier
        .fillMaxSize()
        .background(Color.White), contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = R.drawable.icon), contentDescription = "null", modifier = Modifier.scale(scale.value))
    }
}