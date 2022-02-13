package com.example.activitytracker.services

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesService(private val context: Context) {

    private var fileName = "SavedActivities"
    private val privateMode = Context.MODE_PRIVATE
    private var sharedPreferences = context.getSharedPreferences(fileName, privateMode)
    private var preferenceEditor: SharedPreferences.Editor = sharedPreferences.edit()

    fun openSharedPreferences(requestedFileName:String){
        fileName = requestedFileName
        sharedPreferences = context.getSharedPreferences(fileName, privateMode)
        preferenceEditor = sharedPreferences.edit()
    }

    fun saveToSharedPreferences(keyName: String, keyValue:Int){
        if(!sharedPreferences.contains(keyName)) {
            preferenceEditor.putInt(keyName, keyValue).apply()
            preferenceEditor.commit()
        }
    }

    fun removeFromSharedPreferences(keyName: String){
        if(sharedPreferences.contains(keyName)) {
            preferenceEditor.remove(keyName).apply()
            preferenceEditor.commit()
        }
    }

    fun getSharedPreferences(): MutableList<String>? {
        return sharedPreferences.getStringSet(fileName, emptySet())?.toMutableList()
    }

    fun getSharedPreferenceKeys(): MutableList<String> {
        val sharedPreferenceIds = sharedPreferences.all.map { it.key }
        return sharedPreferenceIds.toMutableList()
    }


}