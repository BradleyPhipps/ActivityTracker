package com.example.activitytracker.ui.main.myactivities

import androidx.navigation.NavController
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MyActivitiesViewController(
    private val view: MyActivitiesView,
    private val viewModel: MyActivitiesViewModel
) {

    fun onViewReady(){
        viewModel.onActivityDataLoaded = {view.setupRecyclerView(viewModel.activityList, {viewModel.onItemSelected(it)},{viewModel.onFollowClick(it)})}
        viewModel.onActivityDataLoading = {view.showLoadingSpinner()}
        view.setOnSwipeToRefreshListener { viewModel.getSavedActivities() }

        viewModel.getSavedActivities()
    }

}