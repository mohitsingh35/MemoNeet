package com.ncs.memoneet.Screens.Capsule

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.ncs.memoneet.Screens.Main.MainActivity
import com.ncs.memoneet.Navigation.Navigation
import com.ncs.memoneet.Screens.Main.Timer
import com.ncs.memoneet.Screens.Main.timerReached
import com.ncs.memoneet.ui.theme.primary

class CapsuleActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            primary {
                val pagerState = rememberPagerState(pageCount = 3)
                val navController = rememberNavController()
                var showtimer by remember {
                    mutableStateOf(false)
                }
                if (showtimer){
                    timerReached()
                }
                Column(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)){
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.09f)
                        .background(Color.White)){
                        Row (
                            Modifier
                                .fillMaxSize()
                                .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween){
                            Row(Modifier.padding(top = 10.dp)) {
                                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "", tint = Color.Black, modifier = Modifier.clickable {
                                    this@CapsuleActivity.startActivity(Intent(this@CapsuleActivity,
                                        MainActivity::class.java))
                                })
                                Spacer(modifier = Modifier.width(15.dp))
                                Text(text = "Blood", fontSize = 18.sp, color = Color.Black, fontWeight = FontWeight.Bold) 
                            }
                            Timer(isRunning = true) {
                                showtimer=true
                            }

                        }
                    }
                    Navigation(navController = navController, pagerState = pagerState)
                }
            }
        }
    }
}
