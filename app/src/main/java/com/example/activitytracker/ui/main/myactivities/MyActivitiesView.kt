package com.example.activitytracker.ui.main.myactivities

import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitytracker.R
import com.example.activitytracker.databinding.MyActivitiesFragmentBinding
import com.example.activitytracker.models.ActivityCoreData

class MyActivitiesView(private val view: MyActivitiesFragmentBinding) {

    fun setOnSwipeToRefreshListener(listener: () -> Unit) {
        view.refreshLayout.setOnRefreshListener() {
            listener.invoke()
        }
    }

    fun showLoadingSpinner() {
        view.LoadingSpinner.visibility = View.VISIBLE
    }

    fun hideLoadingSpinner() {
        view.LoadingSpinner.visibility = View.GONE
    }


    fun setFollowButtonText(followButton: Button?, followingActivity: Boolean) {
        when (followingActivity) {
            true -> followButton?.setText(R.string.card_unfollowText)
            false -> followButton?.setText(R.string.card_followText)
        }
    }

    fun setupRecyclerView(
        activityList: List<ActivityCoreData>,
        selectedItemListener: SelectedItemListener,
        itemFollowButtonClickListener: ItemFollowButtonClickListener
    ) {
        val activityAdapter = ActivityAdapter(
            activityList,
            { selectedItemListener.invoke(it) },
            { activityData -> itemFollowButtonClickListener.invoke(activityData) })

        view.ActivityRecylerView.apply {
            layoutManager =
                LinearLayoutManager(view.root.context, LinearLayoutManager.VERTICAL, false)
            adapter = activityAdapter
        }
        if (view.refreshLayout.isRefreshing) view.refreshLayout.isRefreshing = false
    }
}