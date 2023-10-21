package com.ncs.memoneet.Screens.Quiz

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ncs.memoneet.Data.Questions
import com.ncs.memoneet.R
import com.ncs.memoneet.Screens.Main.MainActivity
import com.ncs.memoneet.ui.theme.main
import com.ncs.memoneet.ui.theme.ques
import com.ncs.memoneet.ui.theme.secondary

@Composable
fun quizScreen(){
    val list= listOf(
        Questions(ques="What is the primary function of red blood cells (erythrocytes)?",
        listOf("Oxygen transport","Immune defense","Blood clotting","Waste removal"),"Oxygen transport"),
        Questions(ques="The liquid component of blood that carries cells, nutrients, hormones, and waste products is called:",
            listOf("Red blood cells","White blood cells","Platelets","Plasma"),"Plasma"),
        Questions(ques="What is the primary function of red blood cells (erythrocytes)?",
            listOf("Oxygen transport","Immune defense","Blood clotting","Waste removal"),"Oxygen transport"),
        Questions(ques="The liquid component of blood that carries cells, nutrients, hormones, and waste products is called:",
            listOf("Red blood cells","White blood cells","Platelets","Plasma"),"Plasma"),
        Questions(ques="What is the primary function of red blood cells (erythrocytes)?",
            listOf("Oxygen transport","Immune defense","Blood clotting","Waste removal"),"Oxygen transport"),
    )
    var checkAnswer by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var selectedOptionIndex by rememberSaveable { mutableStateOf(-1) }
    var showdialog by rememberSaveable { mutableStateOf(false) }
    var showresult by rememberSaveable { mutableStateOf(false) }
    var score by rememberSaveable { mutableStateOf(0) }
    var count by rememberSaveable { mutableStateOf(0) }
    if (showresult){
        showResult(score = score)
    }
    if (selectedOptionIndex != -1) {
        if (list[count].options[selectedOptionIndex] == list[count].answer) {
            showAlert(isCorrect = true, answer = list[count].answer, showAlert = showdialog, nextQues = {
                score += 10
                if (count != list.size - 1) {
                    count++
                    showdialog = false
                    selectedOptionIndex = -1
                } else {
                    val allCorrect = count == list.size - 1
                    if (allCorrect) {
                        showresult = true
                    }
                }
            })
        } else {
            showAlert(isCorrect = false, answer = list[count].answer, showAlert = showdialog, nextQues = {
                if (count != list.size - 1) {
                    count++
                    showdialog = false
                    selectedOptionIndex = -1
                } else {
                    val allCorrect = count == list.size - 1
                    if (allCorrect) {
                        showresult = true
                    }
                }
            })
        }
    }



    Column (
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)){
        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Box(modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(secondary), contentAlignment = Alignment.Center){
                Text(text = "Question ${count+1} / ${list.size}", color = Color.White, fontWeight = FontWeight.ExtraBold)
            }
            Spacer(modifier = Modifier.width(15.dp))
            Row (modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(secondary), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround){
                Icon(imageVector = Icons.Filled.List, contentDescription ="" , tint = Color.White)
                Spacer(modifier = Modifier
                    .width(1.dp)
                    .height(25.dp)
                    .background(Color.White))
                Icon(imageVector = Icons.Filled.Warning, contentDescription ="" , tint = Color.White)

            }
        }
        Spacer(modifier = Modifier.height(25.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        2.dp, color = Color.Black,
                        RoundedCornerShape(10.dp)
                    )
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Q${count+1}. ${list[count].ques}", color = Color.Black, fontSize = 18.sp)
                }
            }
        Spacer(modifier = Modifier.height(25.dp))

        for (j in 0 until list[count].options.size) {
            eachoption(
                options = list[count].options[j],
                onClick = {
                    selectedOptionIndex = j
                },
                isSelected =  selectedOptionIndex==j
            )

        }
        }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
        Row(Modifier.fillMaxWidth()) {
            Box(Modifier
                .fillMaxWidth(0.8f)
                .height(85.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    if (selectedOptionIndex != -1) {
                        checkAnswer = true
                        showdialog = true

                    } else {
                        Toast
                            .makeText(context, "Please Select a Option", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                .background(main), contentAlignment = Alignment.Center) {
                Row {
                    androidx.compose.material3.Text(text = "Check Answer", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 16.sp)
                }
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(53.dp)
                        .clickable {
                            if (count != list.size - 1) {
                                selectedOptionIndex = -1
                                checkAnswer = false
                                count++
                                showdialog = false
                            }
                        }
                        .clip(RoundedCornerShape(10.dp))
                        .background(main), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "",
                        tint = Color.White,
                    )
                }
            }
        }
    }


}

@Composable
fun eachoption(options: String, onClick: () -> Unit, isSelected: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(bottom = 15.dp)
            .clickable {
                onClick()
            }
            .clip(RoundedCornerShape(10.dp))
            .background(if (isSelected) main else ques),
        contentAlignment = Alignment.Center
    ) {
        Text(text = options, color = Color.Black, fontSize = 18.sp)
    }
}
@Composable
fun showAlert(isCorrect:Boolean,answer:String,nextQues:()->Unit,showAlert:Boolean) {
    var showDialog by remember { mutableStateOf(false) }

    if (showAlert) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = { },
            text = {
                if (isCorrect) {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "God Job! The answer is correct",
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
                                nextQues()
                            }
                            .background(main), contentAlignment = Alignment.Center) {
                            Row {
                                androidx.compose.material3.Text(
                                    text = "Next",
                                    color = Color.White,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp
                                )
                            }
                        }

                    }
                } else {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Oh no! The answer is wrong",
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "Correct Answer is: $answer",
                            color = Color.Black,
                            fontWeight = FontWeight.Light,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(Modifier
                            .fillMaxWidth()
                            .height(85.dp)
                            .padding(16.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                nextQues()
                            }
                            .background(main), contentAlignment = Alignment.Center) {
                            Row {
                                androidx.compose.material3.Text(
                                    text = "Next",
                                    color = Color.White,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {

            }
        )
    }
}
@Composable
fun showResult(score:Int) {
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
                    Text(
                        "Quiz Completed",
                        color = Color.Black,
                        fontWeight = FontWeight.Light,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    done()
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "Your Score is: $score ",
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
                            androidx.compose.material3.Text(
                                text = "Return to Home",
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

@Composable
fun done() {
    var isPlaying by remember { mutableStateOf(true) }
    var speed by remember { mutableStateOf(1f) }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.done)
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