package com.example.activitytracker.services.navigation

import android.os.Bundle
import androidx.navigation.NavController
import com.example.activitytracker.services.json.JsonService

class NavigationService(
    val navController: NavController,
    val jsonService: JsonService
) {
    companion object{
        const val bundleKey = "activityString"
    }
    fun navigateToFragment(destinationActionId: Int){
        navController.navigate(destinationActionId)
    }

    inline fun <reified T>  navigateToFragmentWithData(destinationActionId: Int, dataObject: T){
        navController.navigate(destinationActionId, createNavigationBundle(dataObject))
    }

   inline fun <reified T> createNavigationBundle(dataObject: T) : Bundle{
       val navBundle = Bundle()
       val activityString = jsonService.convertToString(dataObject)
       navBundle.putString(bundleKey, activityString)

       return navBundle
    }

}