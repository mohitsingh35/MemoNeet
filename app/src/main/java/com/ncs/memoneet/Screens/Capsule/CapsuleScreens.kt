package com.ncs.memoneet.Screens.Capsule


import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ncs.memoneet.R
import com.ncs.memoneet.ui.theme.main


@OptIn(UnstableApi::class) @Composable
fun video(onClick: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        var isFullscreen by remember { mutableStateOf(false) }

        val url = "https://firebasestorage.googleapis.com/v0/b/nextbus-71301.appspot.com/o/Why%20do%20blood%20types%20matter_%20-%20Natalie%20S.%20Hodge.mp4?alt=media&token=f553878a-bedc-4bea-868e-ad140c689ffb&_gl=1*14ggyk7*_ga*Nzk5NTM1ODYzLjE2OTMyMzU4MjI.*_ga_CW55HF8NVT*MTY5NzkwOTQzMC4xNDUuMS4xNjk3OTA5NDU1LjM1LjAuMA.."
        val context = LocalContext.current

        val exoPlayer = remember {
            ExoPlayer.Builder(context)
                .build()
                .apply {
                    val defaultDataSourceFactory = DefaultDataSource.Factory(context)
                    val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
                        context,
                        defaultDataSourceFactory
                    )
                    val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(MediaItem.fromUri(url))

                    setMediaSource(source)
                    prepare()
                }
        }

        exoPlayer.playWhenReady = true
        exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
        exoPlayer.repeatMode = Player.REPEAT_MODE_ONE
        LazyColumn() {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top =  200.dp)
                ) {
                    DisposableEffect(
                        AndroidView(factory = {
                            PlayerView(context).apply {
                                useController = true
                                resizeMode =
                                    if (isFullscreen) AspectRatioFrameLayout.RESIZE_MODE_FILL
                                    else AspectRatioFrameLayout.RESIZE_MODE_ZOOM

                                player = exoPlayer
                                layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT , FrameLayout.LayoutParams.MATCH_PARENT
                                )
                            }
                        })
                    ) {
                        onDispose { exoPlayer.release() }
                    }
                }
            }
        }


        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
            Column {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)) {
                    Text(text = "Up Next:", color = Color.Black, fontWeight = FontWeight.Bold)
                }
                    Box(Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(16.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .clickable {
                            onClick()
                        }
                        .background(main), contentAlignment = Alignment.Center) {
                    Row {
                        androidx.compose.material3.Text(text = "Notes", color = Color.White, fontWeight = FontWeight.Medium, fontSize = 20.sp)
                        Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = Color.White)
                    }
                }
            }


        }
    }


}
@OptIn(UnstableApi::class) @Composable
fun notes(onClick: () -> Unit){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)){
            item {
                Text(
                    text = "Blood is a vital and complex bodily fluid that plays a crucial role in maintaining our overall health and well-being. It is a specialized connective tissue composed of various components, each with distinct functions. Here are some key points about blood:\n" +
                            "\n" +
                            "Composition: Blood is primarily made up of four main components - red blood cells (erythrocytes), white blood cells (leukocytes), platelets (thrombocytes), and plasma. Plasma is the liquid component of blood that carries cells, nutrients, hormones, and waste products throughout the body.\n" +
                            "\n" +
                            "Red Blood Cells (RBCs): These are responsible for transporting oxygen from the lungs to the body's tissues and organs. Their red color comes from the iron-containing protein hemoglobin.\n" +
                            "\n" +
                            "White Blood Cells (WBCs): WBCs are a part of the immune system and help the body fight off infections and diseases. They come in various types, each with a specific role in immune defense.\n" +
                            "\n" +
                            "Platelets: Platelets are tiny cell fragments that play a crucial role in blood clotting. They help stop bleeding when blood vessels are damaged by forming a plug at the injury site.\n" +
                            "\n" +
                            "Functions: Blood serves several critical functions, including transporting oxygen and nutrients to cells, removing waste products and carbon dioxide, maintaining body temperature, and regulating the body's pH and electrolyte balance. It also plays a role in the immune response and clotting to prevent excessive bleeding.\n" +
                            "\n" +
                            "Circulation: The heart pumps blood throughout the body via a network of blood vessels, including arteries (carry oxygenated blood away from the heart), veins (return deoxygenated blood to the heart), and capillaries (tiny vessels where nutrient and gas exchange occurs at the cellular level).\n" +
                            "\n" +
                            "Blood Types: Human blood is classified into different blood groups, primarily based on the presence or absence of specific antigens, such as the ABO system (A, B, AB, O) and the Rh factor (+ or -). Blood type compatibility is crucial for safe blood transfusions.\n" +
                            "\n" +
                            "Donation: Donating blood is a life-saving act that helps replenish blood supplies for medical procedures, emergencies, and individuals with certain medical conditions. Blood banks and organizations rely on voluntary donations to ensure a steady and safe blood supply.\n" +
                            "\n" +
                            "Blood Disorders: Various medical conditions can affect blood, including anemia (a shortage of red blood cells), leukemia (cancer of the blood-forming tissues), and hemophilia (a genetic disorder affecting blood clotting). These conditions require medical attention and, in some cases, specialized treatments.\n" +
                            "\n" +
                            "In summary, blood is a remarkable fluid that sustains life by delivering essential oxygen and nutrients to cells, removing waste products, and playing a crucial role in immune defense and clotting. Understanding the composition and functions of blood is fundamental to appreciating its significance in maintaining overall health."
                , fontSize = 15.sp, fontWeight = FontWeight.Light, textAlign = TextAlign.Justify, color = Color.Black
                )

            }
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
            Column {
                Box(Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .clickable {
                        onClick()
                    }
                    .background(main), contentAlignment = Alignment.Center) {
                    Row {
                        androidx.compose.material3.Text(text = "Quiz", color = Color.White, fontWeight = FontWeight.Medium, fontSize = 20.sp)
                        Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = Color.White)
                    }
                }
            }
        }
    }
}
@Composable
fun quiz(navController: NavController){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        LazyColumn(
            Modifier
                .fillMaxWidth()){
            items(1) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Take the quiz \n and test your learning",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                }
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    takequiz()
                }
            }
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
            Column {
                Box(Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .clickable {
                        navController.navigate("Quiz")
                    }
                    .background(main), contentAlignment = Alignment.Center) {
                    Row {
                        androidx.compose.material3.Text(text = "Start Quiz", color = Color.White, fontWeight = FontWeight.Medium, fontSize = 20.sp)
                        Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = Color.White)
                    }
                }
            }
        }
    }
}
@Composable
fun takequiz() {
    var isPlaying by remember { mutableStateOf(true) }
    var speed by remember { mutableStateOf(1f) }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.takequiz)
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