package com.ncs.memoneet.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.ncs.memoneet.Screens.Main.TabsContent
import com.ncs.memoneet.Screens.Quiz.quizScreen

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Navigation(
    navController: NavHostController,pagerState: PagerState){
    val context= LocalContext.current
    NavHost(navController = navController, startDestination = "Tabs" ){
        composable("Tabs"){
            TabsContent(pagerState = pagerState, navController = navController )
        }
        composable("Quiz"){
            quizScreen()
        }

    }
}