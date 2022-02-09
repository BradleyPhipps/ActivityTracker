package com.example.activitytracker.ui.main.home

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.activitytracker.R
import com.example.activitytracker.databinding.HomeFragmentBinding
import com.example.activitytracker.models.ActivityCoreData

class HomeView(private val view: HomeFragmentBinding) {

    fun setButtonClickedListener(listener: () -> Unit){
        view.buttonGenerateActivity.setOnClickListener() {
            listener.invoke()
        }
    }

    fun setSaveActivityClickedListener(listener: () -> Unit){
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

    fun DisplayCard(activityData: ActivityCoreData){//, onClick: ()-> Unit
        view.activityCard.activityTitle.text = activityData.activityTitle
        view.activityCard.activityCategory.text = activityData.activityType
        view.activityCard.activityPrice.text = activityData.activityPrice.toString()
        view.ActivityContainer.visibility = View.VISIBLE
    }

    fun onDataLoaded(activityData: ActivityCoreData){
        //setActivityText(activityData.activityTitle)
        hideLoadingSpinner()
        DisplayCard(activityData)
    }
}