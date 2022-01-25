package com.example.activitytracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.activitytracker.services.*
import com.example.activitytracker.ui.main.home.HomeViewModel

class ViewModelFactory() : ViewModelProvider.Factory{
//add service container?
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) { // list possible viewModels to be created
            HomeViewModel::class.java -> mainViewModel()
            else -> throw IllegalArgumentException()
        } as T
    }

    private fun mainViewModel(): HomeViewModel = HomeViewModel(NetworkService(), JsonService(), StatsService(), DataService(NetworkService(), JsonService()), ActivityService(DataService(NetworkService(), JsonService()),ActivityResponseToActivityCoreDataConverter())
    )
}