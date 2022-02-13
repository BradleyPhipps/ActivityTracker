package com.example.activitytracker

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.activitytracker.services.*
import com.example.activitytracker.ui.main.activity.ActivityDetailsViewModel
import com.example.activitytracker.ui.main.home.HomeViewModel
import com.example.activitytracker.ui.main.myactivities.MyActivitiesViewModel
import com.example.activitytracker.ui.main.search.SearchViewModel

class ViewModelFactory(private val context: Context, private val navController: NavController) : ViewModelProvider.Factory{

    private val serviceFactory = ServiceFactory(context, navController)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) { // list possible viewModels to be created
            HomeViewModel::class.java -> homeViewModel()
            MyActivitiesViewModel::class.java -> myActivitiesViewModel()
            SearchViewModel::class.java -> searchViewModel()
            ActivityDetailsViewModel::class.java -> activityDetailsViewModel()
            else -> throw IllegalArgumentException()
        } as T
    }

    private fun homeViewModel(): HomeViewModel = HomeViewModel(serviceFactory.createActivityService(), serviceFactory.createSavedActivityRepository(), serviceFactory.createNavigationService())
    private fun myActivitiesViewModel(): MyActivitiesViewModel = MyActivitiesViewModel(serviceFactory.createActivityService(), serviceFactory.createSavedActivityRepository(),  serviceFactory.createNavigationService())
    private fun searchViewModel(): SearchViewModel = SearchViewModel()
    private fun activityDetailsViewModel(): ActivityDetailsViewModel = ActivityDetailsViewModel()
}