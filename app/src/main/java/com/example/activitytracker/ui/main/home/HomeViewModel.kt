package com.example.activitytracker.ui.main.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activitytracker.R
import com.example.activitytracker.SavedActivityRepository
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.services.activity.ActivityService
import com.example.activitytracker.services.navigation.NavigationService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class HomeViewModel(
    private val activityService: ActivityService,
    private val savedActivityRepository: SavedActivityRepository,
    private val navService: NavigationService
) : ViewModel() {

    lateinit var activityResponse: ActivityCoreData

    var onActivityDataLoaded: (() -> Unit)? = null
    var onActivityDataLoading: (() -> Unit)? = null
    var onFollowStateChanged: ((followState:Boolean) -> Unit)? = null

    @ExperimentalCoroutinesApi
    fun getActivity() {

        onActivityDataLoading?.invoke()

        viewModelScope.launch {
            activityResponse = activityService.getRandomSingleActivity()

            onActivityDataLoaded?.invoke()
        }
    }

    fun onActivityCardClicked(activity: ActivityCoreData){
        navService.navigateToFragmentWithData(R.id.action_global_activityDetailsFragment, activity)
    }

    fun setActivityFollow(activity: ActivityCoreData){
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

        onFollowStateChanged?.invoke(!currentlyFollowing)
        Log.d("Logs: ", "Activity Saved")
    }

}
