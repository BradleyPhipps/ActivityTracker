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
import com.example.activitytracker.services.ActivityService
import com.example.activitytracker.services.NavigationService
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
    var activityQuery = ActivityQueryData(0f, 1f, 0, 0f, 1f, "")
    var dataLoaded = false

    var onActivityDataLoaded: (() -> Unit)? = null
    var onActivityDataLoading: (() -> Unit)? = null
    var onFollowStateChanged: ((Button, Boolean) -> Unit)? = null


    @ExperimentalCoroutinesApi
    fun searchActivities(context: Context) {
        onActivityDataLoading?.invoke()

        viewModelScope.launch {
            activityList = activityService.getActivitiesWithParameters(5, activityQuery, context)
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
        fun onItemSelected(activityData: ActivityCoreData) {
            Log.d("Logs: ", activityData.activityAccessibility.toString())
            //   navService.navigateToFragmentWithData(R.id.action_global_activityDetailsFragment, activityData)
        }

        fun setSearchQuery(
            minAccessibility: Float,
            maxAccessibility: Float,
            participants: Int,
            minPrice: Float,
            maxPrice: Float,
            activityType: String
        ) {
            activityQuery = ActivityQueryData(
                minAccessibility,
                maxAccessibility,
                participants,
                minPrice,
                maxPrice,
                activityType
            )
        }
    }
