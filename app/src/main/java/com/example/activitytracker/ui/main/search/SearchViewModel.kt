package com.example.activitytracker.ui.main.search

import android.content.Context
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activitytracker.R
import com.example.activitytracker.SavedActivityRepository
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.models.ActivityQueryData
import com.example.activitytracker.services.activity.ActivityService
import com.example.activitytracker.services.navigation.NavigationService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

typealias SelectedItemListener = (ActivityCoreData) -> Unit
typealias ItemFollowButtonClickListener = (Button, ActivityCoreData) -> Unit

class SearchViewModel (
    private val activityService: ActivityService,
    private val savedActivityRepository: SavedActivityRepository,
    private val navService: NavigationService
): ViewModel() {

    lateinit var activityList: List<ActivityCoreData>
    var dataLoaded = false

    var onActivityDataLoaded: (() -> Unit)? = null
    var onActivityDataLoading: (() -> Unit)? = null
    var onFollowStateChanged: ((Button, Boolean) -> Unit)? = null


    @ExperimentalCoroutinesApi
    fun searchActivities(context: Context, searchQueryData: ActivityQueryData) {
        onActivityDataLoading?.invoke()

        viewModelScope.launch {
            activityList = activityService.getActivitiesWithParameters(5, searchQueryData, context,savedActivityRepository.getSavedActivitesKeys())
            onActivityDataLoaded?.invoke()
            dataLoaded = true
        }
    }

    fun setActivityFollow(followButton: Button, activity: ActivityCoreData) {
        val currentlyFollowing = activity.activityFollowed

        when (currentlyFollowing) {
            true -> {
                savedActivityRepository.unfollowActivity(activity.activityId)
                activity.activityFollowed = !currentlyFollowing
            }
            false -> {
                savedActivityRepository.followActivity(activity.activityId, activity.activityProgress)
                activity.activityFollowed = !currentlyFollowing
            }
        }

        onFollowStateChanged?.invoke(followButton, !currentlyFollowing)
    }

    fun navigateToSelectedItem(activityData: ActivityCoreData){
        navService.navigateToFragmentWithData(R.id.action_global_activityDetailsFragment, activityData)
    }

    }
