package com.example.activitytracker.ui.main.home

import kotlinx.coroutines.ExperimentalCoroutinesApi

class HomeViewController(
    private val view: HomeView,
    private val viewModel: HomeViewModel
) {
    init {
    }
    //called in the fragment once the controller has been initialised - prevent race condition
    @ExperimentalCoroutinesApi
    fun onViewReady(){
        viewModel.onActivityDataLoaded = {view.onDataLoaded(viewModel.activityResponse)}
        viewModel.onActivityDataLoading = {view.showLoadingSpinner()}
        view.setButtonClickedListener { viewModel.getActivity() }
        view.setSaveActivityClickedListener { viewModel.saveActivity(viewModel.activityResponse) }
        view.setGetActivityClickedListener { viewModel.getSavedActivities() }

    }
}