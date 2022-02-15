package com.example.activitytracker.ui.main.myactivities

import android.widget.Button
import androidx.lifecycle.viewModelScope
import com.example.activitytracker.services.activity.SavedActivityRepository
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.services.activity.ActivityService
import com.example.activitytracker.services.navigation.NavigationService
import com.example.activitytracker.ui.main.ActivityFragmentWithRecyclerView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

typealias SelectedItemListener = (ActivityCoreData) -> Unit
typealias ItemFollowButtonClickListener = (ActivityCoreData) -> Unit

class MyActivitiesViewModel(
    private val activityService: ActivityService,
    private val savedActivityRepository: SavedActivityRepository,
    navService: NavigationService
    ) : ActivityFragmentWithRecyclerView(activityService,savedActivityRepository,navService){

    @ExperimentalCoroutinesApi
    fun getSavedActivities() {
        onActivityDataLoading?.invoke()
        viewModelScope.launch {
            activityList = activityService.getSavedActivities(savedActivityRepository.getSavedActivites())
            onActivityDataLoaded?.invoke()
            dataLoaded = true
        }
    }

}