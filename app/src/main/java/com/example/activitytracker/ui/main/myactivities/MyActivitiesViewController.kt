package com.example.activitytracker.ui.main.myactivities

import androidx.navigation.NavController
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MyActivitiesViewController(
    private val view: MyActivitiesView,
    private val viewModel: MyActivitiesViewModel
) {

    fun onViewReady() {
        setOnActivityDataLoaded()
        setOnActivityDataLoading()
        setSwipeToRefreshListener()
        setOnFollowStateChangeListener()

        getSavedActivities()
    }

    private fun setOnFollowStateChangeListener() {
        viewModel.onFollowStateChanged =
            { button, following -> view.setFollowButtonText(button, following) }
    }

    private fun setSwipeToRefreshListener() {
        view.setOnSwipeToRefreshListener {getSavedActivities() }
    }

    private fun getSavedActivities() {
        viewModel.getSavedActivities()
    }

    private fun setOnActivityDataLoading() {
        viewModel.onActivityDataLoading = { view.showLoadingSpinner() }
    }

    private fun setOnActivityDataLoaded() {
        viewModel.onActivityDataLoaded =
            {
                view.setupRecyclerView(
                    viewModel.activityList,
                    { viewModel.navigateToSelectedItem(it) },
                    { activityData -> viewModel.setActivityFollow(activityData) }
                )
                view.hideLoadingSpinner()
            }

    }


}