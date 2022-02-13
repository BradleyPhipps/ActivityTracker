package com.example.activitytracker.ui.main.home

import kotlinx.coroutines.ExperimentalCoroutinesApi

class HomeViewController(
    private val view: HomeView,
    private val viewModel: HomeViewModel
) {
    init {
    }
    //called in the fragment once the controller has been initialised
    @ExperimentalCoroutinesApi
    fun onViewReady(){
        viewModel.onActivityDataLoaded = { displayData() }
        viewModel.onActivityDataLoading = { displayLoadingSpinner() }
        viewModel.onFollowStateChanged = { displayFollowStateChange()}

        view.setButtonClickedListener { viewModel.getActivity() }
        view.setFollowActivityClickedListener { viewModel.setActivityFollow(viewModel.activityResponse) }
        view.setActivityCardClickedListener { viewModel.onActivityCardClicked(viewModel.activityResponse) }

        viewModel.getActivity()
    }

    private fun displayData(){
        view.onDataLoaded(viewModel.activityResponse)
    }

    private fun displayLoadingSpinner(){
        view.showLoadingSpinner()
    }

    private fun displayFollowStateChange(){
        view.setFollowButtonText(viewModel.activityResponse.activityFollowed)
    }
}