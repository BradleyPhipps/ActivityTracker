package com.example.activitytracker.ui.main.search

import com.example.activitytracker.models.ActivityQueryAccessibility
import com.example.activitytracker.models.ActivityQueryData
import com.example.activitytracker.models.ActivityQueryPrice

class SearchViewController(
    private val view: SearchView,
    private val viewModel: SearchViewModel
) {

    fun onViewReady(){
        setSearchButtonClickListener()
        setOnActivityDataLoading()
        setOnSearchDataLoaded()
        setOnFollowStateChanged()
        populateSpinners()
    }


    private fun setSearchButtonClickListener(){
        view.setSearchClickListener{ runSearch(convertSpinnersToQueryData()) }
    }

    private fun setOnFollowStateChanged(){
            viewModel.onFollowStateChanged =
                { button, following -> view.setFollowButtonText(button, following) }
    }

    private fun populateSpinners(){
        view.populateAccessibilitySpinner()
        view.populatePriceSpinner()
        view.populateTypeSpinner()
    }

    private fun setOnSearchDataLoaded() {
        viewModel.onActivityDataLoaded =
            {
                view.setupRecyclerView(
                        viewModel.activityList,
                        { viewModel.navigateToSelectedItem(it) },
                        {  activityData ->
                            viewModel.setActivityFollow(
                                activityData
                            )
                        }
                    )
                    view.hideLoadingSpinner()
                }
    }

    private fun setOnActivityDataLoading() {
        viewModel.onActivityDataLoading = { view.showLoadingSpinner() }
    }

    private fun convertSpinnersToQueryData(): ActivityQueryData{
        return ActivityQueryData(convertAccessibilitySpinner(view.accessibility), 0,convertPriceSpinner(view.priceString),view.activityType)
    }

    private fun convertPriceSpinner(priceValue: String): ActivityQueryPrice{
        return when(priceValue){
            "" -> ActivityQueryPrice(0f,-1f)
            "FREE" -> ActivityQueryPrice(0f,0f)
            "£" -> ActivityQueryPrice(0.01f,0.3f)
            "££" -> ActivityQueryPrice(0.31f,0.6f)
            "£££" -> ActivityQueryPrice(0.61f , 1f)
            else -> ActivityQueryPrice(0f,1f)
        }
    }

    private fun convertAccessibilitySpinner(acessibilityValue: String): ActivityQueryAccessibility {
        return when (acessibilityValue) {
            "" -> ActivityQueryAccessibility(0f,-1f)
            "Very Easy" -> ActivityQueryAccessibility(0f, 0.1f)
            "Easy" -> ActivityQueryAccessibility(0.11f, 0.3f)
            "Difficult" -> ActivityQueryAccessibility(0.31f, 0.8f)
            "Very Difficult" -> ActivityQueryAccessibility(0.81f, 1f)
            else -> ActivityQueryAccessibility(0f, -1f)
        }
    }

    private fun runSearch(activityQueryData: ActivityQueryData){
        viewModel.searchActivities(activityQueryData)
    }
}