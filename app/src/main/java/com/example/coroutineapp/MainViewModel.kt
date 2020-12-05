package com.example.coroutineapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class MainViewModel: ViewModel() {
    suspend fun getData(): String{
        delay(2000)
        return "One"
    }

    suspend fun getDataTwo(data: String): String{
        delay(4000)
        return "Data $data and Two Gathered!"
    }

    suspend fun getDataThree(data: String): String{
        delay(5000)
        return "$data And finished!"
    }
}