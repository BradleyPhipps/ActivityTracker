package com.example.activitytracker.services.activity

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.activitytracker.R
import com.example.activitytracker.models.ActivityQueryData

class ActivityQueryBuilder(private val context: Context) {

    //region member declarations
    private val scheme = context.getString(R.string.activityQueryScheme)
    private val authority = context.getString(R.string.activityQueryAuthority)
    private val pathStart = context.getString(R.string.activityQueryPath1)
    private val pathEnd = context.getString(R.string.activityQueryPath2)

    private val participants = context.getString(R.string.activityQueryParameterParticipants)
    private val activityType = context.getString(R.string.activityQueryParameterType)

    private val minAccessibility = context.getString(R.string.activityQueryParameterMinAccessibility)
    private val maxAccessibility = context.getString(R.string.activityQueryParameterMaxAccessibility)

    private val minPrice = context.getString(R.string.activityQueryParameterMinPrice)
    private val maxPrice = context.getString(R.string.activityQueryParameterMaxPrice)

    //endregion

    fun buildQuery(queryData: ActivityQueryData): String{
        val builder = Uri.Builder()
        builder.scheme(scheme).authority(authority).appendPath(pathStart).appendPath(pathEnd)

        if(queryData.activityQueryAccessibility.activityMaxAccessibility !=null){
            builder.appendQueryParameter(minAccessibility, queryData.activityQueryAccessibility.activityMinAccessibility.toString())
            builder.appendQueryParameter(maxAccessibility, queryData.activityQueryAccessibility.activityMaxAccessibility.toString())
        }

        if(queryData.activityQueryPrice.activityPriceMax !=null){
            builder.appendQueryParameter(minPrice, queryData.activityQueryPrice.activityPriceMin.toString())
            builder.appendQueryParameter(maxPrice, queryData.activityQueryPrice.activityPriceMin.toString())
        }

        if(queryData.activityNumberParticipants != null && queryData.activityNumberParticipants > 0){
            builder.appendQueryParameter(participants, queryData.activityNumberParticipants.toString())
        }

        if(queryData.activityType != null && queryData.activityType != ""){
            builder.appendQueryParameter(activityType, queryData.activityType)
        }
        return builder.build().toString()
    }

}