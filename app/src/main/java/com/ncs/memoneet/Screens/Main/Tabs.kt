package com.ncs.memoneet.Screens.Main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.ncs.memoneet.Screens.Capsule.notes
import com.ncs.memoneet.Screens.Capsule.quiz
import com.ncs.memoneet.Screens.Capsule.video
import com.ncs.memoneet.ui.theme.main
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {

    val list = listOf(
        "Video","Notes","Quiz")

    val scope = rememberCoroutineScope()
    Column {


        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.White,
            contentColor = Color.Black,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                        .clip(
                            RoundedCornerShape(10.dp)
                        ),
                    height = 5.dp,
                    color = main,
                )
            }
        ) {
            list.forEachIndexed { index, _ ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.height(50.dp)
                    ) {
                        Text(
                            text = list[index],
                            color = if (pagerState.currentPage == index) Color.Black else Color.Gray,
                            fontWeight = if (pagerState.currentPage == index) FontWeight.Bold else FontWeight.Thin
                        )
                    }
                }

            }
        }
        Box(modifier = Modifier.padding(start = 10.dp, end = 10.dp)){
        }
    }
}
@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState,navController: NavController) {
    val scope= rememberCoroutineScope()
    Column {
        Tabs(pagerState = pagerState)
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> video() {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }

                1 -> notes() {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }

                2 -> quiz(navController)
            }
        }
    }
}
