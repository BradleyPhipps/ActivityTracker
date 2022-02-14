package com.example.activitytracker.models

data class ActivityQueryData (
    val activityQueryAccessibility: ActivityQueryAccessibility,
    val activityNumberParticipants: Int? = 0,
    val activityQueryPrice: ActivityQueryPrice,
    val activityType: String?,
)