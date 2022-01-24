package com.example.activitytracker.models

data class ActivityResponse(
    val accessibility: Float,
    val activity: String,
    val key: String,
    val link: String?,
    val participants: Int,
    val price: Float,
    val type: String
)