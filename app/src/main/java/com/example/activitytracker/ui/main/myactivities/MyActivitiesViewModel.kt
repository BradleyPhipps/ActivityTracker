package com.example.activitytracker.ui.main.myactivities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.activitytracker.SavedActivityRepository
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.services.ActivityService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class MyActivitiesViewModel(
    private val activityService: ActivityService,
    private val savedActivityRepository: SavedActivityRepository,
    private val navController: NavController
    ) : ViewModel(){

    lateinit var activityList: List<ActivityCoreData>

    var onActivityDataLoaded: (() -> Unit)? = null
    var onActivityDataLoading: (() -> Unit)? = null

    @ExperimentalCoroutinesApi
    fun getSavedActivities() {

        onActivityDataLoading?.invoke()

        viewModelScope.launch {
            activityList = activityService.getSavedActivities(savedActivityRepository.getSavedActivites().toList())
            onActivityDataLoaded?.invoke()
        }
    }

}