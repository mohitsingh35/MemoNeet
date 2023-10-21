package com.ncs.memoneet.Screens.Capsule

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {
    var time by mutableStateOf(10 * 60)
    var job: Job? = null

    fun startTimer() {
        if (job == null) {
            job = viewModelScope.launch {
                while (time > 0) {
                    delay(1000)
                    time--
                }
            }
        }
    }
}