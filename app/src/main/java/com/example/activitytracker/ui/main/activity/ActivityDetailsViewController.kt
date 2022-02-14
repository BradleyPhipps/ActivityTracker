package com.example.activitytracker.ui.main.activity

import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.ui.main.home.HomeView
import com.example.activitytracker.ui.main.home.HomeViewModel

class ActivityDetailsViewController(
    private val view: ActivityDetailsView,
    private val viewModel: ActivityDetailsViewModel
) {

    fun onViewReady(){
        viewModel.convertDataStringToActivity(viewModel.activityDataString)
        setOnSeekbarChangeListener()
        setOnFollowClickListener()
        setViewText()

    }

    private fun setOnSeekbarChangeListener(){
        view.setProgressOnChangeListener { progress -> onSeekbarChange(progress) }
    }

    private fun onSeekbarChange(progress: Int?){
        viewModel.updateActivityProgress(progress)
        view.setActivityProgress(progress)
    }

    private fun setOnFollowClickListener(){
        view.setFollowButtonOnClickListener { changeFollowState(viewModel.activityCoreData.activityFollowed) }
    }

    private fun changeFollowState(activityFollowed: Boolean){
        when(activityFollowed){
            true -> {
                viewModel.unfollowActivity(viewModel.activityCoreData)
                view.setFollowButtonText(viewModel.activityCoreData.activityFollowed)
            }
            false -> {
                viewModel.followActivity(viewModel.activityCoreData)
                view.setFollowButtonText(viewModel.activityCoreData.activityFollowed)
            }
        }
    }

    private fun setViewText(){
        view.setActivityTitle(viewModel.activityCoreData.activityTitle)
        view.setActivityAccessibility(viewModel.activityCoreData.activityAccessibility)
        view.setActivityPrice(viewModel.activityCoreData.activityPrice)
        view.setActivityParticipants(viewModel.activityCoreData.activityNumberParticipants.toString())
        view.setActivityBannerImage(viewModel.activityCoreData.activityType)
        view.setFollowButtonText(viewModel.activityCoreData.activityFollowed)
        view.setActivityProgress(viewModel.activityCoreData.activityProgress)

        if(viewModel.activityCoreData.activityLink != ""){
            view.setLinkText(viewModel.activityCoreData.activityLink.toString())
            view.displayLinkItems()
        }
    }
}