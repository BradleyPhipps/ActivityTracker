package com.example.activitytracker.ui.main.home

import android.util.Log
import android.view.View
import com.example.activitytracker.databinding.HomeFragmentBinding
import com.example.activitytracker.models.ActivityResponse

class HomeView(private val view: HomeFragmentBinding) {

    fun setButtonClickedListener(listener  : () -> Unit){
        view.buttonGenerateActivity.setOnClickListener() {
            listener.invoke()
        }
    }

    fun showLoadingSpinner(){
        view.LoadingSpinner.visibility = View.VISIBLE
        Log.d("Logs: ", "show spinner")
    }

    fun hideLoadingSpinner(){
        view.LoadingSpinner.visibility = View.GONE
    }

    fun onDataLoaded(pArticleData: ActivityResponse){
       // view.articleRecyclerView.apply {
        //    layoutManager = LinearLayoutManager(view.root.context, LinearLayoutManager.VERTICAL, false)
        //    adapter = ArticleAdapter(pArticleData)
       // }
        Log.d("Logs: ","${pArticleData.activity}")
        hideLoadingSpinner()
     //   view.articleTopic.text = pArticleData.data.topic.title
    }
}