package com.example.activitytracker.ui.main.myactivities

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitytracker.databinding.MyActivitiesFragmentBinding
import com.example.activitytracker.models.ActivityCoreData

class MyActivitiesView (private val view: MyActivitiesFragmentBinding): ActivityAdapter.OnItemClickListener{

    fun setOnSwipeToRefreshListener(listener: () -> Unit){
        view.refreshLayout.setOnRefreshListener() {
            listener.invoke()
        }
    }
    override fun onActivityCardClicked(position: Int) {
        Toast.makeText(view.root.context, "Item $position", Toast.LENGTH_SHORT).show()
    }

    fun showLoadingSpinner(){
        view.LoadingSpinner.visibility = View.VISIBLE
    }

    fun hideLoadingSpinner(){
        view.LoadingSpinner.visibility = View.GONE
    }

    fun onDataLoaded(activityList: List<ActivityCoreData>){

        var activityAdapter =ActivityAdapter(activityList, this)
        view.ActivityRecylerView.apply {
            layoutManager = LinearLayoutManager(view.root.context, LinearLayoutManager.VERTICAL, false)
            adapter = activityAdapter
        }
        activityAdapter.setOnItemClickListener(object: ActivityAdapter.OnItemClickListener {
            override fun onItemClick(position: Int){

            }
        })
        if(view.refreshLayout.isRefreshing) view.refreshLayout.isRefreshing = false

        hideLoadingSpinner()
    }
}