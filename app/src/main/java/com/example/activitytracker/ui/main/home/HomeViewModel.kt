package com.example.activitytracker.ui.main.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.models.ActivityResponse
import com.example.activitytracker.services.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class HomeViewModel(private val networkService: NetworkService,
                    private val jsonService: JsonService,
                    private val statsService: StatsService,
                    private val dataService: DataService,
                    private val activityService: ActivityService
) : ViewModel() {

    lateinit var activityResponse: ActivityCoreData

    var onArticleDataLoaded: (() -> Unit)? = null
    var onArticleDataLoading: (() -> Unit)? = null

    @ExperimentalCoroutinesApi
    fun getActivity() {

        onArticleDataLoading?.invoke()

        viewModelScope.launch {
//            when(val data = dataService.requestApiDataToObject<ActivityResponse>("https://www.boredapi.com/api/activity/")){
//                is DataServiceResult.Success-> {
//                    activityResponse = data.data
//                    onArticleDataLoaded?.invoke()
//                }
//                is DataServiceResult.Error->Log.d("Error: ", data.exception.toString())
//            }
            activityResponse = activityService.getSingleActivity()
            onArticleDataLoaded?.invoke()


        }
    }
}
