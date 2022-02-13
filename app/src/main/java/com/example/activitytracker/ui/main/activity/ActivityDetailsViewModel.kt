package com.example.activitytracker.ui.main.activity

import androidx.lifecycle.ViewModel
import com.example.activitytracker.SavedActivityRepository
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.services.json.JsonService

class ActivityDetailsViewModel(
    private val savedActivityRepository: SavedActivityRepository,
    private val jsonService: JsonService
) : ViewModel() {
    lateinit var activityDataString: String
    lateinit var activityCoreData: ActivityCoreData

    fun convertDataStringToActivity(){
        activityCoreData = jsonService.convertToObject(activityDataString)
    }



}