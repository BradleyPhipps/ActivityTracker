package com.example.activitytracker.ui.main.myactivities

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitytracker.databinding.MyActivitiesFragmentBinding
import com.example.activitytracker.models.ActivityCoreData

class MyActivitiesView (private val view: MyActivitiesFragmentBinding){

     fun setOnSwipeToRefreshListener(listener: () -> Unit){
        view.refreshLayout.setOnRefreshListener() {
            listener.invoke()
        }
    }
     fun onActivityCardClicked(activitySelected: ActivityCoreData) {
        Toast.makeText(view.root.context, activitySelected.activityTitle, Toast.LENGTH_SHORT).show()
         //Navigation.findNavController().navigate(R.id.action_global_activityDetailsFragment)
    }

    fun showLoadingSpinner(){
        view.LoadingSpinner.visibility = View.VISIBLE
    }

    fun hideLoadingSpinner(){
        view.LoadingSpinner.visibility = View.GONE
    }

    fun onDataLoaded(activityList: List<ActivityCoreData>){
        val activityAdapter =ActivityAdapter(activityList){
            onActivityCardClicked(it)
        }

        view.ActivityRecylerView.apply {
            layoutManager = LinearLayoutManager(view.root.context, LinearLayoutManager.VERTICAL, false)
            adapter = activityAdapter
        }

        if(view.refreshLayout.isRefreshing) view.refreshLayout.isRefreshing = false
        hideLoadingSpinner()
    }
}