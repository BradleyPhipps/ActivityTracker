package com.example.activitytracker.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.services.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class HomeViewModel(private val activityService: ActivityService) : ViewModel() {

    lateinit var activityResponse: ActivityCoreData

    var onArticleDataLoaded: (() -> Unit)? = null
    var onArticleDataLoading: (() -> Unit)? = null

    @ExperimentalCoroutinesApi
    fun getActivity() {

        onArticleDataLoading?.invoke()

        viewModelScope.launch {
            activityResponse = activityService.getSingleActivity()
            onArticleDataLoaded?.invoke()
        }
    }
}
