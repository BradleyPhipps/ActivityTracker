package com.example.activitytracker.ui.main.myactivities

import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activitytracker.R
import com.example.activitytracker.SavedActivityRepository
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.services.activity.ActivityService
import com.example.activitytracker.services.navigation.NavigationService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

typealias SelectedItemListener = (ActivityCoreData) -> Unit
typealias ItemFollowButtonClickListener = (Button, ActivityCoreData) -> Unit

class MyActivitiesViewModel(
    private val activityService: ActivityService,
    private val savedActivityRepository: SavedActivityRepository,
    private val navService: NavigationService
    ) : ViewModel(){

    lateinit var activityList: List<ActivityCoreData>
    var dataLoaded = false

    var onActivityDataLoaded: (() -> Unit)? = null
    var onActivityDataLoading: (() -> Unit)? = null
    var onFollowStateChanged:((Button, Boolean) -> Unit)? = null

    fun navigateToSelectedItem(activityData: ActivityCoreData){
        Log.d("Logs: ", activityData.activityTitle)
        navService.navigateToFragmentWithData(R.id.action_global_activityDetailsFragment, activityData)
    }

    fun setActivityFollow(followButton:Button, activity: ActivityCoreData){
        val currentlyFollowing = activity.activityFollowed

        when(currentlyFollowing){
            true -> {
                savedActivityRepository.unfollowActivity(activity.activityId)
                activity.activityFollowed = !currentlyFollowing
            }
            false ->{
                savedActivityRepository.followActivity(activity.activityId,activity.activityProgress)
                activity.activityFollowed = !currentlyFollowing
            }
        }

        onFollowStateChanged?.invoke(followButton, !currentlyFollowing)
        Log.d("Logs: ", "Activity Saved")
    }

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