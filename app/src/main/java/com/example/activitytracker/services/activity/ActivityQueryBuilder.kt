package com.example.activitytracker.services.activity

import android.content.Context
import android.net.Uri
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

        if(queryData.activityMaxAccessibility !=null){
            builder.appendQueryParameter(minAccessibility, queryData.activityMinAccessibility.toString())
            builder.appendQueryParameter(maxAccessibility, queryData.activityMaxAccessibility.toString())
        }

        return builder.build().toString()
    }

}