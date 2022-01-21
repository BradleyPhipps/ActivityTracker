package com.example.activitytracker.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activitytracker.models.ActivityResponse
import com.example.activitytracker.services.JsonService
import com.example.activitytracker.services.StatsService
import com.example.activitytracker.services.data.DataService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class MainViewModel(private val dataService: DataService,
                    private val jsonService: JsonService,
                    private val statsService: StatsService
) : ViewModel() {

    lateinit var activityResponse: ActivityResponse

    var dataLoaded = false
    var onArticleDataLoaded : (() -> Unit)? = null

    @ExperimentalCoroutinesApi
    fun getArticleData(){
        viewModelScope.launch {

            val response = dataService.getNetworkResponse("https://www.boredapi.com/api/activity/")
            Log.d("Logs: ",response)

            activityResponse = jsonService.convertToJson(response)
            dataLoaded = true
            Log.d("Logs: ",activityResponse.activity)
            //statsService.sendStats("https://bbc.github.io/sport-app-dev-tech-challenge/stats?event=load&data=${System.currentTimeMillis()}")
            //onArticleDataLoaded?.invoke()
        }
    }
}