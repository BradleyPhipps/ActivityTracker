package com.example.activitytracker.models

data class ActivityQueryData (
    val activityMinAccessibility: Float = 0f,
    val activityMaxAccessibility: Float?,
    val activityNumberParticipants: Int? = 0,
    val activityPriceMin: Float = 0f,
    val activityPriceMax: Float?,
    val activityType: String?,
)