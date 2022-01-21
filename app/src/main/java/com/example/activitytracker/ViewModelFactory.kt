package com.example.activitytracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.activitytracker.services.JsonService
import com.example.activitytracker.services.StatsService
import com.example.activitytracker.services.data.DataService
import com.example.activitytracker.ui.main.MainFragment
import com.example.activitytracker.ui.main.MainViewModel

class ViewModelFactory() : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) { // list possible viewModels to be created
            MainViewModel::class.java -> mainViewModel()
            else -> throw IllegalArgumentException()
        } as T
    }

    private fun mainViewModel(): MainViewModel = MainViewModel(DataService(), JsonService(), StatsService())
}