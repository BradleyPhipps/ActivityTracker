package com.example.activitytracker.ui.main.home

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.activitytracker.R
import com.example.activitytracker.databinding.HomeFragmentBinding
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.services.ActivityCardBuilder

class HomeView(private val view: HomeFragmentBinding) {

    fun setButtonClickedListener(listener: () -> Unit){
        view.buttonGenerateActivity.setOnClickListener() {
            listener.invoke()
        }
    }

    fun setActivityCardClickedListener(listener: () -> Unit){
        view.activityCard.root.setOnClickListener() {
            listener.invoke()
        }
    }

    fun setFollowActivityClickedListener(listener: () -> Unit){
        view.activityCard.saveActivity.setOnClickListener() {
            listener.invoke()
        }
    }

    fun showLoadingSpinner(){
        view.LoadingSpinner.visibility = View.VISIBLE
    }

    fun hideLoadingSpinner(){
        view.LoadingSpinner.visibility = View.GONE
    }

    private fun displayCard(activityData: ActivityCoreData){//, onClick: ()-> Unit
        ActivityCardBuilder(view.activityCard.root).buildCard(activityData)
        view.ActivityContainer.visibility = View.VISIBLE
    }

    fun setFollowButtonText(followingActivity: Boolean){
        when(followingActivity){
            true -> view.activityCard.saveActivity.setText(R.string.card_unfollowText)
            false -> view.activityCard.saveActivity.setText(R.string.card_followText)
        }

    }

    fun onDataLoaded(activityData: ActivityCoreData){
        hideLoadingSpinner()
        displayCard(activityData)
    }
}