package com.example.activitytracker.services

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesService(context: Context) {

    private val fileName = "SavedActivities"
    private val preferenceKeyName = "Activity"
    private val privateMode = 0
    private val sharedPreferences = context.getSharedPreferences(fileName, privateMode)
    private val preferenceEditor: SharedPreferences.Editor = sharedPreferences.edit()


    fun addSavedActivity(activityKey: String): Boolean{
        return if(sharedPreferences.contains(fileName)){
            preferenceEditor.putString(preferenceKeyName, activityKey).apply()
            preferenceEditor.commit()
            true
        } else {
            false
        }
    }

    fun getSavedActivities(): MutableSet<String>? {
            return sharedPreferences.getStringSet(preferenceKeyName, null)

    }

}