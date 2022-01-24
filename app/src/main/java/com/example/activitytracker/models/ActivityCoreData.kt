package com.example.activitytracker.models

data class ActivityCoreData(
    val activityAccessibility: Float,
    val activityTitle: String,
    val activityId: String,
    val activityLink: String?,
    val activityNumberParticipants: Int,
    val activityPrice: Float,
    val activityType: String
) {
}