package com.example.activitytracker.ui.main.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activitytracker.models.ActivityResponse
import com.example.activitytracker.services.JsonService
import com.example.activitytracker.services.StatsService
import com.example.activitytracker.services.NetworkService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class HomeViewModel(private val networkService: NetworkService,
                    private val jsonService: JsonService,
                    private val statsService: StatsService
) : ViewModel() {

    lateinit var activityResponse: ActivityResponse

    var dataLoaded = false
    var onArticleDataLoaded : (() -> Unit)? = null
    var onArticleDataLoading: (() -> Unit)? = null

    @ExperimentalCoroutinesApi
    fun getActivity(){
        Log.d("Logs: ", "activity")
        onArticleDataLoading?.invoke()
        viewModelScope.launch {
            //Extract this to activity data handler
            val response = networkService.getNetworkResponse("https://www.boredapi.com/api/activity/")
            activityResponse = jsonService.convertToJson(response)
            dataLoaded = true

            onArticleDataLoaded?.invoke()
        }
    }
}