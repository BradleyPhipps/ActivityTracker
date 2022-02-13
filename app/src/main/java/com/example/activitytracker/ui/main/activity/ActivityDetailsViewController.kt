package com.example.activitytracker.ui.main.activity

import com.example.activitytracker.ui.main.home.HomeView
import com.example.activitytracker.ui.main.home.HomeViewModel

class ActivityDetailsViewController(
    private val view: ActivityDetailsView,
    private val viewModel: ActivityDetailsViewModel
) {

    fun onViewReady(){
        viewModel.convertDataStringToActivity()
        setViewText()
    }

    private fun setViewText(){
        view.setActivityTitle(viewModel.activityCoreData.activityTitle)
        view.setActivityAccessibility(viewModel.activityCoreData.activityAccessibility.toString())
        view.setActivityPrice(viewModel.activityCoreData.activityPrice)
        view.setActivityParticipants(viewModel.activityCoreData.activityNumberParticipants.toString())
        view.setActivityBannerImage(viewModel.activityCoreData.activityType)
        view.setFollowButtonText(viewModel.activityCoreData.activityFollowed)
    }
}