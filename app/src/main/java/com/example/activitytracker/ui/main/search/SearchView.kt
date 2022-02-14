package com.example.activitytracker.ui.main.search

import android.content.Context
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitytracker.R
import com.example.activitytracker.databinding.SearchFragmentBinding
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.ui.main.myactivities.ActivityAdapter

class SearchView(private val view: SearchFragmentBinding) {

    val context: Context = view.root.context


    var accessibility:String = ""
        get() = view.accessibilitySpinner.selectedItem.toString()
        private set(value: String) {field = value}
    var priceString = ""
        get() = view.priceSpinner.selectedItem.toString()
        private set(value: String) {field = value}
    var activityType:String = ""
         get() = view.typeSpinner.selectedItem.toString()
        private set(value: String) {field = value}

    fun setSearchClickListener(listener:()-> Unit){
        view.search.setOnClickListener() {
            listener.invoke()
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
            {activityData -> itemFollowButtonClickListener.invoke(activityData)}
        )

        view.SearchRecyclerView.apply {
            layoutManager = LinearLayoutManager(view.root.context, LinearLayoutManager.VERTICAL, false)
            adapter = activityAdapter
        }

    }

    fun populateTypeSpinner(){
        ArrayAdapter.createFromResource(
            view.root.context,
            R.array.activityTypes,
            android.R.layout.simple_spinner_dropdown_item
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            view.typeSpinner.adapter = arrayAdapter
        }
    }

    fun populatePriceSpinner(){
        ArrayAdapter.createFromResource(
            view.root.context,
            R.array.activityPrices,
            android.R.layout.simple_spinner_dropdown_item
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            view.priceSpinner.adapter = arrayAdapter
        }
    }

    fun populateAccessibilitySpinner(){
        ArrayAdapter.createFromResource(
            view.root.context,
            R.array.activityAccessibilityLevels,
            android.R.layout.simple_spinner_dropdown_item
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            view.accessibilitySpinner.adapter = arrayAdapter
        }
    }

}