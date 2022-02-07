package com.example.activitytracker

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.activitytracker.services.*
import com.example.activitytracker.ui.main.home.HomeViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory{

    private val serviceFactory = ServiceFactory(context)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) { // list possible viewModels to be created
            HomeViewModel::class.java -> mainViewModel()
            else -> throw IllegalArgumentException()
        } as T
    }

    private fun mainViewModel(): HomeViewModel = HomeViewModel(serviceFactory.createActivityService(), serviceFactory.createSavedActivityRepository())
}