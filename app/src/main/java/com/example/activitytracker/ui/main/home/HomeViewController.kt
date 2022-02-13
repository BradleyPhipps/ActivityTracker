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
        viewModel.onActivityDataLoaded = {view.onDataLoaded(viewModel.activityResponse)}
        viewModel.onActivityDataLoading = {view.showLoadingSpinner()}
        viewModel.onFollowStateChanged = {view.setFollowButtonText(viewModel.activityResponse.activityFollowed)}

        view.setButtonClickedListener { viewModel.getActivity() }
        view.setFollowActivityClickedListener { viewModel.setActivityFollow(viewModel.activityResponse) }
        view.setActivityCardClickedListener { viewModel.onActivityCardClicked(viewModel.activityResponse) }

        viewModel.getActivity()
    }
}