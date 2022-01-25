package com.example.activitytracker.ui.main.home

class HomeViewController(
    private val view: HomeView,
    private val viewModel: HomeViewModel
) {
    init {
    }
    //called in the fragment once the controller has been initialised - prevent race condition
    fun onViewReady(){
        viewModel.onArticleDataLoaded = {view.onDataLoaded(viewModel.activityResponse)}
        viewModel.onArticleDataLoading = {view.showLoadingSpinner()}
        view.setButtonClickedListener { viewModel.getActivity() }
    }
}