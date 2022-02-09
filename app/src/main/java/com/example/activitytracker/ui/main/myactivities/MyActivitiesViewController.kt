package com.example.activitytracker.ui.main.myactivities

import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MyActivitiesViewController(
    private val view: MyActivitiesView,
    private val viewModel: MyActivitiesViewModel
) {

    fun onViewReady(){
        viewModel.onActivityDataLoaded = {view.onDataLoaded(viewModel.activityList)}
        viewModel.onActivityDataLoading = {view.showLoadingSpinner()}
        view.setOnSwipeToRefreshListener { viewModel.getSavedActivities() }
        viewModel.getSavedActivities()
    }

}