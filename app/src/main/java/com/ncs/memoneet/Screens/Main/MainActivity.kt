package com.ncs.memoneet.Screens.Main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncs.memoneet.Screens.Capsule.CapsuleActivity
import com.ncs.memoneet.ui.theme.MemoNeetTheme
import com.ncs.memoneet.ui.theme.main
import com.ncs.memoneet.ui.theme.primary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            primary {
                val context= LocalContext.current
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White), contentAlignment = Alignment.Center){
                    Box(Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.1f)
                        .padding(16.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .clickable {
                            context.startActivity(
                                Intent(
                                    context,
                                    CapsuleActivity::class.java
                                )
                            )
                        }
                        .background(main), contentAlignment = Alignment.Center) {
                        Row {
                            Text(text = "Start Capsule", color = Color.White, fontWeight = FontWeight.Medium, fontSize = 20.sp)
                        }
                    }
                }
            }
        }
    }
}

