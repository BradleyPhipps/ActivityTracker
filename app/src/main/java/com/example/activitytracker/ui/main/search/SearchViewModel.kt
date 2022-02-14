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
import com.example.activitytracker.ui.main.ActivityFragmentWithRecyclerView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

typealias SelectedItemListener = (ActivityCoreData) -> Unit
typealias ItemFollowButtonClickListener = (Button, ActivityCoreData) -> Unit

class SearchViewModel (
    private val activityService: ActivityService,
    private val savedActivityRepository: SavedActivityRepository,
    private val navService: NavigationService
): ActivityFragmentWithRecyclerView(activityService,savedActivityRepository,navService){

    val numberOfSearchItems = 10

    @ExperimentalCoroutinesApi
    fun searchActivities(context: Context, searchQueryData: ActivityQueryData) {
        onActivityDataLoading?.invoke()

        viewModelScope.launch {
            activityList = activityService.getActivitiesWithParameters(10, searchQueryData, context,savedActivityRepository.getSavedActivitesKeys())
            onActivityDataLoaded?.invoke()
            dataLoaded = true
        }
    }
    }
