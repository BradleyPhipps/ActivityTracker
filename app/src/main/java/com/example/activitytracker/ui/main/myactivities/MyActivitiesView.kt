package com.example.activitytracker.ui.main.myactivities

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitytracker.databinding.MyActivitiesFragmentBinding
import com.example.activitytracker.models.ActivityCoreData

class MyActivitiesView (private val view: MyActivitiesFragmentBinding) {


    fun setOnSwipeToRefreshListener(listener: () -> Unit){
        view.refreshLayout.setOnRefreshListener() {
            listener.invoke()
        }
    }

    fun showLoadingSpinner(){
        view.LoadingSpinner.visibility = View.VISIBLE
    }

    fun hideLoadingSpinner(){
        view.LoadingSpinner.visibility = View.GONE
    }

    fun onDataLoaded(activityList: List<ActivityCoreData>){
        view.ActivityRecylerView.apply {
            layoutManager = LinearLayoutManager(view.root.context, LinearLayoutManager.VERTICAL, false)
            adapter = ActivityAdapter(activityList)
        }

        if(view.refreshLayout.isRefreshing) view.refreshLayout.isRefreshing = false

        hideLoadingSpinner()
    }
}