package com.example.activitytracker.ui.main.myactivities

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.activitytracker.SavedActivityRepository
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.services.ActivityService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

typealias SelectedItemListener = (ActivityCoreData) -> Unit
typealias ItemFollowButtonClickListener = (ActivityCoreData) -> Unit

class MyActivitiesViewModel(
    private val activityService: ActivityService,
    private val savedActivityRepository: SavedActivityRepository,
    private val navController: NavController
    ) : ViewModel(){

    lateinit var activityList: List<ActivityCoreData>
    var dataLoaded = false

    var onActivityDataLoaded: (() -> Unit)? = null
    var onActivityDataLoading: (() -> Unit)? = null

    fun onItemSelected(activityData: ActivityCoreData){
        Log.d("Logs: ", activityData.activityTitle)
    }

    fun onFollowClick(activityData: ActivityCoreData){
        Log.d("Logs: ", activityData.activityId)
    }

    @ExperimentalCoroutinesApi
    fun getSavedActivities() {

        onActivityDataLoading?.invoke()

        viewModelScope.launch {
            activityList = activityService.getSavedActivities(savedActivityRepository.getSavedActivites().toList())
            onActivityDataLoaded?.invoke()
            dataLoaded = true
        }
    }

}