package com.example.activitytracker.ui.main.search

import android.content.Context
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitytracker.R
import com.example.activitytracker.databinding.SearchFragmentBinding
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.ui.main.myactivities.ActivityAdapter

class SearchView(private val view: SearchFragmentBinding) {

    val context: Context = view.root.context

    var minAccessibility: Float = 0f
    var maxAccessibility: Float = 1f
        get() = view.maxAccessibility.text.toString().toFloat()
    var participants: Int = 0
    var minPrice: Float = 0f
    var maxPrice: Float = 1f
    var activityType: String = ""

    fun setSearchClickListener(listener:(Float)-> Unit){
        view.search.setOnClickListener() {
            listener.invoke(maxAccessibility)
        }
    }

    fun setFollowButtonText(followButton: Button, followingActivity: Boolean) {
        when (followingActivity) {
            true -> followButton.setText(R.string.card_unfollowText)
            false -> followButton.setText(R.string.card_followText)
        }
    }

    fun showLoadingSpinner(){
        view.LoadingSpinner.visibility = View.VISIBLE
    }

    fun hideLoadingSpinner(){
        view.LoadingSpinner.visibility = View.GONE
    }

    fun setupRecyclerView(activityList: List<ActivityCoreData>,
                          selectedItemListener: SelectedItemListener,
                          itemFollowButtonClickListener: ItemFollowButtonClickListener
    ){
        val activityAdapter = ActivityAdapter(
            activityList,
            {selectedItemListener.invoke(it)},
            {button,activityData -> itemFollowButtonClickListener.invoke(button, activityData)}
        )

        view.SearchRecyclerView.apply {
            layoutManager = LinearLayoutManager(view.root.context, LinearLayoutManager.VERTICAL, false)
            adapter = activityAdapter
        }

    }

}