package com.example.activitytracker.ui.main.search

class SearchViewController(
    private val view: SearchView,
    private val viewModel: SearchViewModel
) {

    fun onViewReady(){
        setOnSearchDataLoaded()
    }

    private fun setOnSearchDataLoaded(){
        view.setSearchClickListener { viewModel.searchActivities(view.context) }
    }


}