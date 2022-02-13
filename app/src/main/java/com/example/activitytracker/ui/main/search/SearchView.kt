package com.example.activitytracker.ui.main.search

import android.content.Context
import android.util.Log
import com.example.activitytracker.databinding.SearchFragmentBinding
import com.example.activitytracker.models.ActivityCoreData

class SearchView(private val view: SearchFragmentBinding) {

    val context: Context = view.root.context

    fun setSearchClickListener(listener:()-> Unit){
        view.search.setOnClickListener() {
            listener.invoke()
        }
    }



    fun SearchDataLoaded(activityList: List<ActivityCoreData>,){
        Log.d("Logs ", activityList[0].activityTitle )
    }

}