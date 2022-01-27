package com.example.activitytracker.ui.main.home

import android.util.Log
import android.view.View
import com.example.activitytracker.databinding.HomeFragmentBinding
import com.example.activitytracker.models.ActivityCoreData

class HomeView(private val view: HomeFragmentBinding) {

    fun setButtonClickedListener(listener: () -> Unit){
        view.buttonGenerateActivity.setOnClickListener() {
            listener.invoke()
        }
    }

    fun setActivityText(textToSet: String){
        view.tvActivityName.text = textToSet
    }

    fun showLoadingSpinner(){
        view.LoadingSpinner.visibility = View.VISIBLE
    }

    fun hideLoadingSpinner(){
        view.LoadingSpinner.visibility = View.GONE
    }

    fun onDataLoaded(activityData: ActivityCoreData){
        setActivityText(activityData.activityTitle)
        hideLoadingSpinner()
    }
}