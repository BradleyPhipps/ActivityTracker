package com.example.activitytracker.ui.main.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activitytracker.SavedActivityRepository
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.services.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class HomeViewModel(
    private val activityService: ActivityService,
    private val savedActivityRepository: SavedActivityRepository
) : ViewModel() {

    lateinit var activityResponse: ActivityCoreData

    var onActivityDataLoaded: (() -> Unit)? = null
    var onActivityDataLoading: (() -> Unit)? = null

    @ExperimentalCoroutinesApi
    fun getActivity() {

        onActivityDataLoading?.invoke()

        viewModelScope.launch {
            activityResponse = activityService.getRandomSingleActivity()

            onActivityDataLoaded?.invoke()
        }
    }

    fun saveActivity(activity: ActivityCoreData){
        savedActivityRepository.saveActivity(activity.activityId,activity.activityTitle)
        Log.d("Logs: ", "Activity Saved")
    }

    @ExperimentalCoroutinesApi
    fun getSavedActivities() {
        viewModelScope.launch {
            val savedActivities = activityService.getSavedActivities(savedActivityRepository.getSavedActivites().toList())
            for(activity in savedActivities){
                Log.d("Logs: ", activity.activityTitle)
            }

            //onArticleDataLoaded?.invoke()
        }
    }
}
