package com.example.activitytracker.services.converter

import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.models.ActivityResponse

class ActivityResponseToActivityCoreDataConverter {
    fun convert(activityResponse:ActivityResponse): ActivityCoreData {
        with(activityResponse) {
            return ActivityCoreData(
                activityAccessibility = accessibility,
                activityTitle = activity,
                activityId = key,
                activityLink = link,
                activityNumberParticipants = participants,
                activityPrice = price,
                activityType = type,
                activityFollowed = false
            )
        }
    }
}