package com.example.activitytracker.services.activity

import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.util.Log
import com.example.activitytracker.R
import com.example.activitytracker.models.ActivityQueryData

class ActivityQueryBuilder {



    //region member declarations
    private val scheme = "https"
    private val authority = "www.boredapi.com"
    private val pathStart = "api"
    private val pathEnd = "activity"

    private val participants = "participants"
    private val activityType = "type"

    private val minAccessibility = "minaccessibility"
    private val maxAccessibility = "maxaccessibility"

    private val minPrice = "minprice"
    private val maxPrice = "maxprice"

    //endregion

    fun buildQuery(queryData: ActivityQueryData): String{
        val builder = Uri.Builder()
        builder.scheme(scheme).authority(authority).appendPath(pathStart).appendPath(pathEnd)

        if(queryData.activityQueryAccessibility.activityMaxAccessibility !=null&& queryData.activityQueryAccessibility.activityMaxAccessibility >= 0){
            builder.appendQueryParameter(minAccessibility, queryData.activityQueryAccessibility.activityMinAccessibility.toString())
            builder.appendQueryParameter(maxAccessibility, queryData.activityQueryAccessibility.activityMaxAccessibility.toString())
        }

        if(queryData.activityQueryPrice.activityPriceMax !=null && queryData.activityQueryPrice.activityPriceMax >= 0){
            builder.appendQueryParameter(minPrice, queryData.activityQueryPrice.activityPriceMin.toString())
            builder.appendQueryParameter(maxPrice, queryData.activityQueryPrice.activityPriceMax.toString())
        }

        if(queryData.activityNumberParticipants != null && queryData.activityNumberParticipants > 0){
            builder.appendQueryParameter(participants, queryData.activityNumberParticipants.toString())
        }

        if(queryData.activityType != null && queryData.activityType != ""){
            builder.appendQueryParameter(activityType, queryData.activityType)
        }
        Log.d("Logs",builder.build().toString() )
        return builder.build().toString()
    }

}