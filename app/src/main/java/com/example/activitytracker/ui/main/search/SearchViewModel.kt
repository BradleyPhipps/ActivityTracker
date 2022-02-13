package com.example.activitytracker.ui.main.search

import android.content.Context
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.models.ActivityQueryData
import com.example.activitytracker.services.ActivityService
import com.example.activitytracker.services.NavigationService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class SearchViewModel (
    private val activityService: ActivityService,
    private val navService: NavigationService
): ViewModel() {

    lateinit var activityList: List<ActivityCoreData>
    var dataLoaded = false

    var onActivityDataLoaded: (() -> Unit)? = null
    var onActivityDataLoading: (() -> Unit)? = null
    var onFollowStateChanged:((Button, Boolean) -> Unit)? = null


    @ExperimentalCoroutinesApi
    fun searchActivities(context:Context) {
        onActivityDataLoading?.invoke()
        var queryData = ActivityQueryData(0f, 1f, 2, 0f, 1f, "")

        viewModelScope.launch {
            activityList = activityService.getActivitiesWithParameters(5, queryData, context)
            onActivityDataLoaded?.invoke()
            dataLoaded = true
            Log.d("Logs: ", activityList.size.toString())
        }
    }
}