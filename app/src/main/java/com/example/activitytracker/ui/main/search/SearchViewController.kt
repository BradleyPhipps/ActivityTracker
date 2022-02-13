package com.example.activitytracker.ui.main.search

import android.util.Log
import kotlin.math.max

class SearchViewController(
    private val view: SearchView,
    private val viewModel: SearchViewModel
) {

    fun onViewReady(){

        setSearchButtonClickListener()
        setOnActivityDataLoading()
        setOnSearchDataLoaded()
        setOnFollowStateChanged()
    }


    private fun setSearchButtonClickListener(){
        view.setSearchClickListener{ maxAccessibility ->runSearch(maxAccessibility) }
    }

    private fun setOnFollowStateChanged(){
            viewModel.onFollowStateChanged =
                { button, following -> view.setFollowButtonText(button, following) }
    }



    private fun setOnSearchDataLoaded() {
        viewModel.onActivityDataLoaded =
            {
                view.setupRecyclerView(
                    viewModel.activityList,
                    { viewModel.onItemSelected(it) },
                    { button, activityData -> viewModel.setActivityFollow(button, activityData) }
                )
                view.hideLoadingSpinner()

            }
    }

    private fun setOnActivityDataLoading() {
        viewModel.onActivityDataLoading = { view.showLoadingSpinner() }
    }

    private fun runSearch(maxAccessibility: Float){
        viewModel.setSearchQuery(0f,maxAccessibility,1,0f,1f,"")
        viewModel.searchActivities(view.context)
    }


}